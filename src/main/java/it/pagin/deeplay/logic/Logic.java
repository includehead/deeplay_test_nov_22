package it.pagin.deeplay.logic;

import it.pagin.deeplay.model.DataModel;

import java.util.Set;

public class Logic {
    private String negationSymbolRegex;
    private String orSymbol;
    private String orSymbolRegex;
    private String andSymbol;
    private String andSymbolRegex;

    public Integer doMagic(final DataModel dataModel, final String props, final String negationSymbol, final String andSymbol,
                                final String orSymbol) {
        this.andSymbol = andSymbol;
        this.andSymbolRegex =  "(.*)" + andSymbol + "(.*)";
        this.negationSymbolRegex =  "(.*)" + negationSymbol + "(.*)";
        this.orSymbol = orSymbol;
        this.orSymbolRegex =  "(.*)" + orSymbol + "(.*)";
        int counter = 0;
        for (Set<String> animal : dataModel.getData()) {
            if (isMatchRule(animal, props)) {
                counter++;
            }
        }
        return counter;
    }

    private boolean and(final Set<String> data, final String leftPart, final String rightPart) {
        return isMatchRule(data, leftPart) && isMatchRule(data, rightPart);
    }

    private boolean or(final Set<String> data, final String leftPart, final String rightPart) {
        return isMatchRule(data, leftPart) || isMatchRule(data, rightPart);
    }
    private boolean isMatchRule(final Set<String> data, final String rule) {
        if (doesNotContainBinaryOperators(rule)) {
            if (rule.matches(negationSymbolRegex)) {
                return !data.contains(rule);
            }
            return data.contains(rule);
        }
        if (rule.matches(andSymbolRegex)) {
            String[] splitProps = rule.split(andSymbol, 2);
            return and(data, splitProps[0].trim(), splitProps[1].trim());
        }
        if (rule.matches(orSymbolRegex)) {
            String[] splitProps = rule.split(orSymbol, 2);
            return or(data, splitProps[0].trim(), splitProps[1].trim());
        }
        return false;
    }
    private boolean doesNotContainBinaryOperators(final String string) {
        return !string.matches(andSymbolRegex) && !string.matches(orSymbolRegex);
    }
}