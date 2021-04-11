import java.util.ArrayList;
/**
 * This class creates Post objects
 *
 * @author Cameron Costello and Lars Christensen
 * @version March 22, 2021
 */
public class Post{

    private String docID;
    private ArrayList<Integer> positions;
    private int docFrequency;

    /**
     * Constructor for objects of class Post.
     */
    public Post (String docID) {
        this.docID = docID;
        this.docFrequency = 0;
        this.positions = new ArrayList<Integer>();
    }

    /**
     * Method that converts the class into a String.
     * 
     * @return String that is made
     */
    public String toString() {
        String result = "Doc ID: " + this.docID + ", Position: " + positions.toString();
        return result; 
    }
    
    public void addPosition(int position) {
        this.positions.add(position);
        this.docFrequency++;
    }

    public String getDocID() {
        return this.docID;
    }
}
