package pl.comp.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.javafx.SudokuApplication;
import pl.comp.model.Difficulty;

public class NewFormController {

    private static Logger logger = LoggerFactory.getLogger(NewFormController.class);

    ObservableList<String> difficultyList;

    @FXML
    private ChoiceBox difficultyChoice;

    @FXML
    public void initialize() {
        difficultyList = FXCollections.observableArrayList(
            SudokuApplication.bundle.getString("game.difficulty.easy"),
            SudokuApplication.bundle.getString("game.difficulty.medium"),
            SudokuApplication.bundle.getString("game.difficulty.hard"),
            SudokuApplication.bundle.getString("game.difficulty.ultra")
        );
        difficultyChoice.setItems(difficultyList);
        difficultyChoice.setValue(SudokuApplication.bundle.getString("game.difficulty.easy"));
    }

    public void onOkButtonClick(ActionEvent actionEvent) throws IOException {
        Object value = difficultyChoice.getValue();
        String temp = value.toString();

        if (temp == SudokuApplication.bundle.getString("game.difficulty.easy")) {
            SudokuApplication.difficulty = Difficulty.Easy;
        } else if (temp == SudokuApplication.bundle.getString("game.difficulty.medium")) {
            SudokuApplication.difficulty = Difficulty.Medium;
        } else if (temp == SudokuApplication.bundle.getString("game.difficulty.hard")) {
            SudokuApplication.difficulty = Difficulty.Hard;
        } else if (temp == SudokuApplication.bundle.getString("game.difficulty.ultra")) {
            SudokuApplication.difficulty = Difficulty.Ultra;
        } else {
            logger.info("onOkButtonClick: Wrong ChoiceBox value!"); // ! Should be an exception !
            return;
        }

        SudokuApplication.prepareNewGame();

        URL url = SudokuApplication.class.getResource(SudokuApplication.SUDOKU_BOARD_VIEW);
        FXMLLoader loader = new FXMLLoader(url, SudokuApplication.bundle);
        Scene boardScene = new Scene(loader.load(), 440, 440);
        SudokuApplication.globalStage.setScene(boardScene);
        SudokuApplication.globalStage.show();
    }
}
