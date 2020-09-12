package util;

import java.util.LinkedHashSet;
import java.util.Set;

public class AutoCompleteSetManager {

    private static AutoCompleteSetManager instance = new AutoCompleteSetManager();

    private Set<String> fruitTextFieldAutoCompleteSet = new LinkedHashSet<String>();
    private Set<String> sortTextFieldAutoCompleteSet = new LinkedHashSet<String>();
    private Set<String> providerTextFieldAutoCompleteSet = new LinkedHashSet<String>();


    private AutoCompleteSetManager(){

    }

    public static AutoCompleteSetManager getInstance(){
        return instance;
    }

    public void clearAllTextFieldAutoCompleteSets(){

        fruitTextFieldAutoCompleteSet.clear();
        sortTextFieldAutoCompleteSet.clear();
        providerTextFieldAutoCompleteSet.clear();
    }

    public Set<String> getFruitTextFieldAutoCompleteSet() {
        return fruitTextFieldAutoCompleteSet;
    }

    public void addToFruitTextFieldAutoCompleteSet(String fruit) {
        fruitTextFieldAutoCompleteSet.add(fruit);
    }

    public Set<String> getSortTextFieldAutoCompleteSet() {
        return sortTextFieldAutoCompleteSet;
    }

    public void addToSortTextFieldAutoCompleteSet(String sort) {
        sortTextFieldAutoCompleteSet.add(sort);
    }

    public Set<String> getProviderTextFieldAutoCompleteSet() {
        return providerTextFieldAutoCompleteSet;
    }

    public void addToProviderTextFieldAutoCompleteSet(String provider) {
        providerTextFieldAutoCompleteSet.add(provider);
    }


}
