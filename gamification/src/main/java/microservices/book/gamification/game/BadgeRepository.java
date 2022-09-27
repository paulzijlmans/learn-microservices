package microservices.book.gamification.game;

import java.util.List;
import microservices.book.gamification.game.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

public interface BadgeRepository extends CrudRepository<BadgeCard, Long> {

    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);

}
