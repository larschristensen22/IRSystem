import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    static ArrayList<Document> docs;

    public Parser() {
        docs = new ArrayList<Document>();
    }

    public ArrayList<Document> trecParser(String directory) throws FileNotFoundException {
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();
        String line;
        for (File file: filesList){
            //System.out.println(file);
            Scanner sc = new Scanner(file); 
            String docID = "";
            String text = "";
            boolean textTag = false;

            while (sc.hasNextLine()) {
                line = sc.nextLine();

                if (!docID.equals("") && !text.equals("") && !textTag) {
                    Document doc = new Document(docID, text);
                    docs.add(doc);
                    docID = "";
                    text = "";
                }

                else if (line.contains("<DOCNO>")){
                    docID += line.substring(line.indexOf(">") + 1, line.indexOf("</"));
                }
                else if (line.contains("<TEXT>")) {
                    textTag = true;
                }
                else if (line.contains("</TEXT>")) {
                    textTag = false;
                }
                else if (textTag) {
                    text += line + " ";
                }
                
            }
            sc.close();
        }

        return docs;

    }
}
