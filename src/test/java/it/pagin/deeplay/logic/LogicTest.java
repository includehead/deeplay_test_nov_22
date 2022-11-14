package it.pagin.deeplay.logic;

import it.pagin.deeplay.model.DataModel;
import it.pagin.deeplay.model.PropertiesModel;
import it.pagin.deeplay.reader.LocalFileReader;
import it.pagin.deeplay.reader.ReaderFactory;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class LogicTest {
    @Test
    public void performCalculationTest() {
        Logic logic = new Logic();
        final ReaderFactory rf = new ReaderFactory();
        PropertiesModel properties;
        DataModel animals;

        properties = new PropertiesModel(
                rf.getReader(LocalFileReader.class.getSimpleName(), new File("src/test/resources/parameters.txt")).getData());
        animals = new DataModel(rf.getReader(LocalFileReader.class.getSimpleName(), new File("src/test/resources/data.txt")).getData());
        assertArrayEquals(new Integer[] {2, 1, 1}, logic.performCalculations(animals, properties).toArray());

        properties = new PropertiesModel(
                rf.getReader(LocalFileReader.class.getSimpleName(), new File("src/test/resources/parameters.txt")).getData());
        animals = new DataModel(rf.getReader(LocalFileReader.class.getSimpleName(), new File("src/test/resources/bigger_data.txt")).getData());
        assertArrayEquals(new Integer[] {3, 1, 2}, logic.performCalculations(animals, properties).toArray());


        properties = new PropertiesModel(
                rf.getReader(LocalFileReader.class.getSimpleName(), new File("src/test/resources/another_parameters.txt")).getData());
        assertArrayEquals(new Integer[] {3, 1, 2, 2, 0, 0}, logic.performCalculations(animals, properties).toArray());
    }
}