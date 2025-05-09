package pl.comp.dao.exceptions;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DaoInOuException extends IOException {

    private ResourceBundle bundle;

    public DaoInOuException(String message) {
        this(message, new IOException(), Locale.getDefault());
    }

    public DaoInOuException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public DaoInOuException(String message, Throwable cause, Locale locale) {
        super(message, cause);
        // This way we construct a exception with a locale info.
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
