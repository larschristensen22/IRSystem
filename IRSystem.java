import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IRSystem {
    public static void main(String[] args) throws FileNotFoundException {
        String directory = "TrainingData/";
        Parser parser = new Parser();
        ArrayList<Document> docs = parser.trecParser(directory);
        Tokenization tokens = new Tokenization();
        ArrayList<String> newTokens = tokens.tokenize(docs);
        System.out.println(newTokens.get(2));
        //System.out.println(docs.get(0).getDocID());
        //System.out.println(docs.get(0).getText());

    }
}
