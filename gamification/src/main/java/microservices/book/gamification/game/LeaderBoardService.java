package microservices.book.gamification.game;

import java.util.List;
import microservices.book.gamification.game.domain.LeaderBoardRow;

public interface LeaderBoardService {

    List<LeaderBoardRow> getCurrentLeaderBoard();

}
