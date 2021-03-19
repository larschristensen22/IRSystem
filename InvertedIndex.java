import java.util.HashMap;
import java.util.ArrayList;

public class InvertedIndex extends HashMap<String, PostingList>{
    
    public InvertedIndex () {
        
    }

    public InvertedIndex createIndex(ArrayList<String> tokens, String docNo) {
        InvertedIndex index = new InvertedIndex();
        Post post = new Post(docNo);
        for (int i = 0; i < tokens.size(); i++) {
            if (!index.containsKey(tokens.get(i))) {
                PostingList postList = new PostingList();
                index.put(tokens.get(i), postList);
                index.get(tokens.get(i)).addPost(post);

            }
            else {
                index.get(tokens.get(i)).addPost(post);
            }
        }
        
        return index;
    }
}
