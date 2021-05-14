import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Forumlas to calculate cosine similarity
 *
 * @author Lars and Cam
 * @version 3/22/21
 */
public class Formulas {

    /**
     * Calculates weighted term frequency
     * 
     * @return double - weight term frequency
     * @param int termCount - the count of terms.
     *     double idf - the inverse document frequency
     */
    public static double weightedTermFrequency(int termCount, double idf) {
        return (1+ Math.log(termCount)) * idf;
    }

    /**
     * Calculates weighted term frequency for the query
     * 
     * @return double - weight term frequency
     * @param int termCount - the count of query terms.
     */
    public static double weightedTermFrequencyQuery(int termCount) {
        return (1+ Math.log(termCount));
    }

    /**
     * Calculates the l2Norm
     * 
     * @return double - l2 Norm
     * @param double sumWeightedTf - sum of all the terms
     */
    public static double l2Norm(double sumWeightedTf) {

        return Math.sqrt(sumWeightedTf);
    }

    /**
     * Calculates the normalized weight
     * 
     * @return double - normalized weight
     * @param double weightedTf - the weighted term frequency
     * double l2Norm - the l2Norm
     */
    public static double normalizedWeight(double weightedTf, double l2Norm) {
        return (weightedTf / l2Norm);
    }

    /**
     * Calculates the cosine similarity.
     * 
     * @param ArrayList<Document> docs - all the documents
             * ArrayList<Double> tokenWeightedTf - weighted term frequency of query token
             * ArrayList<String> tokens - query tokens
     */
    public static void cosineSimilarity(ArrayList<Document> docs, ArrayList<Double> tokenWeightedTf, ArrayList<String> tokens) {

        double sum;
        //hashmap for storing score
        HashMap<String, Double> cosineScores = new HashMap<String, Double>();
        
        //loop through docs
        for (int i = 0; i < docs.size(); i++) {
            sum = 0.0;
            //loop through tokens and calculate cosine similarity
            for (int j = 0; j < tokens.size(); j++) {
                String token = tokens.get(j);
                Post post = docs.get(i).getPostingList().findPostByDocId(token);
                if (post != null) {
                    sum += (tokenWeightedTf.get(j) * post.getNormalizedWeight());
                }
            }
            cosineScores.put(docs.get(i).getDocID(), sum);
        }
        int rank = 1;
        String maxDoc;
        double max;
        int count = 0;
        
        //rank the documents based on cosine similarity
        while(cosineScores.size() > 0 && count < 1000) {
            max = 0.0;
            maxDoc = "";
            for (Map.Entry mapElement : cosineScores.entrySet()) {

                String key = (String)mapElement.getKey();
                double value = (double)mapElement.getValue();
                if (value > max) {
                    max = value;
                    maxDoc = key;
                }
            }

            //print document ranks
            System.out.println("0 1 " + " " + maxDoc + " " + rank + " " + max + " LarsAndCam");
            rank++;
            cosineScores.remove(maxDoc);
            count++;
        }
    }
}
