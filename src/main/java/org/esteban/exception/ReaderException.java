package org.esteban.exception;

import java.io.FileNotFoundException;

public class ReaderException extends FileNotFoundException {

    public ReaderException(String message) {
        super(message);
    }
}
