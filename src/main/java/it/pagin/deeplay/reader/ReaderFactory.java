package it.pagin.deeplay.reader;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public class ReaderFactory {

    public IReader getReader(final String readerName, @NotNull final InputStream inputStream) {
        if ("LocalFileReader".equals(readerName)) {
            return new LocalFileReader(inputStream);
        }
        return null;
    }

}
