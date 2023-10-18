package se.kth.arafatul.laboration4.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import se.kth.arafatul.laboration4.controller.SudokuController;

public class CheckHintView {
    private VBox buttons;
    private SudokuController controller;

    public CheckHintView(SudokuController controller){
        this.controller = controller;
        createButtons();
    }

    private void createButtons(){
        Button checkButton = new Button("Check");
        Button hintButton = new Button("Hint");

        checkButton.addEventHandler(ActionEvent.ACTION, e -> controller.onCheck());
        hintButton.addEventHandler(ActionEvent.ACTION, e -> controller.onHint());

        buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(12);
        buttons.setPadding(new Insets(12, 0, 12, 12));

        buttons.getChildren().addAll(checkButton, hintButton);
    }

    public VBox getButtons(){
        return this.buttons;
    }
}
