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

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<String> tokenize(ArrayList<Document> docs)
    {

        for (int i = 0; i < docs.size(); i++) {

            StringTokenizer tokenizer = new StringTokenizer(docs.get(i).getText());
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                tokens.add(token);
            }
        }
        return tokens;

    }
}
