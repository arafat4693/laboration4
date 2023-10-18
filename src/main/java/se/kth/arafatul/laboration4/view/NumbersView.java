package se.kth.arafatul.laboration4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import se.kth.arafatul.laboration4.controller.SudokuController;

public class NumbersView {
    private VBox buttons;
    private SudokuController controller;

    public NumbersView(SudokuController controller){
        this.controller = controller;
        createButtons();
    }

    private void createButtons(){
        buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(3);
        buttons.setPadding(new Insets(12, 12, 12, 0));

        for (int i = 1; i <= 9; i++) {
            Button button = new Button(Integer.toString(i));
            button.addEventHandler(ActionEvent.ACTION, new ButtonClickHandler(i));
            buttons.getChildren().add(button);
        }

        Button clearBtn = new Button("C");
        clearBtn.addEventHandler(ActionEvent.ACTION, new ButtonClickHandler(10));
        buttons.getChildren().add(clearBtn);
    }

    private class ButtonClickHandler implements EventHandler<ActionEvent>{
        private int value;

        public ButtonClickHandler(int value){
            this.value = value;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println(value);
            controller.setSelectedValue(value);
        }
    }

    public VBox getButtons(){
        return this.buttons;
    }
}