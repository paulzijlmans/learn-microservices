package microservices.book.multiplication.challenge;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {

    List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);

}
