package words;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WordsLoader {

    public static final Set<Character> ALLOWED_LETTERS = new HashSet<Character>(
            Arrays.asList('e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'd', 'f',
                    'g', 'h', 'j', 'k', 'l', 'v', 'b', 'n', 'm'));

    public static Set<String> loadWords(String fileName) {
        try {
            Set<String> words = new HashSet<String>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            lineLoop: while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("")) {
                    continue;
                }
                if (line.length() != 5) {
                    continue;
                }
                for (char c : line.toCharArray()) {
                    if (!ALLOWED_LETTERS.contains(c)) {
                        continue lineLoop;
                    }
                }
                words.add(line);
            }
            reader.close();
            return words;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("File read error", e);
        }
    }

    @Test
    public void loadWordsTest() {
        Set<String> words = loadWords("src\\words\\words.txt");
        assertTrue(words.contains("truth"));
        assertFalse(words.contains("maybe"));
        assertFalse(words
                .contains("this file is from http://www.wordlistgenerator.net/"));
    }
}
