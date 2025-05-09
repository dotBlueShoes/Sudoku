package pl.comp.model.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuException extends RuntimeException {

    private ResourceBundle bundle;

    public SudokuException(String message) {
        this(message, new Exception(), Locale.getDefault());
    }

    public SudokuException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public SudokuException(String message, Throwable cause, Locale locale) {
        super(message, cause);
        // This way we construct an exception with a locale info.
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
