import java.io.Serializable;
/**
 * Document class to store parsed data into objects.
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class Document implements Serializable {
    //Instance variables of data to be stored
    private String docID;
    private String text;
    private String author;
    private String publisher;
    private String description;
    private String date;
    private PostingList postingList;
    private double l2Norm;
    /**
     * Constructor for objects of class Document. Stores the docID and text of a document.
     */
    public Document (String docID, String text) {
        this.docID = docID;
        this.text = text;
        this.postingList = new PostingList();
        this.l2Norm = 0.0;
    }
    /**
     * Constructor for objects of class Document. Stores all data of a document.
     */
    public Document(String docID, String text, String author, String date, String publisher, String description) {
        this.docID = docID;
        this.text = text;
        this.author = author;
        this.date = date;
        this.publisher = publisher;
        this.description = description;
        this.l2Norm = 0.0;
    }
    
    /**
     * This method returns the docID of a document.
     * 
     * @return String - the docID of a document
     */
    public String getDocID() {
        return this.docID;
    }
    
    /**
     * Assign the l2Norm value of a Document
     * 
     * @param l2Norm the l2Norm value to set the instance variable
     */
    public void setl2Norm(double l2Norm) {
        this.l2Norm = l2Norm;    
    }
    
    /**
     * Return the l2Norm of a Document
     * 
     * @return double - the l2Norm of a document
     */
    public double getl2Norm() {
        return this.l2Norm; 
    }
    
    /**
     * This method returns the text of a document.
     * 
     * @return String - the text of a document
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns the posting list of a document.
     * 
     * @return PostingList - the posting list of a document
     */
    public PostingList getPostingList() {
        return this.postingList;
    }
    
    /**
     * Add a post object to the posting list of a document
     * 
     * @param postToAdd - the post to be added
     * @param position - the position of the term in the document
     * @param idf - the idf score of the term
     */
    public void addPost(Post postToAdd, int position, double idf) {
        this.postingList.addPost(postToAdd, position, idf);
    }
    
    /**
     * Returns a formatted string of the Document and its posting list.
     * 
     * @return String - the string summary
     */
    public String toString() {
        String retString = "DocID: " + this.docID + ", ";    
        System.out.println("PostingList size: " + postingList.getPost().size());
        for (int i = 0; i < postingList.getPost().size(); i++) {
                retString += postingList.getPost().get(i).toString() + " ";
        }
        return retString;
    }

    /**
     * Compute the l2Norm of the Document and the normalized weights for each term.
     */
    public void computeStats() {
        this.l2Norm = Formulas.l2Norm(this.postingList.getSumWeightedTf());
        for (int i = 0; i < this.postingList.getPost().size(); i++) {
            double weightedTf = this.postingList.getPost().get(i).getWeightedTf();
            this.postingList.getPost().get(i).setNormalizedWeight(Formulas.normalizedWeight(weightedTf, this.l2Norm));
        } 
    }
}