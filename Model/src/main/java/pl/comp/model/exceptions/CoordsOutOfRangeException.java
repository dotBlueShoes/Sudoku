package pl.comp.model.exceptions;

import java.util.Locale;

public class CoordsOutOfRangeException extends SudokuException {

    public CoordsOutOfRangeException(String message) {
        super(message);
    }

    public CoordsOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoordsOutOfRangeException(String message, Throwable cause, Locale locale) {
        super(message, cause, locale);
    }
}
