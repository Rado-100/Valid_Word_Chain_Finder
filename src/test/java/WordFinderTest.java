import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

/**
 * Unit tests for the {@link WordFinder} utility class.
 */
public class WordFinderTest {
  
  @Test
  void testFindValidReducibleWord() {
    Set<String> words =
        Set.of("startling", "starting", "staring", "string", "sting", "sing", "sin", "in", "i");
    
    Set<String> result = WordFinder.find(words, 9);
    assertTrue(result.contains("startling"));
    assertEquals(1, result.size());
  }
  
  @Test
  void testFindNonReducibleWord() {
    Set<String> words = Set.of("hello", "hell", "hel", "he", "h");
    Set<String> result = WordFinder.find(words, 5);
    assertTrue(result.isEmpty());
  }
  
  @Test
  void testFind_wordThatIsAlreadyValidOneLetter() {
    Set<String> words = Set.of("a", "i");
    Set<String> result = WordFinder.find(words, 1);
    
    assertTrue(result.isEmpty());
  }
  
  @Test
  void testFindMixedValidAndInvalidWords() {
    Set<String> words =
        Set.of("startling", "starting", "staring", "string", "sting", "sing", "sin", "in", "i",
            "invalid", "zzz", "abc");
    
    Set<String> result = WordFinder.find(words, 9);
    assertEquals(Set.of("startling"), result);
  }
  
  @Test
  void testFindEmptySet() {
    Set<String> words = Set.of();
    Set<String> result = WordFinder.find(words, 5);
    assertTrue(result.isEmpty());
  }
  
  @Test
  void testFind_nullNotAllowed() {
    assertThrows(IllegalArgumentException.class, () -> WordFinder.find(null, 5));
  }
  
  @Test
  void testFind_multipleValidWords() {
    Set<String> words =
        Set.of("startling", "starting", "staring", "string", "sting", "sing", "sin", "in", "i",
            "planets", "planet", "plane", "plan", "pan", "an", "a");
    
    Set<String> result9 = WordFinder.find(words, 9); // "startling"
    Set<String> result7 = WordFinder.find(words, 7); // "planets"
    
    assertEquals(Set.of("startling"), result9);
    assertEquals(Set.of("staring", "planets"), result7);
  }
  
  // Specific to the GeoWealth coding challenge
  @Test
  public void testFindPerformance() throws IOException, URISyntaxException {
    
    Set<String> allWords = WordsLoader.loadAll(
        "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
    
    long startTime = System.currentTimeMillis();
    
    Set<String> result = WordFinder.find(allWords, 9);
    
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    
    assertTrue(duration < 2000, "WordFinder.find() took too long: " + duration + "ms");
    assertTrue(result.contains("STARTLING"), "Expected word not found");
  }
  
}
