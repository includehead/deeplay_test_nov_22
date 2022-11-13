package it.pagin.deeplay.model;

import java.util.ArrayList;
import java.util.List;

public class PropertiesModel {
    private final String negationSymbol;
    private final String andSymbol;
    private final String orSymbol;
    private final List<String> propertiesList;

    public PropertiesModel(final List<String> gottenProps) {
        propertiesList = new ArrayList<>(gottenProps.size());
        negationSymbol = gottenProps.get(0).strip();
        andSymbol = gottenProps.get(1).strip();
        orSymbol = gottenProps.get(2).strip();
        for (int i = 3; i < gottenProps.size(); i++) {
            propertiesList.add(gottenProps.get(i));
        }
    }

    public String getNegationSymbol() {
        return negationSymbol;
    }

    public List<String> getPropertiesList() {
        return propertiesList;
    }

    public String getAndSymbol() {
        return andSymbol;
    }

    public String getOrSymbol() {
        return orSymbol;
    }
}
