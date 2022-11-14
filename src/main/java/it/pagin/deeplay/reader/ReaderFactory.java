package it.pagin.deeplay.reader;

import java.io.File;

public class ReaderFactory {

    public IReader getReader(final String readerName, final File inputStream) {
        if ("LocalFileReader".equals(readerName)) {
            return new LocalFileReader(inputStream);
        }
        return null;
    }

}
