import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
/**
 * BooleanSearch class to run boolean search operations and phrase searches.
 *
 * @author Lars and Cam
 * @version IR HW2
 */
public class BooleanSearch {
    
    //Storing a list of all document IDs
    ArrayList<String> allDocs;

    /**
     * Constructor for objects of class BooleanSearch
     */
    public BooleanSearch(ArrayList<String> allDocs) {
        this.allDocs = allDocs;
    }

    /**
     * Find the documents that contain a given phrase
     *
     * @param query the query
     * @param indexIn the inverted index
     */
    public void phraseSearch(ArrayList<String> query, InvertedIndex indexIn) throws FileNotFoundException {
        //Read the phrase from the query input parameter
        String phrase = query.get(0);
        phrase = phrase.toLowerCase();
        //Remove quotes from phrase search
        phrase = phrase.replaceAll("\"", "");
        //Tokenize the phrase
        StringTokenizer tokenizer = new StringTokenizer(query.get(0), " ");
        ArrayList<PostingList> postings = new ArrayList<PostingList>();
        ArrayList<ArrayList<String>> docId = new ArrayList<ArrayList<String>>();
        //While there are more tokens, retrieve the posting list and a list of documents for each
        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();
            nextToken = nextToken.toLowerCase();
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
        //For each word in the phrase, intersect the document lists to find documents that contain the whole phrase
        ArrayList<String> intersection = new ArrayList<String>();
        for (int i = 1; i < docId.size(); i++) {
            int docCount1 = 0;
            int docCount2 = 0;
            //If we are dealing with the first two words, add to the intersection list if they are in the same 
            //document ID and are in consecutive positions
            if (i == 1) {
                while (docCount1 < docId.get(i - 1).size() && docCount2 < docId.get(i).size()) {
                    if (docId.get(i - 1).get(docCount1).equals(docId.get(i).get(docCount2))) {
                        Post post1 = postings.get(i - 1).findPostByDocId(docId.get(i - 1).get(docCount1));
                        Post post2 = postings.get(i).findPostByDocId(docId.get(i).get(docCount2));
                        for (int j = 0; j < post1.getPositions().size(); j++) {
                            for (int k = 0; k < post2.getPositions().size(); k++) {
                                if (post1.getPositions().get(j) == (post2.getPositions().get(k) - 1)) {
                                    if (!intersection.contains(docId.get(i - 1).get(docCount1))) {
                                        intersection.add(docId.get(i - 1).get(docCount1));
                                    }
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
                }
            }
            //For words after the first two, remove from the intersection list if they are not in the same document ID
            //or are not in consecutive positions of the same document 
            else if (i > 1 && intersection.size() > 0) {
                while (docCount1 < docId.get(i - 1).size() && docCount2 < docId.get(i).size()) {
                    boolean isConsecutive = false;
                    if (docId.get(i - 1).get(docCount1).equals(docId.get(i).get(docCount2))) {
                        Post post1 = postings.get(i - 1).findPostByDocId(docId.get(i - 1).get(docCount1));
                        Post post2 = postings.get(i).findPostByDocId(docId.get(i).get(docCount2));
                        for (int j = 0; j < post1.getPositions().size(); j++) {
                            for (int k = 0; k < post2.getPositions().size(); k++) {
                                if (post1.getPositions().get(j) == (post2.getPositions().get(k) - 1)) {
                                    isConsecutive = true;
                                }
                            }
                        }
                        if (!isConsecutive) {
                            intersection.remove(docId.get(i).get(docCount2));
                        }

                        docCount1++;
                        docCount2++;

                    } else if (docId.get(i - 1).get(docCount1).compareTo(docId.get(i).get(docCount2)) < 0) {
                        intersection.remove(docId.get(i - 1).get(docCount1));
                        docCount1++;
                    } else {
                        docCount2++;
                    }
                }
            }
        }
        //Printing the output for the resulting list of documents
        for (int i = 0; i < intersection.size(); i++) {
            System.out.println("0 1 " + intersection.get(i) + " " + (i + 1) + " 1.0 " + "LarsAndCam");
        }
    }

    /**
     * Search a query based on boolean operators in the query
     *
     * @param query the query entered
     * @param index the index that contains query information
     */
    public void booleanSearch(ArrayList<String> query, InvertedIndex indexIn, ArrayList<String> docNums, int currentQuery) {
        int booleanOp = 0;
        int queryCount = 0;
        
        //lists that will be intersected
        ArrayList<PostingList> postings1 = new ArrayList<PostingList>();
        ArrayList<PostingList> postings2 = new ArrayList<PostingList>();
        ArrayList<String> docId1 = new ArrayList<String>();
        ArrayList<String> docId2 = new ArrayList<String>();
        ArrayList<PostingList> longPostings = new ArrayList<PostingList>();
        ArrayList<ArrayList<String>> longDocId = new ArrayList<ArrayList<String>>();

        //check the query to see which boolean operator is used
        if (query.contains("AND")) {
            postings1.add(indexIn.get(query.get(0).toLowerCase()));
            postings2.add(indexIn.get(query.get(2).toLowerCase()));
            booleanOp = 0;
        } else if (query.contains("OR")) {
            postings1.add(indexIn.get(query.get(0).toLowerCase()));
            postings2.add(indexIn.get(query.get(2).toLowerCase()));
            booleanOp = 1;
        } else if (query.contains("NOT")) {
            postings1.add(indexIn.get(query.get(1).toLowerCase()));
            booleanOp = 2;
        } else {
            booleanOp = 1;
            for (int i = 0; i < query.size(); i++) {
                longPostings.add(indexIn.get(query.get(i).toLowerCase()));
                //System.out.println("ADDING POSTING LIST");
            }
            // postings1.add(indexIn.get(query.get(0).toLowerCase()));
            // postings2.add(indexIn.get(query.get(1).toLowerCase()));
        }

        //determine whether or not the query has NOT operator in it
        if (!query.contains("NOT") && longPostings.size() == 0) {
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
        } else if (longPostings.size() > 0) {
            for (int i = 0; i < longPostings.size(); i++) {
                longDocId.add(new ArrayList<String>());
                //System.out.println("TESTING: " + longPostings.get(i));
                if (longPostings.get(i) != null) {
                    for (int j = 0; j < longPostings.get(i).getPost().size(); j++) {
                        longDocId.get(i).add(longPostings.get(i).getPost().get(j).getDocID());
                    }
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
        
        ArrayList<String> result = new ArrayList<String>();
        //intersect the lists based on the operators
        if (longDocId.size() > 0) {
            for (int i = 0; i < longDocId.size(); i++) {
                for(int j = 0; j < longDocId.get(i).size(); j++) {
                    if (!result.contains(longDocId.get(i).get(j))) {
                        result.add(longDocId.get(i).get(j));
                    }
                }
            }
            for (int i = 0; i < result.size(); i++) {
                //print out results of the intersection
                System.out.println(docNums.get(currentQuery) + " 1" + result.get(i) + " " + (i + 1) + " 1.0 " + "LarsAndCam");
            }
        } else {
            result = BooleanSearch.intersect(docId1, docId2, booleanOp);
            for (int i = 0; i < result.size(); i++) {
                //print out results of the intersection
                System.out.println(docNums.get(currentQuery) + " 1" + result.get(i) + " " + (i + 1) + " 1.0 " + "LarsAndCam");
            }
        }
    }

    /**
     * Interests the postings lists of two words based on certain criteria
     *
     * @param listOne list to intersect
     * @param listTwo list to intersect
     * @param booleanOp indicates how to intersect the lists
     * @return the intersection of the two lists
     */
    public static ArrayList<String> intersect(ArrayList<String> listOne, ArrayList<String> listTwo, int booleanOp) {
        ArrayList<String> intersection = new ArrayList<String>();
        int countOne = 0;
        int countTwo = 0;

        //while not gone off the end of the list
        while (countOne < listOne.size() && countTwo < listTwo.size()) {

            if (booleanOp == 0) {// AND
                //intersect the two lists using AND operator
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
                //intersect the two lists using OR operator
                if (!intersection.contains(listOne.get(countOne))) {
                    intersection.add(listOne.get(countOne));

                }
                if (!intersection.contains(listTwo.get(countTwo))) {
                    intersection.add(listTwo.get(countTwo));

                }
                countOne++;
                countTwo++;
            } else if (booleanOp == 2) {// NOT
                //intersect the two lists using NOT operator
                if (!listOne.contains(listTwo.get(countTwo))) {
                    intersection.add(listTwo.get(countTwo));
                    countTwo++;
                }
            }

        }
        return intersection;
    }
}
