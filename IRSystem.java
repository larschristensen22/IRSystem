import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

public class IRSystem {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String directory = "TrainingData/";

        Parser parser = new Parser();
        ArrayList<Document> docs = new ArrayList<Document>();
        Document doc;

        Tokenization tokens = new Tokenization();
        ArrayList<String> newTokens;
        Normalization normalize = new Normalization();
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();

        for (File file: filesList){
            System.out.println("filename: "+ file.toString());
            docs = parser.trecParser(file.toString());
            for (int i = 0; i < docs.size(); i++) {
                newTokens = tokens.tokenize(docs.get(i));
                newTokens = normalize.removeStopWords(newTokens);
            }

        }


    }
}
