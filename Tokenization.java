import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class tokenizes all the words from each file
 *
 * @author Cameron Costello and Lars Christensen
 * @version March 22, 2021
 */
public class Tokenization
{

    private Normalization normalize;
    private ArrayList<String> tokens;
    /**
     * Constructor for objects of class Tokenization
     * @throws IOException
     * @throws FileNotFoundException
     */
    public Tokenization() throws FileNotFoundException, IOException
    {
        this.tokens = new ArrayList<String>();
        this.normalize = new Normalization();
    }

    /**
     * Tokenize each word in a given Document.
     * 
     * @param The Document to tokenize.
     * @return ArrayList<String> list that new tokens are added to.
     */
    public ArrayList<String> tokenize(Document doc)
    {

        //intilialize tokenizer
        StringTokenizer tokenizer = new StringTokenizer(doc.getText());
        while (tokenizer.hasMoreTokens()) {

            //perform tasks on each token
            String token = tokenizer.nextToken();
            token = removePunctuation(token);
            token = token.trim();
            if (!normalize.removeStopWords(token)) {
                tokens.add(token);
            }
        }

        return tokens;

    }

    /**
     * Remove the punctuation from each document.
     * 
     * @param The token to remove punctuation from.
     * @return String of the new token with removed punctuation.
     */
    public String removePunctuation(String token) {
        
        token = token.toLowerCase();

        String newToken = "";

        //check to see if there is an apostrophe and remove it
        if(token.contains("'")) {
            int apostropheIndex = token.indexOf("'");
            if (token.substring(apostropheIndex+1).length() <= 1) {
                token = token.substring(0, apostropheIndex);
            }
        }
        
        //create each new token
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);

            if(Character.isLetter(c)) {
                newToken += c;
            }
            else if(Character.isDigit(c)) {
                newToken += c;
            }

        }

        return newToken;
    }
}
