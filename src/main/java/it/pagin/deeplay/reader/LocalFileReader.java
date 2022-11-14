package it.pagin.deeplay.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocalFileReader implements IReader {

    private final File file;

    public LocalFileReader(final File file) {
        this.file = file;
    }

    @Override
    public List<String> getData() {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
