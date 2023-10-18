package se.kth.arafatul.laboration4.view;

import javafx.scene.control.Alert;
import se.kth.arafatul.laboration4.controller.SudokuController;
import se.kth.arafatul.laboration4.model.SudokuBoard;

public class SudokuView {
    private SudokuBoard model;
    private MenuView menuView;
    private GridView gridView;
    private CheckHintView checkHintView;
    private NumbersView numbersView;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public SudokuView(SudokuBoard model){
        this.model = model;

        SudokuController controller = new SudokuController(model, this);

        this.menuView = new MenuView(controller);
        this.gridView = new GridView(controller);
        this.checkHintView = new CheckHintView(controller);
        this.numbersView = new NumbersView(controller);
    }

    public void showAlert(String message) {
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText(message);
        alert.show();
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public GridView getGridView() {
        return gridView;
    }

    public CheckHintView getCheckHintView() {
        return checkHintView;
    }

    public NumbersView getNumbersView() {
        return numbersView;
    }
}
