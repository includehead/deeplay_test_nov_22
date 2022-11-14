package it.pagin.deeplay.model;

import java.util.ArrayList;
import java.util.List;

public class PropertiesModel {
    private static final int NEGATION_SYMBOL_LINE_NUMBER = 0;
    private static final int AND_SYMBOL_LINE_NUMBER = 1;
    private static final int OR_SYMBOL_LINE_NUMBER = 2;
    private static final int PROPERTIES_LINE_NUMBER = 3;

    private final LogicSymbols symbols;
    private final List<String> propertiesList;

    /**
     * Avoid regex reserved symbols in logic operators
     *
     * @param propertiesList should contain negation symbol in first[0] string,
     *                       and symbol in second[1], or symbol in third[2]
     */
    public PropertiesModel(final List<String> propertiesList) {
        this.propertiesList = new ArrayList<>(propertiesList.size());
        final String negationSymbol = propertiesList.get(NEGATION_SYMBOL_LINE_NUMBER).strip();
        final String andSymbol = propertiesList.get(AND_SYMBOL_LINE_NUMBER).strip();
        final String orSymbol = propertiesList.get(OR_SYMBOL_LINE_NUMBER).strip();
        symbols = new LogicSymbols(negationSymbol, orSymbol, andSymbol);
        for (int i = PROPERTIES_LINE_NUMBER; i < propertiesList.size(); i++) {
            this.propertiesList.add(propertiesList.get(i));
        }
    }

    public LogicSymbols getLogicSymbols() {
        return symbols;
    }

    public List<String> getPropertiesList() {
        return propertiesList;
    }
}
