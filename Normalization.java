import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class Normalization here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Normalization
{

    public Normalization()
    {
        
    }

    public ArrayList<String> removeStopWords(ArrayList<String> tokens) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("StopWords.txt"));
        ArrayList<String> stopWords = new ArrayList<String>();
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            stopWords.add(word);
            System.out.println("STOP WORD: " + word);
        }
        sc.close();
        

        for (int i = 0; i < tokens.size(); i++) {
            for (int j = 0; j < stopWords.size(); j++) {
                if(tokens.get(i).equals(stopWords.get(j))) {
                    tokens.set(i, "");
                }
            }
        }
        return tokens;
    }
}
