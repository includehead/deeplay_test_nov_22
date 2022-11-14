package it.pagin.deeplay.logic;

import it.pagin.deeplay.model.DataModel;
import it.pagin.deeplay.model.PropertiesModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Logic {
    Logger logger = LoggerFactory.getLogger(Logic.class);
    private PropertiesModel properties;

    public List<Integer> performCalculations(final DataModel animals, final PropertiesModel properties) {
        this.properties = properties;
        final List<Integer> calculatedValues = new ArrayList<>();
        int count;
        for (final String matchingPropertiesString : properties.getPropertiesList()) {
            count = countEligible(animals, matchingPropertiesString);
            logger.info("Result for " + matchingPropertiesString + " = " + count);
            calculatedValues.add(count);
        }
        return calculatedValues;
    }

    private Integer countEligible(final DataModel animals, final String matchingParameters) {
        int counter = 0;
        for (Set<String> row : animals.getData()) {
            if (isMatchRule(row, matchingParameters)) {
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
            if (rule.matches(properties.getLogicSymbols().getNegationSymbolRegex())) {
                return !data.contains(rule);
            }
            return data.contains(rule);
        }
        if (rule.matches(properties.getLogicSymbols().getAndSymbolRegex())) {
            String[] splitProps = rule.split(properties.getLogicSymbols().getAndSymbol(), 2);
            return and(data, splitProps[0].trim(), splitProps[1].trim());
        }
        if (rule.matches(properties.getLogicSymbols().getOrSymbolRegex())) {
            String[] splitProps = rule.split(properties.getLogicSymbols().getOrSymbol(), 2);
            return or(data, splitProps[0].trim(), splitProps[1].trim());
        }
        return false;
    }

    private boolean doesNotContainBinaryOperators(final String string) {
        return !string.matches(properties.getLogicSymbols().getAndSymbolRegex()) &&
                !string.matches(properties.getLogicSymbols().getOrSymbolRegex());
    }
}