package pl.comp.javafx.language;

import java.util.Locale;
import java.util.ResourceBundle;
import pl.comp.javafx.SudokuApplication;

public class Language {

    public static void changeToPolish() {
        Locale polish = new Locale.Builder()
                .setLanguage(SudokuApplication.LOCALE_PL)
                .setRegion(SudokuApplication.LOCALE_PL)
                .build();

        SudokuApplication.bundle = ResourceBundle.getBundle(SudokuApplication.LOCALE_BASE, polish);
        Locale.setDefault(polish);
    }

    public static void changeToEnglish() {
        Locale english = new Locale.Builder()
                .setLanguage(SudokuApplication.LOCALE_EN)
                .setRegion(SudokuApplication.LOCALE_EN)
                .build();

        SudokuApplication.bundle = ResourceBundle.getBundle(SudokuApplication.LOCALE_BASE, english);
        Locale.setDefault(english);
    }

}
