package javafx.project.panels;

import io.github.palexdev.materialfx.css.themes.*;
import javafx.geometry.*;
import javafx.project.components.*;
import javafx.project.enuma.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Dashboard extends BorderPane {
    Stage stage, newStage;

    public Dashboard(Stage stage) {
        super();

        this.stage = stage;

        this.newStage = new Stage();

        super.autosize();
        // super.setPadding(new Insets(6, 12, 6, 12));

        Scene scene = new Scene(this, 800, 600);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        init();

        newStage.centerOnScreen();
        newStage.setResizable(false);
        newStage.setTitle("Dashboard");
        newStage.setScene(scene);
        newStage.show();
    }

    private void init() {
        this.setTop(new TopBar());
        this.setCenter(new MainDash());
        this.setLeft(new SideBar());
    }

    private class TopBar extends BorderPane {
        public TopBar() {
            super();// "-fx-border-insets: 4;" +
            super.setPadding(new Insets(16));
            super.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
            super.getStyleClass().add("topbar");

            this.init();
        }

        private void init() {
            HBox box = new HBox();
            box.setPadding(new Insets(8));
            box.setSpacing(16);

            MainBtn logout = new MainBtn("Logout");

            box.setAlignment(Pos.CENTER);

            logout.setBgColor(Elements.DANGER_COLOR.getName());
            logout.setTextColor("White");
            logout.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));

            logout.setOnAction(event -> {
                stage.setTitle("Login Panel");
                stage.show();
                newStage.close();
            });

            ImgIcon icon = new ImgIcon("src/main/resources/img/user.png");

            box.getChildren().addAll(icon.getBtnIcon(), logout);

            this.setRight(box);
        }

    }

    private class MainDash extends VBox {
        public MainDash() {
            super();
            super.setSpacing(12);
            super.setPadding(new Insets(8, 16, 4, 32));
            VBox.setMargin(this, new Insets(8));

            this.init();
        }

        private void init() {
            Card card = new Card();

            ScrollPanel panel = new ScrollPanel();
            VBox.setVgrow(panel, Priority.ALWAYS);
            VBox.setMargin(panel, new Insets(8, 4, 8, 4));

            panel.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            panel.setMaxHeight(card.getMaxHeight());

            VBox container = new VBox();
            container.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
            container.getStyleClass().add("container");
            container.setSpacing(12);
            container.setMaxWidth(Double.MAX_VALUE);
            container.setMaxHeight(Double.MAX_VALUE);
            container.setPadding(new Insets(4, 8, 2, 8));
            VBox.setMargin(container, new Insets(8));

            Label label = new Label("GG");
            label.autosize();
            label.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a;");

            container.getChildren().addAll(label);

            card.setMaxWidth(newStage.getMaxWidth());
            card.getChildren().add(container);

            this.getChildren().add(card);
        }
    }

    private class SideBar extends VBox {
        public SideBar() {
            super();
            super.setSpacing(12);
            super.setPrefHeight(newStage.getMaxHeight());
            super.setPrefWidth(200);
            // super.autosize();

            this.init();
        }

        private void init() {
            this.setPadding(new Insets(18, 8, 12, 8));
            // this.setStyle("-fx-background-color: #fafafa;" +
            // "-fx-background-insets: -1 -5 -1 -1;" +
            // "-fx-background-radius: 0 16 8 0;" +
            // "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.30), 16, 0.05, 4, 8);");
            this.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
            this.getStyleClass().addAll("sidebar");

            Label headerLabel = new Label("Side Bar");
            headerLabel.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");
            headerLabel.setGraphicTextGap(4);
            this.getChildren().add(headerLabel);

            Label menuLabel = new Label("Components");
            menuLabel.setStyle(Elements.HEADER2.getName() + "-fx-text-fill:#484b6a");
            menuLabel.setPadding(new Insets(20, 8, 2, 8));
            this.getChildren().add(menuLabel);

            ScrollPanel scrollPane = new ScrollPanel();
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            VBox.setVgrow(scrollPane, Priority.ALWAYS);
            Insets scrollPaneMargin = new Insets(0, 5, 10, 5);
            VBox.setMargin(scrollPane, scrollPaneMargin);

            VBox navBar = new VBox();
            navBar.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
            navBar.getStyleClass().add("navbar");
            navBar.setSpacing(10);
            navBar.setMaxWidth(Double.MAX_VALUE);
            navBar.setMaxHeight(Double.MAX_VALUE);

            for (int i = 1; i <= 5; i++) {
                MainBtn btn = new MainBtn("hello");

                btn.setSize((int) navBar.getMaxWidth(), 100);
                btn.setPadding(new Insets(12, 8, 12, 8));
                btn.setBgColor(Elements.INFO_COLOR.getName());
                btn.setRippleColor(Color.web("#8fcee2"));
                btn.setTextColor("black; -fx-font-weight: bold; -fx-font-size:14px");
                btn.buttonTypeProperty().set(io.github.palexdev.materialfx.enums.ButtonType.RAISED);
                btn.depthLevelProperty().set(io.github.palexdev.materialfx.effects.DepthLevel.LEVEL1);

                navBar.getChildren().add(btn);
            }

            scrollPane.setContent(navBar);

            this.getChildren().add(scrollPane);
        }

    }
}
