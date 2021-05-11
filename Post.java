import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class creates Post objects
 *
 * @author Cameron Costello and Lars Christensen
 * @version March 22, 2021
 */
public class Post implements Serializable{

    private String docID;
    private ArrayList<Integer> positions;
    private int termFrequency;
    private double weightedTermFreq;
    private double normalizedWeight;

    /**
     * Constructor for objects of class Post.
     */
    public Post (String docID) {
        this.docID = docID;
        this.termFrequency = 0;
        this.weightedTermFreq = 0.0;
        this.normalizedWeight = 0.0;
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
        this.termFrequency++;
    }

    public String getDocID() {
        return this.docID;
    }

    public void setDocID(String value) {
        this.docID = value;
    }

    public ArrayList<Integer> getPositions() {
        return this.positions;
    }

    public int getTermFreq() {
        return this.termFrequency;
    }

    public void setWeightedTf(double weightedTermFreq) {
        this.weightedTermFreq = weightedTermFreq;
    }

    public double getWeightedTf() {
        return this.weightedTermFreq;
    }

    public void setNormalizedWeight(double normalizedWeight) {
        this.normalizedWeight = normalizedWeight;
    }
}
