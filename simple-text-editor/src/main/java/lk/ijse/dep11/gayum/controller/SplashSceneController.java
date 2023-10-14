package lk.ijse.dep11.gayum.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.geom.Point2D;

public class SplashSceneController {
    public AnchorPane root;
    public Point2D point;

    public void onMouseMoved(MouseEvent e) {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.setX(e.getScreenX() - point.getX());
        primaryStage.setY(e.getScreenY() - point.getY());
    }

    public void onMouseClick(MouseEvent e) {
        point = new Point2D.Double(e.getX(), e.getY());
    }
}
