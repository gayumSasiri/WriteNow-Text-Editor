package lk.ijse.dep11.gayum;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.*;

import java.io.*;

public class TextEditorSceneController {

    public AnchorPane textEditorRoot;
    public MenuItem mbFileNew;
    public MenuItem mbFileExit;
    public MenuItem mbHelpUserGuid;
    public MenuItem mbHelpAboutUs;
    public MenuItem mbFileOpen;
    public HTMLEditor txtHtml;

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

    public void mbFileOpenOnAction(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select text file for open");
        File file = fileChooser.showOpenDialog(textEditorRoot.getScene().getWindow());

        if (file == null) return;
        else {
            String fileName = file.getName();
            ((Stage)(textEditorRoot.getScene().getWindow())).setTitle(fileName);

            try(var fis = new FileReader(file);
                var bis = new BufferedReader(fis)){

                String line = null;
                String content = "";
                int i = 0;
                while ((line = bis.readLine()) != null) {
                    line = line.replace("\t", "&emsp;");
                    content += line + "<br>";
                }
                txtHtml.setHtmlText(content);

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
