package pl.comp.javafx;

import java.util.ListResourceBundle;

public class Authors_pl extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            { "title", "Authors" },
            { "1", "Mateusz Strumiłło" }
        };
    }
}
