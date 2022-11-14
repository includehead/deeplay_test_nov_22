package it.pagin.deeplay.logic;

import it.pagin.deeplay.model.DataModel;
import it.pagin.deeplay.model.PropertiesModel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Logic {
    private final Logger logger = LoggerFactory.getLogger(Logic.class);
    private PropertiesModel properties;

    public List<Integer> performCalculations(final @NotNull DataModel animals, final @NotNull PropertiesModel properties) {
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
        if ("".equals(rule.trim())) {
            return false;
        }
        if (doesNotContainBinaryOperators(rule)) {
            if (rule.matches(properties.getLogicSymbols().getNegationSymbolRegex())) {
                return !data.contains(rule.replace(properties.getLogicSymbols().getNegationSymbol(), ""));
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
        throw new RuntimeException("Some problem in logic symbols!");
    }

    private boolean doesNotContainBinaryOperators(final String string) {
        return !string.matches(properties.getLogicSymbols().getAndSymbolRegex()) &&
                !string.matches(properties.getLogicSymbols().getOrSymbolRegex());
    }
}