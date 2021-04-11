import java.util.ArrayList;
/**
 * This class keeps a list of all the file posts in an arraylist and
 * keeps track of the frequency of posts.
 *
 * @author Cameron Costello and Lars Christensen
 * @version March 22, 2021
 */
public class PostingList 
{

    private int frequency;
    private ArrayList<Post> posts;

    /**
     * Constructor for objects of class PostingList.
     */
    public PostingList() {
        this.frequency = 0;
        this.posts = new ArrayList<Post>();
    }

    /**
     * Adds a post to the posting list.
     * 
     * @param postAdded is the post that is added to the list
     */
    public void addPost(Post postAdded) {
        //this.posts.add(postAdded);
        int size = this.posts.size();
        //int midPoint = (size / 2) - 1;
        int left = 0;
        int right = size - 1; 
        int midPoint = (left + right) / 2;
        this.frequency += 1;
        //System.out.println("LEFT: " + left + " MID: " + midPoint + " RIGHT: " + right);
        if (size == 0) {
            this.posts.add(postAdded);
            System.out.println("INITIAL POST ADDED");
        }
        if (size == 1) {
            if (postAdded.getDocID().compareTo(this.posts.get(midPoint).getDocID()) > 0) {
                this.posts.add(postAdded);
                System.out.println("POST ADDED WITH SIZE 1");
            }
            else {
                this.posts.add(1, postAdded);
                System.out.println("POST ADDED WITH SIZE 1");
            }
        }
        boolean isAdded = false;
        while (left <= right && !isAdded) {
            System.out.println("LEFT: " + left + " MID: " + midPoint + " RIGHT: " + right);
            
            if (right == left) {
                this.posts.add(left, postAdded);
                isAdded = true;
                System.out.println("POST ADDED BY BINARY SEARCH");
                
            }
            else if (postAdded.getDocID().compareTo(this.posts.get(midPoint).getDocID()) > 0) {
                left = midPoint + 1;
                //midPoint = (left + right) / 2;
                
                System.out.println("POST GREATER THAN MIDPOINT");
                System.out.println("NEW POST: " + postAdded.getDocID() + " VS: " + this.posts.get(midPoint).getDocID());
            }
            else {
                right = midPoint - 1;
                //midPoint = (left + midPoint) / 2;
                
                System.out.println("POST LESS THAN MIDPOINT");
                System.out.println("NEW POST: " + postAdded.getDocID() + " VS: " + this.posts.get(midPoint).getDocID());
            }
            midPoint = (left + right) / 2;
        }
        
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
}
