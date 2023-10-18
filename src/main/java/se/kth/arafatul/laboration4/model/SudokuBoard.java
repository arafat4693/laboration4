package se.kth.arafatul.laboration4.model;

import se.kth.arafatul.laboration4.SudokuUtilities;

import java.util.ArrayList;
import java.util.Random;

public class SudokuBoard {
    private Box[][] board;
    private SudokuUtilities.SudokuLevel level;

    public SudokuBoard(SudokuUtilities.SudokuLevel level){
        this.level = level;
        this.board = new Box[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        fillMatrix();
    }

    public SudokuBoard(){
        this(SudokuUtilities.SudokuLevel.EASY);
    }

    public void newGame(){
        this.board = new Box[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        fillMatrix();
    }

    public void easyLevel(){
        this.level = SudokuUtilities.SudokuLevel.EASY;
        this.newGame();
    }

    public void mediumLevel(){
        this.level = SudokuUtilities.SudokuLevel.MEDIUM;
        this.newGame();
    }

    public void hardLevel(){
        this.level = SudokuUtilities.SudokuLevel.HARD;
        this.newGame();
    }

    public boolean updateBox(int row, int col, int value){
        Box box = this.board[row][col];
        if((box.getUserChoice() == 0 && box.getVisibility()) || (box.getUserChoice() != 0 && box.getVisibility())) return false;
        box.setUserChoice(value);
        box.setVisibility(true);
        return true;
    }

    public void clearBox(int row, int col){
        Box box = this.board[row][col];
        if(box.getUserChoice() == 0) return;
        box.setUserChoice(0);
        box.setVisibility(false);
    }

    public void clearAllBoxes(){
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                this.clearBox(row, col);
            }
        }
    }

    public boolean showHint(){
        if(isCompletelyFilled()) return false;

        ArrayList<BoxPos> emptyBoxes = new ArrayList<>();
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                if(!this.board[row][col].getVisibility()) emptyBoxes.add(new BoxPos(row, col));
            }
        }

        Random random = new Random();
        BoxPos boxPos = emptyBoxes.get(random.nextInt(emptyBoxes.size()));

        this.board[boxPos.row][boxPos.col].setVisibility(true);
        return true;
    }

    private class BoxPos{
        private int row;
        private int col;

        public BoxPos(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public boolean isCompletelyFilled(){
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                if(!this.board[row][col].getVisibility()) return false;
            }
        }

        return true;
    }

    public boolean checkBoard() {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                Box box = this.board[row][col];
                if(box.getUserChoice() == 0) continue;
                if(box.getCorrectValue() != box.getUserChoice()) return false;
            }
        }

        return true;
    }

    private void fillMatrix(){
        int [][][] boardValues = SudokuUtilities.generateSudokuMatrix(this.level);

        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                if(boardValues[row][col][0] != 0){
                    this.board[row][col] = new Box(true, boardValues[row][col][1]);
                }else{
                    this.board[row][col] = new Box(false, boardValues[row][col][1]);
                }
            }
        }
    }

    public Box[][] getBoard() {
        Box[][] copy = new Box[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];

        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                Box box = this.board[row][col];
//                copy[row][col] = new Box(box.getVisibility(), box.getCorrectValue());
                copy[row][col] = box;
            }
        }

        return copy;
    }
}
