package microservices.book.gamification.game;

import java.util.List;
import microservices.book.gamification.challenge.ChallengeSolvedDTO;
import microservices.book.gamification.game.domain.BadgeType;

public interface GameService {

    GameResult newAttemptForUser(ChallengeSolvedDTO challenge);

    record GameResult(int score, List<BadgeType> badges) {

    }

}
