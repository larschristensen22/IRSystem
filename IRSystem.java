import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

public class IRSystem {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String directory = "TrainingData/";

        Parser parser = new Parser();
        ArrayList<Document> docs = new ArrayList<Document>();

        Tokenization tokens = new Tokenization();
        ArrayList<String> newTokens = new ArrayList<String>();
        //Normalization normalize = new Normalization();
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();
        InvertedIndex index = new InvertedIndex();

        int fileNum = 0;
        for (File file: filesList){
            System.out.println("filename: "+ file.toString());
            docs = parser.trecParser(file.toString());
            fileNum++;
            System.out.println("File Num: " + fileNum);
            
            // for (int i = 0; i < docs.size(); i++) {
                // newTokens = tokens.tokenize(docs.get(i));
                // newTokens = normalize.removeStopWords(newTokens);
                // index = InvertedIndex.createIndex(newTokens, docs.get(i).getDocID());
            // }

        }

        System.out.println("Number of Docs: " + docs.size());
        for (int i = 0; i < 100; i++) {
            //System.out.println("text: " + docs.get(i).getText());
            newTokens = tokens.tokenize(docs.get(i));
            System.out.println("Tokenizing completed for docNo: + " + i);
            //newTokens = normalize.removeStopWords(newTokens);
            System.out.println("Normalization completed for docNo: + " + i);
            index = InvertedIndex.createIndex(newTokens, docs.get(i).getDocID());
            System.out.println("Index completed for docNo: + " + i);
        }

        for (String name: index.keySet()){
            String key = name;
            String value = index.get(name).toString();  
            System.out.println(key + " " + value.toString());      
        } 
        System.out.println("HASH MAP SIZE: " + index.size());

    }
}
