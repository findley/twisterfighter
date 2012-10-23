package core;

import java.util.Set;

public class Player {

    public static final int MAX_HEALTH = 1000;

    public final Character character;

    public int health = MAX_HEALTH;
    public final int[] location;

    public final WordLetterQueue letterQueue;

    public Player(Character character, int[] location, Set<String> wordStore) {
        this.character = character;
        this.location = location;
        letterQueue = new WordLetterQueue(wordStore);
    }
}
