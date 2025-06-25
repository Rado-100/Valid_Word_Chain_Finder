import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

/**
 * Unit tests for the {@link WordsLoader} utility class.
 */
public class WordsLoaderTest {
  
  @Test
  void testSuccessfulLoad() throws Exception {
    String url = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";
    Set<String> words = WordsLoader.loadAll(url);
    assertNotNull(words);
    assertTrue(words.size() > 10000);
    assertTrue(words.contains("STARTLING"));
  }
  
  @Test
  void testInvalidUrl() {
    String badUrl = "ht@tp://bad url";
    
    assertThrows(URISyntaxException.class, () -> {
      WordsLoader.loadAll(badUrl);
    });
  }
  
  @Test
  void test404NotFound() {
    String nonExistingUrl = "https://example.com/this/file/does/not/exist.txt";
    
    assertThrows(IOException.class, () -> {
      WordsLoader.loadAll(nonExistingUrl);
    });
  }
  
  
}
