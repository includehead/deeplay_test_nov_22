package it.pagin.deeplay;

import it.pagin.deeplay.logic.Logic;
import it.pagin.deeplay.model.DataModel;
import it.pagin.deeplay.model.PropertiesModel;
import it.pagin.deeplay.reader.ReaderFactory;

import java.io.File;

public class Main {
    public static void main(final String[] args) {
        final ReaderFactory rf = new ReaderFactory();
        final Logic logic = new Logic();
        final PropertiesModel properties;
        final DataModel data;
        properties = new PropertiesModel(rf.getReader("LocalFileReader", new File("src/main/resources/parameters.txt")).getData());
        data = new DataModel(rf.getReader("LocalFileReader", new File("src/main/resources/data.txt")).getData());
        for (final String prop : properties.getPropertiesList()) {
            System.out.println(logic.doMagic(data, prop, properties.getNegationSymbol(), properties.getAndSymbol(), properties.getOrSymbol()));
        }
    }
}