import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
/**
 * Write a description of class Normalization here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Normalization
{

    private HashSet<String> stopWords;

    public Normalization() throws FileNotFoundException, IOException
    {

        Path fileName = Path.of("StopWords.txt");

        String actual = Files.readString(fileName);

        String[] words = actual.split("\n");
        stopWords = new HashSet<String>();

        for (int i = 0; i < words.length; i++) {
            //System.out.print("WORD: " + words[i]);
            words[i] = words[i].trim();
            stopWords.add(words[i]);
        }

    }

    public boolean removeStopWords(String token){

        // for (int i = 0; i < tokens.size(); i++) {
            if (stopWords.contains(token)){
                // tokens.remove(i);
                // i--;
                return true;
            }

        // }
        //return tokens;
        return false;
    }
}
