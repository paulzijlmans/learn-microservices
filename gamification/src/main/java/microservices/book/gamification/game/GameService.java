package microservices.book.gamification.game;

import java.util.List;
import microservices.book.gamification.challenge.ChallengeSolvedEvent;
import microservices.book.gamification.game.domain.BadgeType;

public interface GameService {

    GameResult newAttemptForUser(ChallengeSolvedEvent challenge);

    record GameResult(int score, List<BadgeType> badges) {

    }

}
