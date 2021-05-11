import java.util.ArrayList;

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

    public static double normalizedWeightQuery(int termCount, ArrayList<Double> weightedTf) {
        return (weightedTermFrequencyQuery(termCount) / l2Norm(weightedTf));
    }

    public static double cosineSimilarity(ArrayList<String> query, int termCount, ArrayList<Double> weightedTf) {
        double sum = 0.0;
        for (int i = 0; i < query.size(); i++) {
            sum += normalizedWeightQuery(termCount, weightedTf) * weightedTermFrequencyQuery(termCount);
        }
        return sum;
    }
}
