import java.util.HashMap;
import java.util.ArrayList;

public class InvertedIndex extends HashMap<String, PostingList>{
    private static InvertedIndex index = new InvertedIndex();
    
    public InvertedIndex () {

    }

    public static InvertedIndex createIndex(ArrayList<String> tokens, String docNo) {

        Post post = new Post(docNo);
        for (int i = 0; i < tokens.size(); i++) {
            if (!index.containsKey(tokens.get(i))) {
                PostingList postList = new PostingList();
                index.put(tokens.get(i), postList);
                index.get(tokens.get(i)).addPost(post);

            }
            else {
                // if(!index.get(tokens.get(i)).getPost().toString().contains(docNo)) {
                    // index.get(tokens.get(i)).addPost(post);
                // }

            }
        }
    
        return index;
    }
}
