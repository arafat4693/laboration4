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


    /**
     * Starts a new game by initializing the Sudoku board and filling it with a new puzzle.
     */
    public void newGame(){
        this.board = new Box[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        fillMatrix();
    }

    /**
     * Sets the Sudoku game difficulty level to EASY and starts a new game with an easy-level puzzle.
     */
    public void easyLevel(){
        this.level = SudokuUtilities.SudokuLevel.EASY;
        this.newGame();
    }

    /**
     * Sets the Sudoku game difficulty level to MEDIUM and starts a new game with an medium-level puzzle.
     */
    public void mediumLevel(){
        this.level = SudokuUtilities.SudokuLevel.MEDIUM;
        this.newGame();
    }

    /**
     * Sets the Sudoku game difficulty level to HARD and starts a new game with an hard-level puzzle.
     */
    public void hardLevel(){
        this.level = SudokuUtilities.SudokuLevel.HARD;
        this.newGame();
    }

    /**
     * Updates the value of a Sudoku box at the specified row and column if the box is modifiable.
     *
     * @param row   The row index of the Sudoku box to update.
     * @param col   The column index of the Sudoku box to update.
     * @param value The new value to set for the Sudoku box.
     * @return {@code true} if the box was successfully updated, {@code false} if the box is not modifiable.
     */
    public boolean updateBox(int row, int col, int value){
        Box box = this.board[row][col];
        if((box.getUserChoice() == 0 && box.getVisibility()) || (box.getUserChoice() != 0 && box.getVisibility())) return false;
        box.setUserChoice(value);
        box.setVisibility(true);
        return true;
    }

    /**
     * Clears the value of a Sudoku box at the specified row and column if the box is modifiable.
     *
     * @param row The row index of the Sudoku box to clear.
     * @param col The column index of the Sudoku box to clear.
     */
    public void clearBox(int row, int col){
        Box box = this.board[row][col];
        if(box.getUserChoice() == 0) return;
        box.setUserChoice(0);
        box.setVisibility(false);
    }

    /**
     * Clears the values of all Sudoku boxes in the game, making them empty and non-visible.
     */
    public void clearAllBoxes(){
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                this.clearBox(row, col);
            }
        }
    }

    /**
     * Shows a hint by revealing the value of a random empty Sudoku box, provided that the puzzle is not completely filled.
     *
     * @return {@code true} if a hint was shown, {@code false} if the puzzle is already completely filled.
     */
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

    /**
     * Checks if the Sudoku puzzle is completely filled, meaning that all boxes are visible.
     *
     * @return {@code true} if the puzzle is completely filled, {@code false} otherwise.
     */
    public boolean isCompletelyFilled(){
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                if(!this.board[row][col].getVisibility()) return false;
            }
        }

        return true;
    }

    /**
     * Checks if the current state of the Sudoku board is correct, where each user choice matches the correct value.
     *
     * @return {@code true} if the Sudoku board is correct, {@code false} if there are discrepancies in user choices.
     */
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
                copy[row][col] = this.board[row][col];
            }
        }

        return copy;
    }

    public void setBoard(Box[][] board){
        this.board = board;
    }
}
