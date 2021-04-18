import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * InvertedIndex class to create the inverted index storing tokens and their posting lists.
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class InvertedIndex extends HashMap<String, PostingList> {

    public static InvertedIndex index = new InvertedIndex();
    public static ArrayList<String> docIDs = new ArrayList<String>();

    /**
     * This method modifies the inverted index with new tokens from a new document
     * 
     * @return InvertedIndex - a hash map containing tokens and posting lists
     * @param ArrayList<String> tokens - the list of tokens to be added to the inverted index
     * @param String docNo - the document number
     * @throws IOException
     */
    public static void createIndex(ArrayList<String> tokens, String docNo) throws IOException {
        //Create a hashset to store the unique tokens from the document as there may be repeats
        // HashSet<String> newTokens = new HashSet<String>();

        // for (int i = 0; i < tokens.size(); i++) {
        // // if (!uniqueTokens.contains(tokens.get(i))) {
        // // uniqueTokens.add(tokens.get(i));
        // // }
        // }
        int position = 0;
        //Loop through the unique tokens to check if they are in index already
        for (String word: tokens) {
            //If not, add them to the index with a new posting list
            Post post = null;

            //new Post(docNo);
            if (!index.containsKey(word)) {
                PostingList postList = new PostingList();
                //post = new Post(docNo);
                index.put(word, postList);
                post = index.get(word).addPost(docNo);

            }
            else {
                //if (!index.get(word).getSinglePost().getDocID().equals(docNo)) {
                post = index.get(word).addPost(docNo); // return post being added - instead of adding //pass doc Num into addPast

                //add position to post
                //binary search to get post
                //}

                
            }
            
            post.addPosition(position);
            //System.out.println("POST: " + post.toString());
            //index.get(word).getSinglePost().addPosition(position);

            //System.out.println("WORD: " + word + " AT POSITION: " + position);
            //System.out.println("INDEX ARRAYLIST: " + index.get(word).getSinglePost().toString());
            position++;
        }


    }

    public void writeIndexToFile() throws IOException {
        String outputFileName = "indexOutput.txt";
        File file = new File(outputFileName);
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));

        for (String name: index.keySet()){
            String key = name;
            String value = index.get(name).toString();  
            bf.write(key + ":" + value + "\n\n");
            //System.out.println(key + " " + value.toString());      
        } 

        bf.close();
    }

    public void serializeIndex(String path) {
        try {
            FileOutputStream fout=new FileOutputStream(path + ".txt");  
            ObjectOutputStream out=new ObjectOutputStream(fout);  
            out.writeObject(index);  
            out.flush();  
            //closing the stream  
            out.close();  
            System.out.println("Wrote index successfully!");  
        } catch(Exception e) {
            System.out.println(e);
        }  
    }

    public static InvertedIndex deserializeIndex(String path) {
        InvertedIndex newIndex;
        try{  
            //Creating stream to read the object  
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(path + ".txt"));  
            newIndex = (InvertedIndex)in.readObject();  
            //printing the data of the serialized object  
            System.out.println("Read index successfully!");  
            //closing the stream  
            in.close();  
            } catch(Exception e) {
                System.out.println(e);
                newIndex = null;
            }  
        return newIndex;
    }
}
