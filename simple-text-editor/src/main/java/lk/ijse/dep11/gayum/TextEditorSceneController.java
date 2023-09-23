package lk.ijse.dep11.gayum;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class TextEditorSceneController {

    public AnchorPane textEditorRoot;
    public MenuItem mbFileNew;
    public MenuItem mbFileExit;
    public MenuItem mbHelpUserGuid;
    public MenuItem mbHelpAboutUs;

    public void mbFileNewOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane newTextEditorScene = FXMLLoader.load(getClass().getResource("/view/TextEditorScene.fxml"));
        Scene newScene = new Scene(newTextEditorScene);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle("* Untitled Document");
        newStage.centerOnScreen();
        newStage.show();

    }

    public void mbFileExitOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) textEditorRoot.getScene().getWindow();
        stage.close();
    }

    public void mbHelpUserGuidOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/UserGuidScene.fxml"));
        Scene userGuidScene = new Scene(root);
        Stage userGuidStage = new Stage();

        userGuidStage.initModality(Modality.WINDOW_MODAL);
        userGuidStage.initOwner(textEditorRoot.getScene().getWindow());

        userGuidStage.setScene(userGuidScene);
        userGuidStage.setTitle("User Guide");
        userGuidStage.setResizable(false);
        userGuidStage.centerOnScreen();
        userGuidStage.show();
    }

    public void mbHelpAboutUsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AboutUsScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(textEditorRoot.getScene().getWindow());

        stage.setScene(scene);
        stage.setTitle("About Us");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
