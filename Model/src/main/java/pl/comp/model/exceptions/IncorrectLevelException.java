package pl.comp.model.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class IncorrectLevelException extends RuntimeException {

    private ResourceBundle bundle;

    public IncorrectLevelException(String message) {
        this(message, new RuntimeException(), Locale.getDefault());
    }

    public IncorrectLevelException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public IncorrectLevelException(String message, Throwable cause, Locale locale) {
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
