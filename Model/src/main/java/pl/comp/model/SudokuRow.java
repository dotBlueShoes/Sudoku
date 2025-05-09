package pl.comp.model;

import java.util.List;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;

public class SudokuRow extends SudokuFields {
    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public SudokuRow clone() throws CloneNotSupportedException {
        try {
            return new SudokuRow(cloneFields());
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }
}
