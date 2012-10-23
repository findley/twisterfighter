package core;

import java.util.LinkedList;

public class KeyQueue {

    public static String[] COMBO_KEYS = { "e", "r", "t", "y", "u", "i", "o",
            "p", "d", "f", "g", "h", "j", "k", "l", "v", "b", "n", "m" };

    public static int INIT_LENGTH = 10;

    private LinkedList<String> keys = null;

    public KeyQueue() {
        keys = new LinkedList<String>();
        for (int i = 0; i < INIT_LENGTH; i++) {
            addRandomKey();
        }
    }

    private void addRandomKey() {
        keys.add(COMBO_KEYS[(int) Math.floor(Math.random() * COMBO_KEYS.length)]);
    }

    public String popKey(String notAllowed) {
        String key = null;
        do {
            addRandomKey();
            key = keys.poll();
        } while (key.equals(notAllowed));
        return key;
    }

    public String get(int index) {
        return keys.get(index);
    }
}
