import java.io.FileNotFoundException;
import java.util.HashSet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * Normalization class to remove punctuation and stop words from tokens (words).
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class Normalization
{
    // hash set to store the stopwords
    private HashSet<String> stopWords;

    /**
     * Constructor for objects of class Normalization. Loads in the stop words to the hash set.
     */
    public Normalization() throws FileNotFoundException, IOException
    {
        Path fileName = Path.of("StopWords.txt");
        //Read in the file as a string
        String actual = Files.readString(fileName);

        String[] words = actual.split("\n");
        stopWords = new HashSet<String>();
        //Loop through the file string to store the stop words in the hash set
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
            stopWords.add(words[i]);
        }
    }

    /**
     * This method evaluates a token and returns true if it is a stop word and false otherwise.
     * 
     * @return boolean - true if stop word, false if not
     * @param String token - the token to be evaluated
     */
    public boolean removeStopWords(String token){
        if (stopWords.contains(token)){
            return true;
        }
        return false;
    }
}
