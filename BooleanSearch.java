import java.util.ArrayList;
import java.util.HashMap;
/**
 * Write a description of class BooleanSearch here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BooleanSearch
{

    /**
     * Constructor for objects of class BooleanSearch
     */
    public BooleanSearch()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void phraseSearch(ArrayList<String> query)
    {
        // put your code here

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void booleanSearch(ArrayList<String> query, InvertedIndex indexIn)
    {

        ArrayList<PostingList> postings = new ArrayList<PostingList>();
        HashMap<String, ArrayList<String>> docId = new HashMap<String, ArrayList<String>>();

        for (int i = 0; i < query.size(); i++) {
            postings.add(indexIn.get(query.get(i)));
            docId.put(query.get(i), new ArrayList<String>());
        }

        for (int i = 0; i < postings.size(); i++) {

            for (int x = 0; x < postings.get(i).getPost().size(); x++) {
                docId.get(i).add(postings.get(i).getPost().get(x).getDocID());
            }

        }

        String wordOne = "";
        String wordTwo = "";

        int j = 0;
        int k = 0;
        while(j < query.size()) {
            if (query.get(j).equals("AND")) {
              //  intersect(postings.get(j), , 0);
            }
            else if( query.get(j).equals("NOT")) {

            } else if (query.get(j).equals("OR")) {
            } else {
            }
        }

    }

    public ArrayList<String> intersect(PostingList listOne, PostingList listTwo, int booleanOp) {
        ArrayList<String> intersection = new ArrayList<String>();
        int countOne = 0;
        int countTwo = 0;

        while (countOne < listOne.getPost().size() && countTwo < listTwo.getPost().size()) {

            if (booleanOp == 0) {//AND
                if (listOne.getPost().get(countOne).getDocID().equals(listTwo.getPost().get(countTwo).getDocID())) {
                    intersection.add(listOne.getPost().get(countOne).getDocID());
                    countTwo++;
                    countOne++;
                } else if (listOne.getPost().get(countOne).getDocID().compareTo(listTwo.getPost().get(countTwo).getDocID()) > 0) {
                    countTwo++;
                } else {
                    countOne++;    
                }
            } else if (booleanOp == 1) {//OR
                if (!intersection.contains(listOne.getPost().get(countOne).getDocID())) {
                    intersection.add(listOne.getPost().get(countOne).getDocID());

                } 
                if (!intersection.contains(listTwo.getPost().get(countTwo).getDocID())) {
                    intersection.add(listTwo.getPost().get(countTwo).getDocID());

                } 
                countOne++;
                countTwo++;
            } else if (booleanOp == 2) {//NOT
                if (!listTwo.getPost().get(countTwo).getDocID().contains(listOne.getPost().get(countOne).getDocID())) {
                    intersection.add(listTwo.getPost().get(countTwo).getDocID());
                    countOne++;
                    countTwo++;
                }
            }

        }
        return intersection;
    }
}
