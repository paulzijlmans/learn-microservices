package microservices.book.multiplication.challenge;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChallengeServiceTest {

    private ChallengeService challengeService;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl();
    }

    @Test
    public void checkCorrectAttemptTest() {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 3000);

        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 5000);

        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        then(resultAttempt.isCorrect()).isFalse();
    }
}