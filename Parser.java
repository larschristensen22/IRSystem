import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.*;

/**
 * This class is used to parse the input files into a specific format
 * and create a Document list out of them.
 *
 * @author Cameron Costello and Lars Christensen
 * @version March 22, 2021
 */
public class Parser {

    private Document doc;
    public static ArrayList<Document> docs;
    public static ArrayList<String> queries;
    public static ArrayList<String> docNums;

    /**
     * Constructor for objects of class Parser.
     */
    public Parser() {
        docs = new ArrayList<Document>();
        queries = new ArrayList<String>();
        docNums = new ArrayList<String>();
    }

    /**
     * This method creates the format in which the files will be parsed into
     * 
     * @return ArrayList<Document> a list of newly parsed documents
     * @param String directory the directory to the file that will be parsed
     */
    public void batchParser(File trecQueries) throws FileNotFoundException, IOException {

        // parseQueryFile(trecQueries);
        Scanner sc = new Scanner(trecQueries);
        String query = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            //System.out.println("LINE: " + line);
            String regex = "[0-9]+";

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            //System.out.println("MATCHES: " + m.matches());
            if (line.equals("<DOCNO>")) {
                //sc.nextLine();
                //System.out.println("IN IF STATEMENT");
                queries.add(query); 
                query = "";
            } else if (line.equals("</DOCNO>")) {
                //sc.nextLine();
            } else if (m.matches()) {
                //System.out.println("MATCH: " + line);
                docNums.add(line);
                sc.nextLine();
            } else if(!line.equals("")) {
                // System.out.println("Adding: " + line);
                if (line.charAt(line.length() - 1) == ' ') {
                    query += line;
                } else {
                    query += line + " ";   
                }
                //System.out.println("CREATING QUERY: " + query);
                //queries.add(line);    
                //sc.nextLine();
            }
        }
        //System.out.println("FINAL QUERY: " + query);
        queries.add(query);
        sc.close();
       
    }
    /**
     * This method creates the format in which the files will be parsed into
     * 
     * @return ArrayList<Document> a list of newly parsed documents
     * @param String directory the directory to the file that will be parsed
     */
    public void trecParser(String directory) throws FileNotFoundException, IOException {

        //create the filepath
        File directoryPath = new File(directory);
        File filesList[] = directoryPath.listFiles();

        Path fileName = Path.of(directory);
        String actual = Files.readString(fileName);

        //split the file on new lines
        String[] words = actual.split("\n");

        //create initial instances of variables to be parsed
        String docID = "";
        String text = "";
        String title = "";
        String publisher = "";
        String description = "";
        String date = "";
        String author = "";
        boolean textTag = false;
        boolean descTag = false;

        //loop through new lines to parse each line
        for (int i = 0; i < words.length; i++) {

            //check if a line needs to be parsed in a specific way
            //create Document objects from the parsingr
            if (!docID.equals("") && !text.equals("") && !textTag) {
                doc = new Document(docID, text);
                docs.add(doc);
                docID = "";
                text = "";
            }
            else if (words[i].contains("<BYLINE>")) {
                author +=  words[i + 1].trim();
            }
            else if (words[i].contains("<DATE>")) {
                date +=  words[i].substring(words[i].indexOf(">") + 1);
            } 
            else if (words[i].contains("<PUB>")) {
                publisher +=  words[i].substring(words[i].indexOf(">") + 1);
            }
            else if (words[i].contains("<HEADLINE>")) {
                descTag = true;
            }
            else if (words[i].contains("</HEADLINE>")) {
                descTag = false;
            }
            else if (descTag) {
                description += words[i] + " ";
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
                text += words[i].substring(0, words[i].length() - 1) + " ";
            }

        }

    }
}
