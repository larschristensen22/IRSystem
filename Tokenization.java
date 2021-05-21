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
     * @param doc the document to tokenize.
     * @return ArrayList<String> list that new tokens are added to.
     */
    public ArrayList<String> tokenize(Document doc)
    {

        tokens.clear();

        //intilialize tokenizer
        StringTokenizer tokenizer = new StringTokenizer(doc.getText());

        while (tokenizer.hasMoreTokens()) {

            //perform tasks on each token
            String token = tokenizer.nextToken();

            token = token.toLowerCase();
            token = removePunctuation(token);
            token = token.trim();

            if (!normalize.removeStopWords(token)) {
                tokens.add(token);
            }

        }

        return tokens;

    }

    /**
     * Tokenize each word in a given Document with stopwords.
     * 
     * @param doc the document to tokenize.
     * @return ArrayList<String> list that new tokens are added to.
     */
    public ArrayList<String> tokenizeWithStop(Document doc)
    {

        tokens.clear();

        //intilialize tokenizer
        StringTokenizer tokenizer = new StringTokenizer(doc.getText());

        while (tokenizer.hasMoreTokens()) {

            //perform tasks on each token
            String token = tokenizer.nextToken();

            token = token.toLowerCase();
            token = removePunctuation(token);
            token = token.trim();
            tokens.add(token);
        }

        return tokens;

    }

    /**
     * Tokenize each word in a given Document.
     * 
     * @param input the document to tokenize.
     * @return ArrayList<String> list that new tokens are added to.
     */
    public ArrayList<String> tokenizeQuery(String input, int stopWords)
    {
        ArrayList<String> queryTokens = new ArrayList<String>();
        int firstQuote = input.indexOf("\"");
        String subToken = "";

        if (firstQuote != -1) {
            subToken = input.substring(firstQuote + 1);
            int secondQuote = subToken.indexOf("\"");
            if (secondQuote != -1) {
                subToken = input.substring(firstQuote + 1, secondQuote + 1);
                input = input.substring(0, firstQuote) + input.substring(secondQuote + 2);
                queryTokens.add(subToken);
            }
        }

        StringTokenizer tokenizer = new StringTokenizer(input);

        while (tokenizer.hasMoreTokens()) {

            //perform tasks on each token
            String token = tokenizer.nextToken();

            token = removePunctuation(token);
            token = token.trim();
            
            if (token.equals("AND") || token.equals("OR") || token.equals("NOT")) {
                queryTokens.add(token);
            } else if (stopWords == 1) {
                if (!normalize.removeStopWords(token)) {
                    queryTokens.add(token);
                }
            } else {
                queryTokens.add(token);
            }

        }

        return queryTokens;

    }

    /**
     * Remove the punctuation from each document.
     * 
     * @param token the token to remove punctuation from.
     * @return String of the new token with removed punctuation.
     */
    public String removePunctuation(String token) {

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
