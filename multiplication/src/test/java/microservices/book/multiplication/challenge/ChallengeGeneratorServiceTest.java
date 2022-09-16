package microservices.book.multiplication.challenge;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChallengeGeneratorServiceTest {

    private ChallengeGeneratorService challengeGeneratorService;

    @Mock
    private RandomGenerator randomGenerator;

    @BeforeEach
    public void setUp() {
        challengeGeneratorService = new ChallengeGeneratorServiceImpl(randomGenerator);
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() {
        given(randomGenerator.nextInt(89)).willReturn(20, 30);

        Challenge challenge = challengeGeneratorService.randomChallenge();

        then(challenge).isEqualTo(new Challenge(31, 41));
    }
}