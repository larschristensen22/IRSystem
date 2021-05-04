import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
/**
 * IRSystem class to run methods of the projects. This is the main class. Includes a scanner to read 
 * in queries and runs inverted index when necessary.
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class IRSystem {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //The folder containing the training data
        String directory = "TrainingData/";
        //Scanning in user responses to quetions on stop words and their desired query
        System.out.println("Would you like stop words in your index?");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Please enter your query");
        String query = sc.nextLine();
        sc.close();
        //Creating objects for use in the IR System
        Parser parser = new Parser();
        ArrayList<Document> docs = new ArrayList<Document>();
        Tokenization tokens = new Tokenization();
        ArrayList<String> newTokens = new ArrayList<String>();
        InvertedIndex index = new InvertedIndex();
        ArrayList<String> testTokens =  tokens.tokenizeQuery(query);

        //Obtains a list of the files in the training data folder
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();

        //Loop through the list of files to parse their contents and create Document objects
        for (File file: filesList){
            //System.out.println("filename: "+ file.toString());
            parser.trecParser(file.toString());
        }
        docs = Parser.docs;
        //Storing a list of all Document IDs for use in potential NOT queries
        ArrayList<String> allDocs = new ArrayList<String>();
        for (int i = 0; i < docs.size(); i++) {
            allDocs.add(docs.get(i).getDocID());
            
        }
        System.out.println("SIZE: " + allDocs.size());

        //Loop through the document objects to tokenize and add tokens to the inverted index
        File tempFile = null;
        File createFile = null;
        boolean exists;
        //If the user indicated they wanted stop words or not
        if (answer.equalsIgnoreCase("no")) {
            tempFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "IndexRemoveStopWords.txt.txt");
            createFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "IndexRemoveStopWords.txt");
            exists = tempFile.exists();
        } else {
            tempFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "IndexWithStopWords.txt.txt");
            createFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "IndexWithStopWords.txt");
            exists = tempFile.exists();
        }
        //Prints if the inverted index file already exists
        System.out.println("Exists: " + exists);
        //If the index does not exist, create and serialize one
        if (!exists) {
            System.out.println("File does not exist");
            for (int i = 0; i < docs.size(); i++) {
                //tokensNoStop = tokens.tokenize(docs.get(i));
                if (answer.equalsIgnoreCase("no")) {
                    newTokens = tokens.tokenize(docs.get(i));
                } else {
                    newTokens = tokens.tokenizeWithStop(docs.get(i));
                }
                InvertedIndex.createIndex(newTokens, docs.get(i).getDocID());
            }
            index = InvertedIndex.index;
            System.out.println("Serializing...");

            index.serializeIndex(createFile.toString());
        } else {
            System.out.println("File already exists");
        }
        //If the user did not want stop words, deserialize without stop words. If they wanted stop words, use that.
        if (answer.equalsIgnoreCase("no")) {
            System.out.println("Deserializing index without stop words");
            index = InvertedIndex.deserializeIndex(createFile.toString());
        } else {
            System.out.println("Deserializing index with stop words");
            index = InvertedIndex.deserializeIndex(createFile.toString()); 
        }
        //Create posting lists for each document
        Document.createDocPostingList(index, docs); 
        System.out.println("TESTING DOC POSTING LIST" + docs.get(0).getPostingList().toString());
        //Run phrase search if there are quotes and boolean search if not
        BooleanSearch bs = new BooleanSearch(allDocs);
        if (query.charAt(0) == '"' && query.charAt(query.length() - 1) == '"') {
            System.out.println("Phrase Searching");
            bs.phraseSearch(testTokens, index);
        } else {
            System.out.println("Boolean Searching");
            bs.booleanSearch(testTokens, index);
        }
    }
}
