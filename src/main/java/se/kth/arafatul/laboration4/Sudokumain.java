package se.kth.arafatul.laboration4;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import se.kth.arafatul.laboration4.GUI.GridView;
import se.kth.arafatul.laboration4.model.SudokuBoard;

public class Sudokumain extends HelloApplication{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        GridView view = new GridView();
        SudokuBoard sudokuBoard = new SudokuBoard();

        MenuBar menuBar = menu();
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(leftbuttons());
        root.setCenter(view.getNumberPane());
        root.setRight(rightbuttons());
        primaryStage.setTitle("Sudoku");

        primaryStage.setScene(new Scene(root, 410, 350));
        primaryStage.show();
    }
    private MenuBar menu(){
        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("file");
        MenuItem exitItem = new MenuItem("exit");
        MenuItem saveItem = new MenuItem("save");
        MenuItem loadItem = new MenuItem("load");
        exitItem.setOnAction(e -> System.exit(0));
        file.getItems().add(exitItem);
        file.getItems().add(saveItem);
        file.getItems().add(loadItem);


        Menu game = new Menu("game");
        MenuItem newItem = new MenuItem("New Game");
        //start new game
        game.getItems().add(newItem);
        Menu difficultyMenu = new Menu("Difficulty");
        MenuItem easyItem = new MenuItem("Easy");
        MenuItem mediumItem = new MenuItem("Medium");
        MenuItem hardItem = new MenuItem("Hard");
        difficultyMenu.getItems().addAll(easyItem, mediumItem, hardItem);
        game.getItems().add(difficultyMenu);

        Menu help = new Menu("help");
        MenuItem aboutItem = new MenuItem("About");
        help.getItems().add(aboutItem);

        menuBar.getMenus().addAll(file, game, help);
        return menuBar;
    }
    private VBox leftbuttons(){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        Button checkButton = new Button("Check");
        vBox.getChildren().add(checkButton);

        Button hintButton = new Button("Hint");
        vBox.getChildren().add(hintButton);
        return vBox;
    }
    private VBox rightbuttons(){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(1);
        for (int i = 0; i<= 9; i++){
            Button numberButton = new Button(Integer.toString(i));
            vBox.getChildren().add(numberButton);
        }
        Button numberButton = new Button("C");
        vBox.getChildren().add(numberButton);
        return vBox;
    }
}
