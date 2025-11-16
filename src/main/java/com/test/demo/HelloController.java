package com.test.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;

public class HelloController {
    public GridPane block;
    @FXML
    private Label welcomeText;
    private double xLimitMax, yLimitMax, xLimitMin, yLimitMin;

    @FXML
    protected void onHelloButtonClick() {
        boolean flip = true;
        boolean added = false;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                Pane pane = new Pane();
                if (flip)
                    pane.setStyle("-fx-background-color: green;");
                else
                    pane.setStyle("-fx-background-color: red;");
                pane.setId(i + "" + j);
                pane.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("pressed");
                        System.out.println(pane.getId());
                    }
                });
                pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("released");
                        System.out.println(pane.getId());
                    }
                });
                flip = !flip;

                pane.setPrefSize(30, 30); // preferred size
                pane.setMinSize(30, 30);
                pane.setMaxSize(30, 30);
                block.add(pane, i, j);
            }

        Pane pane = (Pane) block.getChildren().get(block.getChildren().size()-1);
        if (!added) {
            URL url = this.getClass().getResource("/ball.png");
            ImageView view = new ImageView(url.toExternalForm());
            pane.getChildren().add(view);
            view.setFitWidth(30);
            view.setFitHeight(30);
            view.setPreserveRatio(true);
            view.setOnMousePressed(e -> {
                Bounds bound = block.localToScene(block.getBoundsInLocal());
                xLimitMax = bound.getMaxX();
                xLimitMin = bound.getMinX();
                yLimitMax = bound.getMaxY();
                yLimitMin = bound.getMinY();
                System.out.println("clicked");
                e.consume();
            });
            view.setOnMouseDragged((MouseEvent e) ->{
                moveImage(e,view);
            });
        }

    }

    private void moveImage(MouseEvent mouseEvent, ImageView view) {
        double xLimit = mouseEvent.getSceneX();
        double x = mouseEvent.getX();
        if (xLimit <= xLimitMin || xLimit >= xLimitMax)
            x = x - (xLimit - (xLimit <= xLimitMin ? xLimitMin : xLimitMax));
        view.setX(x - (view.getFitWidth() / 2));

        double yLimit = mouseEvent.getSceneY();
        double y = mouseEvent.getY();
        if (yLimit <= yLimitMin || yLimit >= yLimitMax)
            y = y - (yLimit - (yLimit <= yLimitMin ? yLimitMin : yLimitMax));
        view.setY(y - (view.getFitHeight() / 2));
    }
}