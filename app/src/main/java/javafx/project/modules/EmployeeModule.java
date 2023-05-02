package javafx.project.modules;

import javafx.project.enuma.MainStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EmployeeModule extends VBox {
    public EmployeeModule() {
        super();
        this.init();
    }

    public EmployeeModule(String Header) {
        super();

        this.init();
    }

    private void init() {
        Label header1 = new Label("EmployeeModule");
        this.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

        header1.getStyleClass().add("header1");

        this.getChildren().add(header1);
    }

}