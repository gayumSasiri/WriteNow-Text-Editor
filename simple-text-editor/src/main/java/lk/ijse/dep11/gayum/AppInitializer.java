package lk.ijse.dep11.gayum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        AnchorPane root = FXMLLoader.load((getClass().getResource("/view/SplashScene.fxml")));
        Scene splashScene = new Scene(root);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(splashScene);

        stage.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.fill(Color.TRANSPARENT));
        splashScene.setFill(Color.TRANSPARENT);

        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}
