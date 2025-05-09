package pl.comp.javafx.converters;

import javafx.util.StringConverter;
import pl.comp.javafx.helpers.SudokuFieldHelper;

public class SudokuFieldConverter extends StringConverter<Integer> {

    @Override
    public String toString(Integer integer) {
        if (integer > 9 || integer < 1) {
            return "";
        } else {
            return integer.toString();
        }
    }

    @Override
    public Integer fromString(String string) {
        if (SudokuFieldHelper.PATTERN.matcher(string).matches()) {
            return Integer.valueOf(string);
        } else {
            return 0;
        }
    }

}