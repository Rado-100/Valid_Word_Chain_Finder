import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for loading a list of words from an external source.
 *
 * <p>The words are fetched from a remote URL.
 * The source file includes a header, which is skipped automatically.
 *
 * @author Rado S
 */
public class WordsLoader {
  
  /**
   * Loads all valid words from the external URL.
   * 
   * @param url the URL string pointing to the remote text file containing words
   * @return a collection containing all words from the file, excluding header lines
   * @throws IOException if there is an error reading from the URL stream
   * @throws URISyntaxException if the URL is malformed
   */
  public static Set<String> loadAll(String url) throws IOException, URISyntaxException {
    
    URI allWordsURI = new URI(url);
    URL allWordsURL = allWordsURI.toURL();
    
    try (BufferedReader bufferReader = new BufferedReader(
        new InputStreamReader(allWordsURL.openConnection().getInputStream()))) {
      
      return bufferReader.lines()
          .filter(line -> !line.isBlank())
          .collect(Collectors.toSet());
      
    }
  }
}
