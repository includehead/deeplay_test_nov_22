package it.pagin.deeplay.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocalFileReader implements IReader {

    private InputStream inputStream;

    public LocalFileReader(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public List<String> getData() {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
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
