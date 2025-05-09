package pl.comp.model;

import java.util.List;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;

public class SudokuColumn extends SudokuFields {
    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public SudokuColumn clone() throws CloneException {
        try {
            return new SudokuColumn(cloneFields());
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }
}
