import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IRSystem {
    public static void main(String[] args) throws FileNotFoundException {
        String directory = "TrainingData/";
        Parser parser = new Parser();
        ArrayList<Document> docs = parser.trecParser(directory);
        Tokenization tokens = new Tokenization();
        ArrayList<String> newTokens = tokens.tokenize(docs);
        Normalization normalize = new Normalization();
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            test.add(newTokens.get(i));
        }
        // ArrayList<String> test = new ArrayList<String>();
        // for (int i = 0; i < 20; i++) {
        //     test.add("the");
        // }
        test = normalize.removeStopWords(test);
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
        }
        //System.out.println("TEST WORD: " + tokens.removePunctuation("You're"));
        //System.out.println(docs.get(0).getDocID());
        //System.out.println(docs.get(0).getText());

    }
}
