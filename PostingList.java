import java.util.ArrayList;
/**
 * Write a description of class PostingList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PostingList
{
    private int frequency;
    
    private ArrayList<Post> posts;
    
    public PostingList() {
        this.frequency = 0;
        this.posts = new ArrayList<Post>();
    }
    
    public void addPost(Post postAdded) {
        this.posts.add(postAdded);
        this.frequency += 1;
    }

    public String toString() {
        String result = "FREQUENCY: " + this.frequency + " POSTS: " + posts.toString().toString();
        return result;
    }
}
