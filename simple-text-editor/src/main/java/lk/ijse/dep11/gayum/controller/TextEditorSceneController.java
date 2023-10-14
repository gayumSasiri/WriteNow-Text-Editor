package lk.ijse.dep11.gayum.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.*;

import java.io.*;
import java.util.Optional;

public class TextEditorSceneController {

    public AnchorPane textEditorRoot;
    public MenuItem mbFileNew;
    public MenuItem mbFileExit;
    public MenuItem mbHelpUserGuid;
    public MenuItem mbHelpAboutUs;
    public MenuItem mbFileOpen;
    public TextArea txtArea;
    public MenuItem mbFileSaveAs;
    public MenuItem mbFilePrint;
    public MenuItem mbSave;
    public MenuItem mbEditCut;
    public MenuItem mbEditCopy;
    public MenuItem mbEditPaste;
    public MenuItem mbEditSelectAll;
    public Menu help;
    public Menu edit;
    public Menu file;
    private File currentFile;
    public void initialize(){
        txtArea.accessibleTextProperty().addListener((val,prev,curr)->{

        });
    }

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
        if(txtArea.getText().isEmpty()){
            Stage stage = (Stage) textEditorRoot.getScene().getWindow();
            stage.close();
            return;
        }
        Alert conformationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save file before exit?",
                ButtonType.NO, ButtonType.CANCEL, ButtonType.YES);
        Optional<ButtonType> buttonType = conformationAlert.showAndWait();
        if (buttonType.isEmpty() || buttonType.get()==ButtonType.CANCEL){
            actionEvent.consume();
            return;
        } else if (buttonType.get() == ButtonType.YES) {
            mbSaveOnAction(actionEvent);
            Stage stage = (Stage) textEditorRoot.getScene().getWindow();
            stage.close();
        }else {
            Stage stage = (Stage) textEditorRoot.getScene().getWindow();
            stage.close();
        }
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
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Files (*.java)","*.java"));
        fileChooser.setTitle("Select text file for open");
        currentFile = fileChooser.showOpenDialog(textEditorRoot.getScene().getWindow());

        if (currentFile == null) return;
        else {
            String fileName = currentFile.getName();
            ((Stage)(textEditorRoot.getScene().getWindow())).setTitle(fileName);
            readFile(currentFile);
        }
    }

    private void readFile(File currentFile) {
        try(var fis = new FileReader(currentFile);
            var bis = new BufferedReader(fis)){
            String line = null;
            String content = "";
            while ((line = bis.readLine()) != null) {
                content += line +"\n" ;
            }
            txtArea.setText(content);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mbFileSaveAs(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the text file");
        fileChooser.setInitialFileName("Untitled Document.txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Text Files (*.txt, *.html)", "*.txt", "*.html"));
        currentFile = fileChooser.showSaveDialog(textEditorRoot.getScene().getWindow());
        if (currentFile == null) return;
        try {
            currentFile.createNewFile();
            try(var fos = new FileWriter(currentFile);
                var bos = new BufferedWriter(fos)){
                String content = txtArea.getText();
                bos.write(content);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mbFilePrintOnAction(ActionEvent actionEvent) {
        Printer defaultPrinter = Printer.getDefaultPrinter();
        if (defaultPrinter == null){
            new Alert(Alert.AlertType.ERROR,"No default printer has been configured,Try again!").showAndWait();
            return;
        }
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob.showPrintDialog(textEditorRoot.getScene().getWindow())){
            PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
            printerJob.getJobSettings().setPageLayout(pageLayout);
            boolean success = printerJob.printPage(textEditorRoot);
            if (success){
                printerJob.endJob();
            }
        }
    }

    public void mbSaveOnAction(ActionEvent actionEvent) {
        if (currentFile == null){
            mbFileSaveAs(actionEvent);
        }else{
            try {
                try(var fos = new FileWriter(currentFile);
                    var bos = new BufferedWriter(fos)){
                    String content = txtArea.getText();
                    bos.write(content);
                    bos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void textEditorRootOnDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void textEditorRootOnDragDropped(DragEvent dragEvent) {
        dragEvent.setDropCompleted(true);
        File droppedFile = dragEvent.getDragboard().getFiles().get(0);
        readFile(droppedFile);
    }

    public void mbEditCutOnAction(ActionEvent actionEvent) {
        String selectedText = txtArea.getSelectedText();
        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);

            int selectionStart = txtArea.getSelection().getStart();
            int selectionEnd = txtArea.getSelection().getEnd();
            txtArea.deleteText(selectionStart, selectionEnd);
        }
    }

    public void mbEditCopyOnAction(ActionEvent actionEvent) {
        String selectedText = txtArea.getSelectedText();
        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
        }

    }

    public void mbEditPasteOnAction(ActionEvent actionEvent) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        DataFormat plainText = DataFormat.PLAIN_TEXT;
        if (clipboard.hasContent(plainText)) {
            String pastedText = (String) clipboard.getContent(plainText);
            txtArea.appendText(pastedText);
        }
    }

    public void mbEditSelectAllOnAction(ActionEvent actionEvent) {
        txtArea.selectAll();
    }
}
