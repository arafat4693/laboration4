package se.kth.arafatul.laboration4.controller;

import javafx.scene.control.Alert;
import se.kth.arafatul.laboration4.model.Box;
import se.kth.arafatul.laboration4.model.SudokuBoard;
import se.kth.arafatul.laboration4.view.SudokuView;

public class SudokuController {
    private SudokuBoard model;
    private SudokuView view;
    private int selectedValue;

    public SudokuController(SudokuBoard model, SudokuView view){
        this.model = model;
        this.view = view;
    }

    public Box[][] getBoard(){
        return this.model.getBoard();
    }

    public void handleExit() {
        // TODO: save drawing to file before exit
        System.exit(0);
    }

    public void initNewGame(){
        model.newGame();
        view.getGridView().newGameUpdate();
    }

    public void initLvlHard(){
        model.hardLevel();
        view.getGridView().newGameUpdate();
    }

    public void initLvlEasy(){
        model.easyLevel();
        view.getGridView().newGameUpdate();
    }

    public void initLvlMedium(){
        model.mediumLevel();
        view.getGridView().newGameUpdate();
    }

    public void setSelectedValue(int value){
        this.selectedValue = value;
    }

    public void onTileSelected(int row, int col){
        if(this.selectedValue == 0) return;

        if(this.selectedValue == 10){
            this.model.clearBox(row, col);
        }else {
            boolean isUpdated = this.model.updateBox(row, col, this.selectedValue);
            if(isUpdated && this.model.isCompletelyFilled()){
                this.view.showAlert("The game is over. All boxed are filled");
            }
        }

        view.getGridView().newGameUpdate();
        this.selectedValue = 0;
    }

    public void onHint(){
        boolean isHint = this.model.showHint();
        if(isHint && this.model.isCompletelyFilled()){
            this.view.showAlert("The game is over. All boxed are filled");
        }
        view.getGridView().newGameUpdate();
    }

    public void onCheck(){
        boolean isCorrect = this.model.checkBoard();
        if(isCorrect){
            this.view.showAlert("Great, numbers entered so far are correct.");
        }else{
            this.view.showAlert("Sorry, not all entered numbers are correct.");
        }
    }

    public void onAbout(){
        this.view.showAlert("Each horizontal row, vertical row and box of 3 × 3 squares should contain a digit only once.");
    }

    public void onClear(){
        this.model.clearAllBoxes();
        view.getGridView().newGameUpdate();
    }
}
