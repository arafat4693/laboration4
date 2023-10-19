package se.kth.arafatul.laboration4.model;

import javafx.stage.FileChooser;
import se.kth.arafatul.laboration4.model.Box;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class SudokuFileManagement {

    /**
     * Serializes a Sudoku board to a file in binary format. The board is represented as a two-dimensional array of boxes.
     *
     * @param board The Sudoku board to be serialized.
     * @throws IOException If an I/O error occurs during the serialization process.
     */
    public static void serializeToFile(Box[][] board) throws IOException {
        // ...
        // and then, make sure the file always get closed
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter(
                        "sudoku files", "*.sudoku");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save sudoku File");
        File file = fileChooser.showSaveDialog(null);

        ObjectOutputStream oos = null;

        try {
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);

            oos.writeObject(board);

            System.out.println("Serializing successfully completed");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Deserializes a Sudoku board from a file in binary format. The deserialized board is represented as a two-dimensional array of boxes.
     *
     * @return The deserialized Sudoku board as a two-dimensional array of boxes.
     * @throws IOException            If an I/O error occurs during the deserialization process.
     * @throws ClassNotFoundException   If the class for the deserialized objects does not exist in this application.
     */
    @SuppressWarnings("unchecked")
    public static Box[][] deSerializeFromFile() throws IOException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter(
                        "sudoku files", "*.sudoku");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open sudoku File");
        File file = fileChooser.showOpenDialog(null);

        // ...
        // and then, make sure the file always get closed
        ObjectInputStream ois = null;

        try {

            FileInputStream fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);

            // Downcast!
            Box[][] board = (Box[][]) ois.readObject();

            System.out.println("Deserializing successfully completed");
//            System.out.println(projects);

            return board;

        } catch (ClassNotFoundException e) { // !!!
            System.out.println("The class for this type of objects does " + "not exist in this application!");
            throw e;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private SudokuFileManagement() {}
}
