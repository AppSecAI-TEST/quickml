package quickml.supervised.classifier.tree.decisionTree.scorers;

import quickml.supervised.classifier.tree.decisionTree.tree.ClassificationCounter;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by chrisreeves on 6/24/14.
 */
public class GiniImpurityScorer implements Scorer<ClassificationCounter>{
    @Override
    public double scoreSplit(ClassificationCounter a, ClassificationCounter b) {
        ClassificationCounter parent = ClassificationCounter.merge(a, b);
        double aGiniIndex = getGiniIndex(a) * a.getTotal() / parent.getTotal() ;
        double bGiniIndex = getGiniIndex(b) * b.getTotal() / parent.getTotal();
        double score =  unSplitScore - aGiniIndex - bGiniIndex;
        //TODO: make this call only when the best split for a particular attribute is found...as it is redundant.
        return correctScoreForGainRatioPenalty(score);
    }

    @Override
    public void setUnSplitScore(ClassificationCounter a) {
        unSplitScore = getGiniIndex(a);

    }

    private double getGiniIndex(ClassificationCounter cc) {
        double sum = 0.0d;
        for (Map.Entry<Object, Double> e : cc.getCounts().entrySet()) {
            double error = (cc.getTotal() > 0) ? e.getValue() / cc.getTotal() : 0;
            sum += error * error;
        }
        return 1.0d - sum;
    }

    @Override
    public String toString() {
        return "GiniImpurity";
    }
}
