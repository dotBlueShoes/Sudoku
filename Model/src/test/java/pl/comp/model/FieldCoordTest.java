package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldCoordTest {

    private static Logger logger = LoggerFactory.getLogger(FieldCoordTest.class);

    @Test
    public void equalsTest() {
        FieldCoord coord = new FieldCoord(0, 0);
        Object object = new Object();

        logger.info(coord.toString());

        Assertions.assertEquals(coord, coord);
        Assertions.assertNotEquals(object, coord);
        Assertions.assertNotEquals(null, coord);
    }
}
