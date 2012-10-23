package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class WordLetterQueue {

    private String word = null;
    private int index;

    private final Set<String> wordStore;

    public WordLetterQueue(Set<String> wordStore) {
        this.wordStore = wordStore;
    }

    public String getWord() {
        return word;
    }

    public int getLetterIndex() {
        return index;
    }

    public char getLetter() {
        return word.charAt(index);
    }

    public int getIndex() {
        return index;
    }

    private void advanceWord() {
        if (word != null) {
            wordStore.add(word);
        }
        List<String> l = new ArrayList<String>(wordStore);
        Collections.shuffle(l);
        word = l.get(0);
        wordStore.remove(word);
        index = 0;
    }

    public void advanceLetter() {
        if (word == null) {
            advanceWord();
        } else {
            index++;
            if (index >= word.length()) {
                advanceWord();
            }
        }
    }

    public void advanceLetterIfMatches(char c) {
        if (c == getLetter()) {
            advanceLetter();
        }
    }
}
