package edu.neu.ccs.pyramid.classification.logistic_regression;

import edu.neu.ccs.pyramid.feature.FeatureUtility;
import edu.neu.ccs.pyramid.util.Pair;
import org.apache.mahout.math.Vector;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by chengli on 12/7/14.
 */
public class LogisticRegressionInspector {
    //todo if features are on different scales, weights are not comparable
    public static List<FeatureUtility> topFeatures(LogisticRegression logisticRegression,
                                                         int k){
        String[] featureNames = logisticRegression.getFeatureNames();
        Vector weights = logisticRegression.getWeights().getWeightsWithoutBiasForClass(k);
        Comparator<FeatureUtility> comparator = Comparator.comparing(FeatureUtility::getUtility);
        List<FeatureUtility> list = IntStream.range(0,weights.size())
                .mapToObj(i -> new FeatureUtility(i,featureNames[i]).setUtility(weights.get(i)))
                .filter(featureUtility -> featureUtility.getUtility()>0)
                .sorted(comparator.reversed())
                .collect(Collectors.toList());
        IntStream.range(0,list.size()).forEach(i-> list.get(i).setRank(i));
        return list;
    }

    public static List<FeatureUtility> topFeatures(LogisticRegression logisticRegression,
                                                         int k,
                                                         int limit){
        return topFeatures(logisticRegression,k).stream().limit(limit).collect(Collectors.toList());
    }

    public static int[] numOfUsedFeaturesEachClass(LogisticRegression logisticRegression){
        int[] numbers = new int[logisticRegression.getNumClasses()];
        for (int k=0;k<logisticRegression.getNumClasses();k++){
            numbers[k] = logisticRegression.getWeights().getWeightsWithoutBiasForClass(k).getNumNonZeroElements();
        }
        return numbers;
    }

    public static int numOfUsedFeaturesCombined(LogisticRegression logisticRegression){
        Set<Integer> usedFeatures = new HashSet<>();
        for (int k=0;k<logisticRegression.getNumClasses();k++){
            Vector vector = logisticRegression.getWeights().getWeightsWithoutBiasForClass(k);
            for (Vector.Element element: vector.nonZeroes()){
                usedFeatures.add(element.index());
            }
        }
        return usedFeatures.size();

    }


}
