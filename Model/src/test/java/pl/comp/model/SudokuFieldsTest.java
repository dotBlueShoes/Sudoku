package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SudokuFieldsTest {

    private static Logger logger = LoggerFactory.getLogger(SudokuFieldsTest.class);

    @Test
    public void verifyTest() {
        List<SudokuField> fields = new ArrayList<>(9);

        // Repeat Test
        for ( int i = 0; i < 9; i++) {
            SudokuField field = new SudokuField();
            field.setFieldValue(1);
            fields.add(field);
        }

        {
            SudokuFields container = new SudokuColumn(fields);
            logger.info(container.toString());
            Assertions.assertFalse(container.verify());
        }

    }

    @Test
    public void equalsTest() {
        List<SudokuField> fields = new ArrayList<>(9);
        List<SudokuField> others = new ArrayList<>(9);
        Object tempObject = new Object();

        for ( int i = 0; i < 9; i++) {
            SudokuField field = new SudokuField();
            field.setFieldValue(i);
            fields.add(field);
        }

        for ( int i = 0; i < 9; i++) {
            SudokuField field = new SudokuField();
            field.setFieldValue(1);
            others.add(field);
        }

        SudokuFields container1 = new SudokuColumn(fields);
        SudokuFields container2 = new SudokuColumn(others);

        Assertions.assertNotEquals(tempObject, container1);
        Assertions.assertNotEquals(null, container1);
        Assertions.assertEquals(container1, container1);
        Assertions.assertNotEquals(container1, container2);
    }

}
