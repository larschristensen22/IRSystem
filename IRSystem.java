import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
/**
 * IRSystem class to run methods of the projects. This is the main class.
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class IRSystem {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //The folder containing the training data
        String directory = "TrainingData/";
        //Creating objects for use in the IR System
        Parser parser = new Parser();
        ArrayList<Document> docs = new ArrayList<Document>();
        Tokenization tokens = new Tokenization();
        ArrayList<String> newTokens = new ArrayList<String>();
        InvertedIndex index = new InvertedIndex();
        //Obtains a list of the files in the training data folder
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();
        int fileNum = 0;
        //Loop through the list of files to parse their contents and create Document objects
        for (File file: filesList){
            System.out.println("filename: "+ file.toString());
            docs = parser.trecParser(file.toString());
            fileNum++;
            System.out.println("File Num: " + fileNum);
        }
        System.out.println("Number of Docs: " + docs.size());
        //Loop through the document objects to tokenize and add tokens to the inverted index
        for (int i = 0; i < docs.size(); i++) {
            newTokens = tokens.tokenize(docs.get(i));
            System.out.println("Tokenizing completed for docNo: + " + i);
            System.out.println("Normalization completed for docNo: + " + i);
            index = InvertedIndex.createIndex(newTokens, docs.get(i).getDocID());
            System.out.println("Index completed for docNo: + " + i);
        }
        //Loop through the hash map to print its contents
        for (String name: index.keySet()){
            String key = name;
            String value = index.get(name).toString();  
            System.out.println(key + " " + value.toString());      
        } 
        System.out.println("HASH MAP SIZE: " + index.size());
    }
}
