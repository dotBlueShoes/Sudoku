package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumnTest {
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>();

        for (int i = 0; i < SudokuColumn.SIZE; i++) {
            SudokuField temp = new SudokuField();
            temp.setFieldValue(i);
            fields.add(temp);
        }

        {
            SudokuColumn column = new SudokuColumn(fields);
            SudokuColumn clone = column.clone();

            Assertions.assertEquals(column, clone); // correctness of creating copy.
            Assertions.assertNotSame(column, clone); // it's full disconnection.
        }

    }
}
