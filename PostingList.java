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
        this.posts.add(postAdded);
        this.frequency += 1;
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
        String result = "Frequency: " + this.frequency + " Posts: " + this.posts.toString().toString();
        return result;
    }
}
