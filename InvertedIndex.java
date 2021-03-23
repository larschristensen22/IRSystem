import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * InvertedIndex class to create the inverted index storing tokens and their posting lists.
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class InvertedIndex extends HashMap<String, PostingList> {
    
    private static InvertedIndex index = new InvertedIndex();
    
    /**
     * This method modifies the inverted index with new tokens from a new document
     * 
     * @return InvertedIndex - a hash map containing tokens and posting lists
     * @param ArrayList<String> tokens - the list of tokens to be added to the inverted index
     * @param String docNo - the document number
     */
    public static InvertedIndex createIndex(ArrayList<String> tokens, String docNo) {
        //Create a hashset to store the unique tokens from the document as there may be repeats
        HashSet<String> uniqueTokens = new HashSet<String>();
        Post post = new Post(docNo);
        for (int i = 0; i < tokens.size(); i++) {
            if (!uniqueTokens.contains(tokens.get(i))) {
                uniqueTokens.add(tokens.get(i));
            }
        }
        //Loop through the unique tokens to check if they are in index already
        for (String word: uniqueTokens) {
            //If not, add them to the index with a new posting list
            if (!index.containsKey(word)) {
                PostingList postList = new PostingList();
                index.put(word, postList);
                index.get(word).addPost(post);
            }
            //Else, add the docNo to the existing posting list
            else {
                index.get(word).addPost(post);
            }   
        }
        return index;
    }
}
