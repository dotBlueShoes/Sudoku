package pl.comp.javafx;

import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            { "title", "Authors" },
            { "1", "Matthew Strumiłło " }
        };
    }
}