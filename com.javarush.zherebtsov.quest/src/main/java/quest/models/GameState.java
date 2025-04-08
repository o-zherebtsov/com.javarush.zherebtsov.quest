package quest.models;

public class GameState {

    private String playerName;
    private int gamesPlayed;
    private String currentScene;
    private int gamesWon;
    private int gamesLost;
    private boolean gameWon;

    public GameState() {
        this.gamesPlayed = 0;
        this.currentScene = "start";
        this.gamesWon = 0;
        this.gamesLost = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public void incrementGamesLost() {
        this.gamesLost++;
    }

    public void incrementGamesWon() {
        this.gamesWon++;
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}
