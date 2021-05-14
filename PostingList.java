import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;
/**
 * This class keeps a list of all the file posts in an arraylist and
 * keeps track of the frequency of posts.
 *
 * @author Cameron Costello and Lars Christensen
 * @version March 22, 2021
 */
public class PostingList implements Serializable
{

    private int frequency;
    private double idf;
    private double sumWeightedTf;
    private ArrayList<Post> posts;
    private static final int NUMBER_OF_DOCS = 19936;

    /**
     * Constructor for objects of class PostingList.
     */
    public PostingList() {
        this.frequency = 0;
        this.posts = new ArrayList<Post>();
        this.idf = 0;
        this.sumWeightedTf = 0.0;
    }

    /**
     * Adds a post to the document list
     * 
     * @param Post postToAdd - the post to add to the doc list
     * double idf - the idf used for the calculations in the method
     */
    public void addPost(Post postToAdd, int position, double idf) {

        boolean exists = false;

        for (int i = 0; i < this.posts.size(); i++) {
            if (this.posts.get(i).getDocID().equals(postToAdd.getDocID())) {
                //calcuates new idf
                double oldWeightedTf = this.posts.get(i).getWeightedTf();
                this.sumWeightedTf -= (oldWeightedTf * oldWeightedTf);

                exists = true;

                //calculates weight term frequency
                this.posts.get(i).addPosition(position);
                double weightedTf = Formulas.weightedTermFrequency(this.posts.get(i).getTermFreq(), idf);
                double weightedTfSquared = weightedTf * weightedTf;
                this.posts.get(i).setWeightedTf(weightedTf);
                this.sumWeightedTf += weightedTfSquared;

            } 
        }

        if (!exists) {

            postToAdd.addPosition(position);
            //calculates weight term frequency
            double weightedTf = Formulas.weightedTermFrequency(postToAdd.getTermFreq(), idf);
            double weightedTfSquared = weightedTf * weightedTf;
            postToAdd.setWeightedTf(weightedTf);
            this.sumWeightedTf += weightedTfSquared;
            this.posts.add(postToAdd);

        }

    }

    /**
     * Returns the IDF
     * 
     * @return double, the idf
     */
    public double getIdf() {
        return this.idf;    
    }

    /**
     * Returns sum of the weighted term frequencies
     * 
     * @return double, the sum of weighted term frequencies
     */
    public double getSumWeightedTf() {
        return this.sumWeightedTf;    
    }

    /**
     * Adds a post to the posting list in a sorted order.
     * 
     * @param postAdded is the post that is added to the list
     * @return Post that is added to the list
     */
    public Post addPost(String docNo) {

        int size = this.posts.size();
        int left = 0;
        int right = size - 1; 
        int midPoint = (left + right) / 2;
        Post postAdded = new Post(docNo);
        this.frequency += 1;

        //add post anywhere if size is 0
        if (size == 0) {
            this.posts.add(postAdded);
            return postAdded;
        }

        //compare this post docID to the current post and then add it in correct position
        if (size == 1) {

            if(docNo.compareTo(this.posts.get(midPoint).getDocID()) == 0) {
                return this.posts.get(midPoint);
            }
            if (postAdded.getDocID().compareTo(this.posts.get(midPoint).getDocID()) > 0) {
                this.posts.add(postAdded);
            }
            else {
                this.posts.add(0, postAdded);
            }
            //add this post to doc
            return postAdded;
        }

        //loop through all posts
        boolean isAdded = false;
        while (left <= right && !isAdded) {

            //add post if left is equal to right
            if (right == left) {

                //determine where to add the post
                if(posts.get(right).getDocID().compareTo(docNo) > 0) {
                    this.posts.add(left, postAdded);
                    calculateIdf();
                    //System.out.println("IDFIDFIDF: " + this.idf);
                    return postAdded;
                } else if (posts.get(right).getDocID().compareTo(docNo) < 0) {
                    this.posts.add(left+1, postAdded);
                    calculateIdf();
                    //System.out.println("IDFIDFIDF: " + this.idf);
                    return postAdded;
                } else {
                    return this.posts.get(right);
                }

            }
            else if (docNo.compareTo(this.posts.get(midPoint).getDocID()) > 0) {
                //move midpoint to the right if the docNo is larger than the docID
                left = midPoint + 1;
                if (left > right) {
                    left = right;
                }

            } else if ((docNo.compareTo(this.posts.get(midPoint).getDocID()) == 0)) {
                return this.posts.get(midPoint);
            }
            else {
                //move midpoint to the left if the docNo is smaller than the docID
                right = midPoint - 1;
                if (right < left) {
                    right = left;
                }

            }

            //change midpoint
            midPoint = (left + right) / 2;

        }
        //System.out.println("LEFT: " + left + " RIGHT: " + right + " MIDPOINT: " + midPoint);

        return null;

    }

    /**
     * Returns the list of all the posts
     * 
     * @return ArrayList<Post> the list containing all posts.
     */
    public ArrayList<Post> getPost() {
        return posts;
    }

    /**
     * Returns the list of all the posts
     * 
     * @return ArrayList<Post> the list containing all posts.
     */
    public Post getSinglePost() {
        return posts.get(frequency - 1);
    }

    /**
     * Method that converts the class into a String.
     * 
     * @return String that is made
     */
    @Override
    public String toString() {
        String result = " Frequency: " + this.frequency + " Posts: " + this.posts.toString();
        return result;
    }

    /**
     * Finds a post by its docID
     * 
     * @return Post that was found
     * @param docId that searches for a post
     */
    public Post findPostByDocId(String docId) {
        Post returnPost = null;

        //loop through posts to find a post matching docId
        for (int i = 0; i < this.posts.size(); i++) {
            if (docId.equals(this.posts.get(i).getDocID())) {
                returnPost = this.posts.get(i);
            }
        }

        return returnPost;
    }

    /**
     * Calculates idf for each posting list
     */
    public void calculateIdf() {
        int length = this.posts.size();
        this.idf = Math.log(NUMBER_OF_DOCS / length);
    }
}
