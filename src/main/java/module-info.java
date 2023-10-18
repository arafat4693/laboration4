module se.kth.arafatul.laboration4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.kth.arafatul.laboration4 to javafx.fxml;
    exports se.kth.arafatul.laboration4;
}