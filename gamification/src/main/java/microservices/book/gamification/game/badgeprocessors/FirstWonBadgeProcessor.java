package microservices.book.gamification.game.badgeprocessors;

import java.util.List;
import java.util.Optional;
import microservices.book.gamification.challenge.ChallengeSolvedEvent;
import microservices.book.gamification.game.domain.BadgeType;
import microservices.book.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

@Component
public class FirstWonBadgeProcessor implements BadgeProcessor {

    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore,
        List<ScoreCard> scoreCardList, ChallengeSolvedEvent solvedDTO) {
        return scoreCardList.size() == 1 ? Optional.of(BadgeType.FIRST_WON) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.FIRST_WON;
    }
}
