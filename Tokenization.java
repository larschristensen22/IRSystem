import java.util.StringTokenizer;
import java.util.ArrayList;
/**
 * Write a description of class Tokenization here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tokenization
{

    private ArrayList<String> tokens;
    /**
     * Constructor for objects of class Tokenization
     */
    public Tokenization()
    {
        this.tokens = new ArrayList<String>();
    }

    public ArrayList<String> tokenize(ArrayList<Document> docs)
    {

        for (int i = 0; i < docs.size(); i++) {

            StringTokenizer tokenizer = new StringTokenizer(docs.get(i).getText());
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                token = removePunctuation(token);
                token = token.trim();
                tokens.add(token);
            }
        }
        return tokens;

    }

    public String removePunctuation(String token) {
        token = token.toLowerCase();
        String newToken = "";
        
        if(token.contains("'")) {
            int apostropheIndex = token.indexOf("'");
            if (token.substring(apostropheIndex+1).length() <= 1) {
                token = token.substring(0, apostropheIndex);
            }
        }
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
