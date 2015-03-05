package edu.neu.ccs.pyramid.dataset;

import java.util.Map;

/**
 * Created by chengli on 3/4/15.
 */
public class CategoricalFeature extends Feature {
    // red
    private String category;
    private int numCategories;
    // color
    private String variableName;
    private Map<String, Integer> categoryIndexMap;



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public int getNumCategories() {
        return numCategories;
    }

    public void setNumCategories(int numCategories) {
        this.numCategories = numCategories;
    }

    public Map<String, Integer> getCategoryIndexMap() {
        return categoryIndexMap;
    }

    public void setCategoryIndexMap(Map<String, Integer> categoryIndexMap) {
        this.categoryIndexMap = categoryIndexMap;
    }
}
