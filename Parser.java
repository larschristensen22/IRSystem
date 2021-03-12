import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    
    static ArrayList<Document> docs;

    public static ArrayList<Document> trecParser(String directory) throws FileNotFoundException {
        docs = new ArrayList<Document>();
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();
        String line;
        for (File file: filesList){
            System.out.println(file);
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
                    while (!sc.nextLine().contains("<"))
                        text += " " + sc.nextLine();
                }
            }
            sc.close();
        }

        return docs;
        
    }
}
