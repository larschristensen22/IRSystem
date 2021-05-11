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
    public static void createIndex(ArrayList<String> tokens, String docNo, Document doc) throws IOException {

        int position = 0;

        //Loop through the unique tokens to check if they are in index already
        for (String word: tokens) {
            //If not, add them to the index with a new posting list
            Post post = null;
            //if word is not in index, add it
            if (!index.containsKey(word)) {
                PostingList postList = new PostingList();
                index.put(word, postList);
                //initialize the post
                post = index.get(word).addPost(docNo);

            }
            else {
                //otherwise get the post
                post = index.get(word).addPost(docNo); 
            }

            double idf = index.get(word).getIdf();
            Post newPost = new Post(word);
            doc.addPost(newPost, position, idf);

            //add position to the post based on the word
            post.addPosition(position);

            position++;
        }

    }

    /**
     * Write the index to an output file
     * 
     * @throws IOException
     */
    public void writeIndexToFile() throws IOException {

        //name of the index file
        String outputFileName = "indexOutput.txt";
        File file = new File(outputFileName);
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));

        //write the index to the index output file
        for (String name: index.keySet()){
            String key = name;
            String value = index.get(name).toString();  
            bf.write(key + ":" + value + "\n\n"); 
        } 

        bf.close();
    }

    /**
     * Serialize the index given in the path
     * 
     * @param path will serialize the index at this filepath
     */
    public void serializeIndex(String path) {
        try {
            FileOutputStream fout=new FileOutputStream(path);  
            ObjectOutputStream out=new ObjectOutputStream(fout);  
            out.writeObject(index);  
            out.flush();  
            //closing the stream  
            out.close();  
            //prints when the index is successfully written to output
            System.out.println("Wrote index successfully!");  
        } catch(Exception e) {
            System.out.println(e);
        }  
    }

    /**
     * Deserialize the index given in the path
     * 
     * @param path the path of the index that will be deserialized
     * @return return the index that was deserialized
     */
    public static InvertedIndex deserializeIndex(String path) {
        InvertedIndex newIndex;
        try{  
            //Creating stream to read the object  
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(path));  
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
