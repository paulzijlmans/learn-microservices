package microservices.book.multiplication.challenge;

import microservices.book.multiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        boolean isCorrect =
            attemptDTO.guess() == attemptDTO.factorA() * attemptDTO.factorB();
        User user = new User(null, attemptDTO.userAlias());

        return new ChallengeAttempt(null, user, attemptDTO.factorA(),
            attemptDTO.factorB(), attemptDTO.guess(), isCorrect);
    }
}
