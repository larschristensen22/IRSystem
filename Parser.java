import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    
    ArrayList<Document> docs;

    public ArrayList<Document> trecParser() {
        docs = new ArrayList<Document>();
        File directoryPath = new File("");
        File filesList[] = directoryPath.listFiles();
        for (File file: filesList){
            Scanner sc = new Scanner(file); 
            String docID = "";
            String text = "";
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (!docID.equals("") && !text.equals("")) {
                    Document doc = new Document(docID, text);
                    docs.add(doc);
                    docID = "";
                    text = "";
                }

                else if (line.contains("<DOCNO>")){
                    docID += line.substring(line.indexOf(">"), line.indexOf("</"));
                }
                else if (line.contains("<TEXT>")) {
                    while (!sc.nextLine().equals("</TEXT>"))
                        text += " " + sc.nextLine();
                }
            }
        }
        
    }
}
