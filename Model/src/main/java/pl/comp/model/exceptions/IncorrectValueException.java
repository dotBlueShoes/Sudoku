package pl.comp.model.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class IncorrectValueException extends IllegalArgumentException {

    private ResourceBundle bundle;

    public IncorrectValueException(String message) {
        this(message, new IllegalArgumentException(), Locale.getDefault());
    }

    public IncorrectValueException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public IncorrectValueException(String message, Throwable cause, Locale locale) {
        super(message);
        initCause(cause);
        // This way we construct an exception with a locale info.
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
