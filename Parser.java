import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
public class Parser {

    private Document doc;
    private ArrayList<Document> docs;

    public Parser() {
        docs = new ArrayList<Document>();
    }

    public ArrayList<Document> trecParser(String directory) throws FileNotFoundException, IOException {
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();

        Path fileName = Path.of(directory);
        String actual = Files.readString(fileName);

        String[] words = actual.split("\n");

        String docID = "";
        String text = "";
        boolean textTag = false;

        for (int i = 0; i < words.length; i++) {

            if (!docID.equals("") && !text.equals("") && !textTag) {
                doc = new Document(docID, text);
                docs.add(doc);
                docID = "";
                text = "";
            }

            else if (words[i].contains("<DOCNO>")){
                docID += words[i].substring(words[i].indexOf(">") + 1, words[i].indexOf("</"));
            }
            else if (words[i].contains("<TEXT>")) {
                textTag = true;
            }
            else if (words[i].contains("</TEXT>")) {
                textTag = false;
            }
            else if (textTag) {
                text += words[i] + " ";
            }

        }

        return docs;
    }
}
