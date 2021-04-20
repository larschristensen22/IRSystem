import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Write a description of class BooleanSearch here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BooleanSearch {
    ArrayList<String> allDocs;

    /**
     * Constructor for objects of class BooleanSearch
     */
    public BooleanSearch(ArrayList<String> allDocs) {
        this.allDocs = allDocs;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public void phraseSearch(ArrayList<String> query, InvertedIndex indexIn) {
        String phrase = query.get(0);
        phrase = phrase.replaceAll("\"", "");
        StringTokenizer tokenizer = new StringTokenizer(query.get(0), " ");
        ArrayList<PostingList> postings = new ArrayList<PostingList>();
        ArrayList<ArrayList<String>> docId = new ArrayList<ArrayList<String>>();

        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();
            System.out.println("TOKEN: " + nextToken);
            PostingList newPosting = indexIn.get(nextToken);
            // System.out.println(newPosting.toString());
            ArrayList<String> newDocId = new ArrayList<String>();
            for (int i = 0; i < newPosting.getPost().size(); i++) {
                newDocId.add(newPosting.getPost().get(i).getDocID());
            }
            docId.add(newDocId);
            postings.add(newPosting);
        }
        // ArrayList<String> intersection = new ArrayList<String>();

        // if (docId.size() >= 3) {
        // intersection = BooleanSearch.recursiveIntersect(docId);
        // }

        // for (int i = 0; i < intersection.size(); i++) {
        // System.out.println("0 1 " + intersection.get(i) + " " + (i+1) + " 1.0 " +
        // "LarsAndCam");
        // }

        // ArrayList<String> intersection = docId.get(0);
        ArrayList<String> intersection = new ArrayList<String>();
        System.out.println("INTERSECTION SIZE: " + intersection.size());
        for (int i = 1; i < docId.size(); i++) {
            // System.out.println();
            int docCount1 = 0;
            int docCount2 = 0;
            while (docCount1 < docId.get(i - 1).size() && docCount2 < docId.get(i).size()) {
                if (i == 1) {
                    if (docId.get(i - 1).get(docCount1).equals(docId.get(i).get(docCount2))) {
                        Post post1 = postings.get(i - 1).findPostByDocId(docId.get(i - 1).get(docCount1));
                        Post post2 = postings.get(i).findPostByDocId(docId.get(i).get(docCount2));
                        for (int j = 0; j < post1.getPositions().size(); j++) {
                            for (int k = 0; k < post2.getPositions().size(); k++) {
                                if (post1.getPositions().get(j) == (post2.getPositions().get(k) - 1)) {
                                    intersection.add(docId.get(i).get(docCount2));
                                }
                            }
                        }
                        docCount1++;
                        docCount2++;
                    } else if (docId.get(i - 1).get(docCount1).compareTo(docId.get(i).get(docCount2)) < 0) {
                        docCount1++;
                    } else {
                        docCount2++;
                    }
                } else if (intersection.size() > 0){
                    if (docId.get(i - 1).get(docCount1).equals(docId.get(i).get(docCount2))) {
                        Post post1 = postings.get(i - 1).findPostByDocId(docId.get(i - 1).get(docCount1));
                        Post post2 = postings.get(i).findPostByDocId(docId.get(i).get(docCount2));
                        for (int j = 0; j < post1.getPositions().size(); j++) {
                            for (int k = 0; k < post2.getPositions().size(); k++) {
                                if (post1.getPositions().get(j) != (post2.getPositions().get(k) - 1)) {
                                    intersection.remove(docId.get(i).get(docCount2));
                                }
                            }
                        }
                        docCount1++;
                        docCount2++;

                    } else if (intersection.get(docCount1).compareTo(docId.get(i).get(docCount2)) < 0) {
                        intersection.remove(docCount1);
                        docCount1++;
                    } else {
                        docCount2++;
                    }

                }
            }
            System.out.println("INTERSECTION SIZE: " + intersection.size());
            // System.out.println(intersection.toString());
        }

        // while (docCount1 < docId.get(0).size() && docCount2 < docId.get(1).size()) {
        // if (docId.get(0).get(docCount1).equals(docId.get(1).get(docCount2))) {
        // intersection.add(docId.get(0).get(docCount1));
        // docCount1++;
        // docCount2++;
        // } else if (docId.get(0).get(docCount1).compareTo(docId.get(1).get(docCount2))
        // > 0) {
        // docCount2++;
        // } else {
        // docCount1++;
        // }
        // }

        // int count = 2;
        // docCount1 = 0;
        // docCount2 = 0;
        // ArrayList<String> finalIntersection = new ArrayList<String>();
        // while (count < docId.size()) {
        // for (int i = 0; i < intersection.size(); i++) {
        // for (int )
        // }

        // if (docId.get(count).get(docCount1).equals(intersection.get(docCount2))) {
        // finalIntersection.add(intersection.get(docCount2));
        // docCount1++;
        // docCount2++;
        // } else if
        // (docId.get(count).get(docCount1).compareTo(intersection.get(docCount2)) > 0)
        // {
        // docCount2++;
        // } else {
        // docCount1++;
        // }
        // }

        // for (int i = 0; i+1 < docId.size(); i++) {
        // intersection = BooleanSearch.intersect(docId.get(i), docId.get(i+1), 0);
        // }
        // int i = 1;
        // intersection = BooleanSearch.intersect(docId.get(0), docId.get(1), 0);
        // ArrayList<String> finalIntersection;
        // while (i < docId.size()) {
        // finalIntersection = BooleanSearch.intersect(intersection, docId.get(i+1), 0);
        // }

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public void booleanSearch(ArrayList<String> query, InvertedIndex indexIn) {
        int booleanOp = 0;
        ArrayList<PostingList> postings1 = new ArrayList<PostingList>();
        ArrayList<PostingList> postings2 = new ArrayList<PostingList>();
        ArrayList<String> docId1 = new ArrayList<String>();
        ArrayList<String> docId2 = new ArrayList<String>();

        if (query.contains("AND")) {
            postings1.add(indexIn.get(query.get(0)));
            postings2.add(indexIn.get(query.get(2)));
            booleanOp = 0;
        } else if (query.contains("OR")) {
            postings1.add(indexIn.get(query.get(0)));
            postings2.add(indexIn.get(query.get(2)));
            booleanOp = 1;
        } else if (query.contains("NOT")) {
            postings1.add(indexIn.get(query.get(1)));
            booleanOp = 2;
        } else {
            booleanOp = 1;
            postings1.add(indexIn.get(query.get(0)));
            postings2.add(indexIn.get(query.get(1)));
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
        } else {
            for (int i = 0; i < postings1.size(); i++) {
                for (int j = 0; j < postings1.get(i).getPost().size(); j++) {
                    docId1.add(postings1.get(i).getPost().get(j).getDocID());
                }
            }
            docId2 = allDocs;
        }
        ArrayList<String> result = BooleanSearch.intersect(docId1, docId2, booleanOp);
        for (int i = 0; i < result.size(); i++) {
            System.out.println("0 1 " + result.get(i) + " " + (i + 1) + " 1.0 " + "LarsAndCam");
        }

    }

    public static ArrayList<String> intersect(ArrayList<String> listOne, ArrayList<String> listTwo, int booleanOp) {
        ArrayList<String> intersection = new ArrayList<String>();
        int countOne = 0;
        int countTwo = 0;

        while (countOne < listOne.size() && countTwo < listTwo.size()) {

            if (booleanOp == 0) {// AND
                if (listOne.get(countOne).equals(listTwo.get(countTwo))) {
                    intersection.add(listOne.get(countOne));
                    countTwo++;
                    countOne++;
                } else if (listOne.get(countOne).compareTo(listTwo.get(countTwo)) > 0) {
                    countTwo++;
                } else {
                    countOne++;
                }
            } else if (booleanOp == 1) {// OR
                if (!intersection.contains(listOne.get(countOne))) {
                    intersection.add(listOne.get(countOne));

                }
                if (!intersection.contains(listTwo.get(countTwo))) {
                    intersection.add(listTwo.get(countTwo));

                }
                countOne++;
                countTwo++;
            } else if (booleanOp == 2) {// NOT
                if (!listOne.contains(listTwo.get(countTwo))) {
                    intersection.add(listTwo.get(countTwo));
                    countTwo++;
                }
            }

        }
        return intersection;
    }

    public static ArrayList<String> recursiveIntersect(ArrayList<ArrayList<String>> docs) {
        // ArrayList<String> returnList = new ArrayList<String>();
        int i = 0;
        while (i + 2 < docs.size()) {
            return BooleanSearch.intersect(BooleanSearch.intersect(docs.get(i), docs.get(i + 1), 0), docs.get(i + 2),
                    0);
        }

        return null;

        // return returnList;
    }
}
