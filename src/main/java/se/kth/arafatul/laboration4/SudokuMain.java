package se.kth.arafatul.laboration4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.kth.arafatul.laboration4.model.SudokuBoard;
import se.kth.arafatul.laboration4.view.*;

public class SudokuMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        SudokuBoard model = new SudokuBoard();
        SudokuView view = new SudokuView(model);

        GridView matrix = view.getGridView();
        MenuView gameMenu = view.getMenuView();
        CheckHintView checkHintButtons = view.getCheckHintView();
        NumbersView numberButtons = view.getNumbersView();

        borderPane.setTop(gameMenu.getMenuBar());
        borderPane.setCenter(matrix.getNumberPane());
        borderPane.setLeft(checkHintButtons.getButtons());
        borderPane.setRight(numberButtons.getButtons());

        Scene scene = new Scene(borderPane, 415, 342);

        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
