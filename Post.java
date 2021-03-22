
/**
 * This class creates Post objects
 *
 * @author Cameron Costello and Lars Christensen
 * @version March 22, 2021
 */
public class Post{

    private String docID;
    private int docFrequency;

    /**
     * Constructor for objects of class Post.
     */
    public Post (String docID) {
        this.docID = docID;
        this.docFrequency = 1;
    }

    /**
     * Method that converts the class into a String.
     * 
     * @return String that is made
     */
    public String toString() {
        String result = "DOC ID: " + this.docID;
        return result; 
    }
}
