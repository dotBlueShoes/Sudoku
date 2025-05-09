package pl.comp.model.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class CloneException extends CloneNotSupportedException  {

    private ResourceBundle bundle;

    public CloneException(String message) {
        this(message, new CloneNotSupportedException(), Locale.getDefault());
    }

    public CloneException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public CloneException(String message, Throwable cause, Locale locale) {
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
