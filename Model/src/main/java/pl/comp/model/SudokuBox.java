package pl.comp.model;

import java.util.List;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;

public class SudokuBox extends SudokuFields {
    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public SudokuBox clone() throws CloneException {
        try {
            return new SudokuBox(cloneFields());
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }
}
