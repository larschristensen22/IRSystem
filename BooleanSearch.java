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
    ArrayList<String> allDocs;
    /**
     * Constructor for objects of class BooleanSearch
     */
    public BooleanSearch(ArrayList<String> allDocs)
    {
        this.allDocs = allDocs;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void phraseSearch(ArrayList<String> query)
    {
        

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void booleanSearch(ArrayList<String> query, InvertedIndex indexIn)
    {
        int booleanOp = 0;
        ArrayList<PostingList> postings1 = new ArrayList<PostingList>();
        ArrayList<PostingList> postings2 = new ArrayList<PostingList>();
        ArrayList<String> docId1 = new ArrayList<String>();
        ArrayList<String> docId2 = new ArrayList<String>();

        if (query.contains("AND")) {
            postings1.add(indexIn.get(query.get(0)));
            postings2.add(indexIn.get(query.get(2)));
            booleanOp = 0;
        }
        else if (query.contains("OR")) {
            postings1.add(indexIn.get(query.get(0)));
            postings2.add(indexIn.get(query.get(2)));
            booleanOp = 1;
        }
        else if (query.contains("NOT")) {
            postings1.add(indexIn.get(query.get(1)));
            booleanOp = 2;
        }

        if (!query.contains("NOT")) {
            for (int i = 0; i < postings1.size(); i++) {
                for (int j = 0; j < postings1.get(i).getPost().size(); j++) {
                    docId1.add(postings1.get(i).getPost().get(j).getDocID());
                }
            }
            for (int i = 0; i < postings2.size(); i++) {
                for (int j = 0; j < postings2.get(i).getPost().size(); j++) {
                    docId2.add(postings2.get(i).getPost().get(j).getDocID());
                }
            }
        }
        else {
            for (int i = 0; i < postings1.size(); i++) {
                for (int j = 0; j < postings1.get(i).getPost().size(); j++) {
                    docId1.add(postings1.get(i).getPost().get(j).getDocID());
                }
            }
            docId2 = allDocs;
        }
        ArrayList<String> result = BooleanSearch.intersect(docId1, docId2, booleanOp);
        for (int i = 0; i < result.size(); i++) {
            System.out.println("0 1 " + result.get(i) + " " + (i+1) + " 1.0 " + "LarsAndCam");
        }
        
    }

    public static ArrayList<String> intersect(ArrayList<String> listOne, ArrayList<String> listTwo, int booleanOp) {
        ArrayList<String> intersection = new ArrayList<String>();
        int countOne = 0;
        int countTwo = 0;

        while (countOne < listOne.size() && countTwo < listTwo.size()) {

            if (booleanOp == 0) {//AND
                if (listOne.get(countOne).equals(listTwo.get(countTwo))) {
                    intersection.add(listOne.get(countOne));
                    countTwo++;
                    countOne++;
                } else if (listOne.get(countOne).compareTo(listTwo.get(countTwo)) > 0) {
                    countTwo++;
                } else {
                    countOne++;    
                }
            } else if (booleanOp == 1) {//OR
                if (!intersection.contains(listOne.get(countOne))) {
                    intersection.add(listOne.get(countOne));

                } 
                if (!intersection.contains(listTwo.get(countTwo))) {
                    intersection.add(listTwo.get(countTwo));

                } 
                countOne++;
                countTwo++;
            } else if (booleanOp == 2) {//NOT
                if (!listOne.contains(listTwo.get(countTwo))) {
                    intersection.add(listTwo.get(countTwo));
                    countTwo++;
                }
            }

        }
        return intersection;
    }
}
