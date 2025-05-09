module pl.pk.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires Model;
    requires Dao;
    requires org.slf4j;
    requires org.apache.logging.log4j;

    opens pl.comp.javafx to javafx.fxml;
    exports pl.comp.javafx;
    exports pl.comp.javafx.controllers;
    opens pl.comp.javafx.controllers to javafx.fxml;
    exports pl.comp.javafx.helpers;
    opens pl.comp.javafx.helpers to javafx.fxml;
    exports pl.comp.javafx.converters;
    opens pl.comp.javafx.converters to javafx.fxml;
    exports pl.comp.javafx.language;
    opens pl.comp.javafx.language to javafx.fxml;
}