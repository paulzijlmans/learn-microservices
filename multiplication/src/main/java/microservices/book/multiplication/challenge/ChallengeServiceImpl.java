package microservices.book.multiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.user.User;
import microservices.book.multiplication.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository attemptRepository;
    private final ChallengeEventPub challengeEventPub;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        User user = userRepository.findByAlias(attemptDTO.userAlias())
            .orElseGet(() -> {
                log.info("Creating new user with alias {}",
                    attemptDTO.userAlias());
                return userRepository.save(new User(attemptDTO.userAlias()));
            });

        boolean isCorrect =
            attemptDTO.guess() == attemptDTO.factorA() * attemptDTO.factorB();

        ChallengeAttempt checkedAttempt = new ChallengeAttempt(null, user, attemptDTO.factorA(),
            attemptDTO.factorB(), attemptDTO.guess(), isCorrect);

        ChallengeAttempt storedAttempt = attemptRepository.save(checkedAttempt);

        // Publishes an event to notify potentially interested subscribers
        challengeEventPub.challengeSolved(storedAttempt);

        return storedAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(final String userAlias) {
        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
