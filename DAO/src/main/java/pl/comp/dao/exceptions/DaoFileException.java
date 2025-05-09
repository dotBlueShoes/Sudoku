package pl.comp.dao.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class DaoFileException extends ClassNotFoundException {

    private ResourceBundle bundle;

    public DaoFileException(String message) {
        this(message, new ClassNotFoundException(), Locale.getDefault());
    }

    public DaoFileException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public DaoFileException(String message, Throwable cause, Locale locale) {
        super(message, cause);
        // This way we construct a exception with a locale info.
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
