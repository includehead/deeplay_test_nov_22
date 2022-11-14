package it.pagin.deeplay.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataModel {
    private final List<Set<String>> data;

    public DataModel(List<String> rawData) {
        data = new ArrayList<>(rawData.size());
        for (String string : rawData) {
            String[] splitStrings = string.split(",");
            String[] trimmedStrings = Arrays.stream(splitStrings).map(String::trim).toArray(String[]::new);
            data.add(new HashSet<>(List.of(trimmedStrings)));
        }
    }

    public List<Set<String>> getData() {
        return data;
    }
}
