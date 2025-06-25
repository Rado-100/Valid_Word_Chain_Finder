import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

public class Main {
  
  public static void main(String[] args) throws IOException, URISyntaxException {
    
    Set<String> allWords = WordsLoader.loadAll(
        "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
    
    Set<String> validWords = WordFinder.find(allWords, 9);
    
    validWords.forEach(s -> System.out.println(s));
    System.out.println(validWords.size());
  }
}
