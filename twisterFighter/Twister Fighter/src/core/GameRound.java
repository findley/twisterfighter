package core;

import java.util.Set;

import run.Game;
import words.WordsLoader;

public class GameRound {

    /**
     * In milliseconds
     */
    public static long TIME_LIMIT = 300000L;

    public long timeRemaining = TIME_LIMIT;

    public static enum State {
        STARTING, RUNNING, PAUSED, DONE;
    }

    public State state = State.STARTING;

    private final Set<String> wordStore = WordsLoader
            .loadWords("src/words/words.txt");
    public Player player1 = new Player(Character.SOME_GUY, new int[] {
            23 * Game.GAME_WIDTH / 80, 28 * Game.GAME_HEIGHT / 60 }, wordStore);
    public Player player2 = new Player(Character.SOME_OTHER_GUY, new int[] {
            52 * Game.GAME_WIDTH / 80, 28 * Game.GAME_HEIGHT / 60 + 1 },
            wordStore);

    public GameRound() {
        player1.letterQueue.advanceLetter();
        player2.letterQueue.advanceLetter();
    }

    /**
     * Updates a player for the current combo key having been successfully
     * pressed; Does not check that this actually happened
     * 
     * @param player
     */
    public void advanceAttack(Player player) {
        player.letterQueue.advanceLetter();
        if (player.letterQueue.getLetterIndex() == 0) {
            getOpponent(player).health -= 50; // TODO: arbitrary damage value
            player.character.attackSound.play();
        }
    }

    public Player getOpponent(Player player) {
        if (player == player1) {
            return player2;
        } else if (player == player2) {
            return player1;
        } else {
            throw new IllegalArgumentException(
                    "The player must be player1 or player2");
        }
    }
}
