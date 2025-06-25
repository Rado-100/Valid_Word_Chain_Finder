import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Utility class for finding special words in a dictionary that can be reduced
 * one letter at a time, with each intermediate word being valid.
 * Example: STARTLING -> STARTING -> STARING -> STRING -> STING -> SING -> SIN -> IN -> I
 * The only valid one-letter words are "A" and "I".
 *
 * @author Rado S
 */
public class WordFinder {
  
  /**
   * Set of valid one-letter English words.
   */
  private static final LinkedHashSet<String> VALID_ONE_LETTER_WORDS =
      new LinkedHashSet<>(Arrays.asList("A", "I"));
  
  /**
   * Finds all words of the given length that can be reduced to a valid one-letter word
   * by successively removing one letter at a time, with each intermediate word being valid.
   *
   * @param words the set of valid dictionary words
   * @param wordLength the target starting word length
   * @return a set of words that can be fully reduced according to the rules
   */
  public static Set<String> find(Set<String> words, int wordLength) {
    
    if (words == null) {
      throw new IllegalArgumentException("Words set must not be null.");
    }
    
    if (wordLength < 1) {
      throw new IllegalArgumentException("Word length must be at least 1");
    }
    
    
    Set<String> validWords = new HashSet<>();
    
    for (String word : words) {
      
      if (word.length() == wordLength && checkWordIsValid(words, word)) {
        validWords.add(word);
      }
    }
    return validWords;
  }
  
  /**
   * Recursively checks whether the given word can be reduced to a valid one-letter word
   * by removing one letter at a time and ensuring all intermediate words are valid.
   *
   * @param words the set of valid dictionary words
   * @param word the current word to check
   * @return true if the word can be reduced to a valid one-letter word, false otherwise
   */
  private static boolean checkWordIsValid(Set<String> words, String word) {
    
    for (int i = 0; i < word.length(); ++i) {
      String newWord = word.substring(0, i) + word.substring(i + 1);
      if (newWord.length() > 1 && words.contains(newWord)) {
        
        if (checkWordIsValid(words, newWord)) {
          return true;
        }
      }
      if (newWord.length() == 1 && WordFinder.VALID_ONE_LETTER_WORDS.contains(newWord.toUpperCase())) {
        return true;
      }
    }
    
    return false;
  }
  
}
