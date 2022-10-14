package microservices.book.gamification.game;

import microservices.book.gamification.challenge.ChallengeSolvedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameEventHandlerTest {

    private GameEventHandler gameEventHandler;

    @Mock
    private GameService gameService;

    @BeforeEach
    void setup() {
        gameEventHandler = new GameEventHandler(gameService);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void handleMultiplicationSolved(boolean correct) {
        ChallengeSolvedEvent event = solvedEvent(correct);

        gameEventHandler.handleMultiplicationSolved(event);

        verify(gameService).newAttemptForUser(event);
    }

    private ChallengeSolvedEvent solvedEvent(boolean correct) {
        return new ChallengeSolvedEvent(1L, correct, 30, 40, 10L, "john");
    }

}