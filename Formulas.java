import java.util.ArrayList;

public class Formulas {
    
    public double weightedTermFrequency(int termCount, double idf) {
        return (1+ Math.log(termCount)) * idf;
    }

    public double weightedTermFrequencyQuery(int termCount) {
        return (1+ Math.log(termCount));
    }

    public double l2Norm(ArrayList<Double> weightedTf) {
        double sum = 0.0;
        for (int i = 0; i < weightedTf.size(); i++) {
            sum += weightedTf.get(i);
        }
        
        return Math.sqrt(sum);
    }

    public double normalizedWeight(int termCount, double idf, ArrayList<Double> weightedTf) {
        return (weightedTermFrequency(termCount, idf) / l2Norm(weightedTf));
    }

    public double normalizedWeightQuery(int termCount, ArrayList<Double> weightedTf) {
        return (weightedTermFrequencyQuery(termCount) / l2Norm(weightedTf));
    }

    public double cosineSimilarity(ArrayList<String> query, int termCount, ArrayList<Double> weightedTf) {
        double sum = 0.0;
        for (int i = 0; i < query.size(); i++) {
            sum += normalizedWeightQuery(termCount, weightedTf) * weightedTermFrequencyQuery(termCount);
        }
        return sum;
    }
}
