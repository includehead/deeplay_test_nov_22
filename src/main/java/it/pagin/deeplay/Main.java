package it.pagin.deeplay;

import it.pagin.deeplay.logic.Logic;
import it.pagin.deeplay.model.DataModel;
import it.pagin.deeplay.model.PropertiesModel;
import it.pagin.deeplay.reader.LocalFileReader;
import it.pagin.deeplay.reader.ReaderFactory;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(final String[] args) {
        final ReaderFactory rf = new ReaderFactory();
        final Logic logic = new Logic();
        final PropertiesModel properties;
        final DataModel animals;

        properties = new PropertiesModel(
                rf.getReader(LocalFileReader.class.getSimpleName(), new File("src/main/resources/parameters.txt")).getData());
        animals = new DataModel(rf.getReader(LocalFileReader.class.getSimpleName(), new File("src/main/resources/data.txt")).getData());

        List<Integer> resultValues = logic.performCalculations(animals, properties);
        System.out.println("Количество травоядных животных = " + resultValues.get(0));
        System.out.println("Количество травоядных или плотоядных и они при этом маленьких животных = " + resultValues.get(1));
        System.out.println("Количество всеядных но не высоких животных = " + resultValues.get(2));
    }
}