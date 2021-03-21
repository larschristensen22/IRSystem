import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class InvertedIndex extends HashMap<String, PostingList>{
    private static InvertedIndex index = new InvertedIndex();
    
    public InvertedIndex () {

    }

    public static InvertedIndex createIndex(ArrayList<String> tokens, String docNo) {
        HashSet<String> uniqueTokens = new HashSet<String>();
        Post post = new Post(docNo);
        for (int i = 0; i < tokens.size(); i++) {
            if (!uniqueTokens.contains(tokens.get(i))) {
                uniqueTokens.add(tokens.get(i));
            }
        }
        for (String word: uniqueTokens) {
            if (!index.containsKey(word)) {
                PostingList postList = new PostingList();
                index.put(word, postList);
                index.get(word).addPost(post);
            }
            else {
                index.get(word).addPost(post);
            }   
        }
        return index;
    }
}
