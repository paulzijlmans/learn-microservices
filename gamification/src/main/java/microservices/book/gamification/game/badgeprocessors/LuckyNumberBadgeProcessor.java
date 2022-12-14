package microservices.book.gamification.game.badgeprocessors;

import java.util.List;
import java.util.Optional;
import microservices.book.gamification.challenge.ChallengeSolvedEvent;
import microservices.book.gamification.game.domain.BadgeType;
import microservices.book.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

@Component
public class LuckyNumberBadgeProcessor implements BadgeProcessor {

    private static final int LUCKY_FACTOR = 42;

    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore,
        List<ScoreCard> scoreCardList, ChallengeSolvedEvent solved) {
        return solved.factorA() == LUCKY_FACTOR ||
            solved.factorB() == LUCKY_FACTOR ?
            Optional.of(BadgeType.LUCKY_NUMBER) :
            Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.LUCKY_NUMBER;
    }
}
