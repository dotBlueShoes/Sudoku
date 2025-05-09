package pl.comp.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuBoxTest {
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>();

        for (int i = 0; i < SudokuBox.SIZE; i++) {
            SudokuField temp = new SudokuField();
            temp.setFieldValue(i);
            fields.add(temp);
        }

        {
            SudokuBox box = new SudokuBox(fields);
            SudokuBox clone = box.clone();

            Assertions.assertEquals(box, clone); // correctness of creating copy.
            Assertions.assertNotSame(box, clone); // it's full disconnection.
        }

    }
}
