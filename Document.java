/**
 * Document class to store parsed data into objects.
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class Document {
    //Instance variables of data to be stored
    private String docID;
    private String text;
    private String author;
    private String publisher;
    private String description;
    private String date;
    /**
     * Constructor for objects of class Document. Stores the docID and text of a document.
     */
    public Document (String docID, String text) {
        this.docID = docID;
        this.text = text;
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
     * This method returns the text of a document.
     * 
     * @return String - the text of a document
     */
    public String getText() {
        return this.text;
    }
}