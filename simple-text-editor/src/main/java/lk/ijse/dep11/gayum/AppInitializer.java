package lk.ijse.dep11.gayum;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        AnchorPane root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/view/SplashScene.fxml"))));
        Scene splashScene = new Scene(root);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(splashScene);

        stage.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.fill(Color.TRANSPARENT));
        splashScene.setFill(Color.TRANSPARENT);

        stage.centerOnScreen();
        stage.setResizable(false);

        stage.show();

        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(1000),root);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);

        fadeOutTransition.setOnFinished(event -> {
            stage.close();

            try {
                AnchorPane textEditorRoot = FXMLLoader.load(getClass().getResource("/view/TextEditorScene.fxml"));
                Scene textEditorScene = new Scene(textEditorRoot);
                primaryStage.setScene(textEditorScene);
                primaryStage.setTitle("* Untitled Document");
                primaryStage.centerOnScreen();
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fadeOutTransition.setDelay(Duration.seconds(2));
        fadeOutTransition.play();

    }
}
