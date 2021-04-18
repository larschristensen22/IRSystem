import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
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
        // ArrayList<String> newTokens = new ArrayList<String>();
        InvertedIndex index = new InvertedIndex();
        // ArrayList<String> docIDs = new ArrayList<String>();
    
        String testString = "money AND thing";
        ArrayList<String> testTokens =  tokens.tokenizeQuery(testString);
        // for (int i = 0; i < testTokens.size(); i++) {
        //     System.out.println(testTokens.get(i));
        // }
        //Obtains a list of the files in the training data folder
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();
        int fileNum = 0;
        
        //Loop through the list of files to parse their contents and create Document objects
        for (File file: filesList){
            System.out.println("filename: "+ file.toString());
            parser.trecParser(file.toString());
            fileNum++;
            System.out.println("File Num: " + fileNum);
        }
        docs = Parser.docs;
        ArrayList<String> allDocs = new ArrayList<String>();
        for (int i = 0; i < docs.size(); i++) {
            allDocs.add(docs.get(i).getDocID());
        }
        
        //Loop through the document objects to tokenize and add tokens to the inverted index
        // for (int i = 0; i < docs.size(); i++) {
        //     newTokens = tokens.tokenize(docs.get(i));
        //     //System.out.println("Doc: " + docs.get(i).getText());
        //     //System.out.println("Tokenizing completed for docNo: + " + i);
        //     //System.out.println("Normalization completed for docNo: + " + i);
            
        //     System.out.println("Index completed for docNo: " + docs.get(i).getDocID() + " " + i + "/"+ docs.size());
        //     InvertedIndex.createIndex(newTokens, docs.get(i).getDocID());
        // }
        // index = InvertedIndex.index;
        // index.serializeIndex("IndexRemoveStopWords");
        index = InvertedIndex.deserializeIndex("IndexRemoveStopWords");
        
        BooleanSearch bs = new BooleanSearch(allDocs);
        bs.booleanSearch(testTokens, index);
        
    }
}
