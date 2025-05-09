package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SudokuRowTest {
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>();

        for (int i = 0; i < SudokuRow.SIZE; i++) {
            SudokuField temp = new SudokuField();
            temp.setFieldValue(i);
            fields.add(temp);
        }

        {
            SudokuRow row = new SudokuRow(fields);
            SudokuRow clone = row.clone();

            Assertions.assertEquals(row, clone); // correctness of creating copy.
            Assertions.assertNotSame(row, clone); // it's full disconnection.
        }

    }
}
