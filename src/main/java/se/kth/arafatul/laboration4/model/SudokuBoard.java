package se.kth.arafatul.laboration4.model;

import se.kth.arafatul.laboration4.SudokuUtilities;

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
                copy[row][col] = new Box(box.getVisibility(), box.getCorrectValue());
            }
        }

        return copy;
    }
}
