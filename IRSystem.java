import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IRSystem {
    public static void main(String[] args) throws FileNotFoundException {
        String directory = "TrainingData/";
        ArrayList<Document> docs = new ArrayList<Document>();
        docs = Parser.trecParser(directory);
        System.out.println(docs.get(0).getDocID());
        System.out.println(docs.get(0).getText());

    }
}
