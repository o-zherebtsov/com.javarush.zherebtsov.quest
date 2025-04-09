package quest.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    void testInitialState() {
        GameState gameState = new GameState();

        assertEquals("start", gameState.getCurrentScene());
        assertEquals(0, gameState.getGamesPlayed());
        assertNull(gameState.getPlayerName());
        assertFalse(gameState.isGameWon());
        assertEquals(0, gameState.getGamesWon());
        assertEquals(0, gameState.getGamesLost());
    }

    @Test
    void testSettersAndGetters() {
        GameState gameState = new GameState();

        gameState.setPlayerName("TestPlayer");
        gameState.setCurrentScene("scene1");
        gameState.setGameWon(true);
        gameState.incrementGamesPlayed();

        assertEquals("TestPlayer", gameState.getPlayerName());
        assertEquals("scene1", gameState.getCurrentScene());
        assertTrue(gameState.isGameWon());
        assertEquals(1, gameState.getGamesPlayed());
    }

    @Test
    void testIncrementGamesPlayed() {
        GameState gameState = new GameState();

        gameState.incrementGamesPlayed();
        gameState.incrementGamesPlayed();

        assertEquals(2, gameState.getGamesPlayed());
    }

    @Test
    void testIncrementGamesLost() {
        GameState gameState = new GameState();

        gameState.incrementGamesLost();
        gameState.incrementGamesLost();
        gameState.incrementGamesLost();

        assertEquals(3, gameState.getGamesLost());
    }

    @Test
    void testIncrementGamesWon() {
        GameState gameState = new GameState();

        gameState.incrementGamesWon();
        gameState.incrementGamesWon();
        gameState.incrementGamesWon();
        gameState.incrementGamesWon();

        assertEquals(4, gameState.getGamesWon());
    }
}
