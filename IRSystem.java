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
        Normalization normalize = new Normalization();
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();
        InvertedIndex index = new InvertedIndex();


        for (File file: filesList){
            System.out.println("filename: "+ file.toString());
            docs = parser.trecParser(file.toString());
            for (int i = 0; i < 1; i++) {
                newTokens = tokens.tokenize(docs.get(i));
                newTokens = normalize.removeStopWords(newTokens);
                index = index.createIndex(newTokens, docs.get(i).getDocID());
            }

        }

        // for (int i = 0; i < newTokens.size(); i++) {
        //     System.out.println("TOKEN: " + newTokens.get(i));
        // }

        for (String name: index.keySet()){
            String key = name;
            String value = index.get(name).toString();  
            System.out.println(key + " " + value.toString());      
        } 
        System.out.println("HASH MAP SIZE: " + index.size());

    }
}
