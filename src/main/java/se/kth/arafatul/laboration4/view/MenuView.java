package se.kth.arafatul.laboration4.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import se.kth.arafatul.laboration4.controller.SudokuController;

public class MenuView {
    private MenuBar menuBar;
    private SudokuController controller;
    public MenuView(SudokuController controller){
        this.controller = controller;
        createMenuBar();
    }

    private void createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem loadGame = new MenuItem("Load game");
        MenuItem saveGame = new MenuItem("Save game");
        MenuItem exitGame = new MenuItem("Exit");

        loadGame.addEventHandler(ActionEvent.ACTION, e -> controller.onLoadFile());
        saveGame.addEventHandler(ActionEvent.ACTION, e -> controller.onSaveFile());
        exitGame.addEventHandler(ActionEvent.ACTION, e -> controller.handleExit());

        fileMenu.getItems().addAll(loadGame, saveGame, exitGame);

        Menu gameMenu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        Menu difficultyLvl = new Menu("Difficulty");
        MenuItem easyLvl = new MenuItem("Easy");
        MenuItem mediumLvl = new MenuItem("Medium");
        MenuItem hardLvl = new MenuItem("Hard");

        newGame.addEventHandler(ActionEvent.ACTION, e -> controller.initNewGame());
        easyLvl.addEventHandler(ActionEvent.ACTION, e -> controller.initLvlEasy());
        mediumLvl.addEventHandler(ActionEvent.ACTION, e -> controller.initLvlMedium());
        hardLvl.addEventHandler(ActionEvent.ACTION, e -> controller.initLvlHard());

        difficultyLvl.getItems().addAll(easyLvl, mediumLvl, hardLvl);
        gameMenu.getItems().addAll(newGame, difficultyLvl);

        Menu helpMenu = new Menu("Help");
        MenuItem clear = new MenuItem("Clear");
        MenuItem check = new MenuItem("Check");
        MenuItem about = new MenuItem("About");

        about.addEventHandler(ActionEvent.ACTION, e -> controller.onAbout());
        check.addEventHandler(ActionEvent.ACTION, e -> controller.onCheck());
        clear.addEventHandler(ActionEvent.ACTION, e -> controller.onClear());

        helpMenu.getItems().addAll(clear, check, about);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    public MenuBar getMenuBar() {
        return this.menuBar;
    }
}
