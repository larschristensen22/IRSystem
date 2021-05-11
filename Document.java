import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    public void setl2Norm(double l2Norm) {
        this.l2Norm = l2Norm;    
    }
    
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

    public PostingList getPostingList() {
        return this.postingList;
    }

    public static void createDocPostingList(InvertedIndex index, ArrayList<Document> docs) {
        for (String token: index.keySet()) {
            PostingList postList = index.get(token);
            for (int i = 0; i < postList.getPost().size(); i++) {
                for (int j = 0; j < docs.size(); j++) {
                    if (postList.getPost().get(i).getDocID().equals(docs.get(j).getDocID())) {
                        Post postToAdd = postList.getPost().get(i);
                        postToAdd.setDocID(token);
                        docs.get(j).postingList.getPost().add(postToAdd);
                    }
                }
            }
        } 
    }
    //create method add post
    //pass post in as parameter
    public void addPost(Post postToAdd, int position, double idf) {
            this.postingList.addPost(postToAdd, position, idf, getl2Norm());
            this.l2Norm = Formulas.l2Norm(this.postingList.getSumWeightedTf());
    }
    
    public String toString() {
        String retString = "DocID: " + this.docID + ", ";    
        System.out.println("PostingList size: " + postingList.getPost().size());
        for (int i = 0; i < postingList.getPost().size(); i++) {
                retString += postingList.getPost().get(i).toString() + " ";
        }
        return retString;
    }
}