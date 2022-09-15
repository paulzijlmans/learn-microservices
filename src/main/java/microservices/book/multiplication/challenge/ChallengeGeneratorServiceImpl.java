package microservices.book.multiplication.challenge;

import java.util.Random;
import java.util.random.RandomGenerator;

public class ChallengeGeneratorServiceImpl implements ChallengeGeneratorService {

    private final static int MINIMUM_FACTOR = 11;
    private final static int MAXIMUM_FACTOR = 100;

    private final RandomGenerator randomGenerator;

    ChallengeGeneratorServiceImpl() {
        this.randomGenerator = new Random() {
        };
    }

    protected ChallengeGeneratorServiceImpl(final RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Challenge randomChallenge() {
        return new Challenge(next(), next());
    }

    private int next() {
        return randomGenerator.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR;
    }
}
