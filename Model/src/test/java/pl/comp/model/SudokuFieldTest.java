package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.model.exceptions.IncorrectValueException;

public class SudokuFieldTest {

    @Test
    public void setFieldValueTest() {
        int smallerValue = -1;
        int biggerValue = 10;

        SudokuField field = new SudokuField();

        Assertions.assertThrows(IncorrectValueException.class, () -> field.setFieldValue(smallerValue));
        Assertions.assertThrows(IncorrectValueException.class, () -> field.setFieldValue(biggerValue));
    }

    @Test
    public void equalsTest() {
        SudokuField field = new SudokuField();
        SudokuField other = new SudokuField();
        Object tempObject = new Object();

        field.setFieldValue(5);
        other.setFieldValue(2);

        Assertions.assertNotEquals(tempObject, field);
        Assertions.assertNotEquals(null, field);
        Assertions.assertEquals(field, field);
        Assertions.assertNotEquals(field, other);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuField field = new SudokuField();
        field.setFieldValue(8);

        {
            SudokuField clone = field.clone();
            Assertions.assertEquals(field, clone);
        }
    }

    @Test
    public void compareTest() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        SudokuField field3 = new SudokuField();

        field1.setFieldValue(1);
        field2.setFieldValue(2);
        field3.setFieldValue(1);

        Assertions.assertTrue(field1.compareTo(field2) < 0);
        Assertions.assertTrue(field2.compareTo(field3) > 0);
        Assertions.assertEquals(0, field3.compareTo(field1));

        // It can also be null, therefore exception...
        Assertions.assertThrows(NullPointerException.class, () -> {
            field1.compareTo(null);
        });
    }

}
