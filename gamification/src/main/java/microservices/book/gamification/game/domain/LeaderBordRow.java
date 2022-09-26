package microservices.book.gamification.game.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;

@Value
@AllArgsConstructor
public class LeaderBordRow {

    Long userId;
    Long totalScore;
    @With
    List<String> badges;

    public LeaderBordRow(final Long userId, final Long totalScore) {
        this.userId = userId;
        this.totalScore = totalScore;
        this.badges = List.of();
    }
}
