package se.kth.arafatul.laboration4.model;
import java.io.*;
import java.nio.file.*;
public class Sudokufilemanager {



    private String filename;
    public Sudokufilemanager(String filename) {
        this.filename = filename;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public Boolean saveGame(SudokuBoard board){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(board);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    public SudokuBoard loadGame(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (SudokuBoard) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
