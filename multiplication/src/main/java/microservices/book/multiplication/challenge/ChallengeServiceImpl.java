package microservices.book.multiplication.challenge;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.user.User;
import microservices.book.multiplication.user.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository attemptRepository;

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

        return attemptRepository.save(checkedAttempt);
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(final String userAlias) {
        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
