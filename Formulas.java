import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Formulas {
    
    public static double weightedTermFrequency(int termCount, double idf) {
        return (1+ Math.log(termCount)) * idf;
    }

    public static double weightedTermFrequencyQuery(int termCount) {
        return (1+ Math.log(termCount));
    }

    public static double l2Norm(double sumWeightedTf) {

        return Math.sqrt(sumWeightedTf);
    }

    public static double normalizedWeight(double weightedTf, double l2Norm) {
        return (weightedTf / l2Norm);
    }

    public static void cosineSimilarity(ArrayList<Document> docs, ArrayList<Double> tokenWeightedTf, ArrayList<String> tokens) {
        double sum;
        HashMap<String, Double> cosineScores = new HashMap<String, Double>();
        for (int i = 0; i < docs.size(); i++) {
            sum = 0.0;
            for (int j = 0; j < tokens.size(); j++) {
                String token = tokens.get(j);
                Post post = docs.get(i).getPostingList().findPostByDocId(token);
                if (post != null) {
                    System.out.println("TOKEN: " + tokens.get(j) + " NW: " + post.getNormalizedWeight());
                    sum += (tokenWeightedTf.get(j) * post.getNormalizedWeight());
                }
            }
            //System.out.println("SUM: " + sum);
            cosineScores.put(docs.get(i).getDocID(), sum);
        }
        int rank = 1;
        String maxDoc;
        double max;
        int count = 0;
        while(cosineScores.size() > 0 && count < 1000) {
            max = 0.0;
            maxDoc = "";
            for (Map.Entry mapElement : cosineScores.entrySet()) {
                
                //System.out.println("IN LOOP");
                String key = (String)mapElement.getKey();
                double value = (double)mapElement.getValue();
                if (value > max) {
                    max = value;
                    maxDoc = key;
                }
            }
            
            System.out.println("0 1 " + " " + maxDoc + " " + rank + " " + max + " LarsAndCam");
            rank++;
            cosineScores.remove(maxDoc);
            count++;
        }
    }
}
