package control;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

import java.io.FileWriter;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import java.nio.file.Paths;



public class TheTextViewConsoleControl {
    public Button btnSave;
    public Button btnOpen;

    public TextArea txtFSpace;
    public MenuButton mnuFile;
    public MenuButton mnuEdit;
    public MenuButton mnuHelp;

    public Label lblStatus;
    public Button btnCopy;
    public Button btnCut;
    public Button btnPaste;

    public Button btnNew;
    public Button btnUp;
    public TextField txtFind;
    public TextField txtReplace;
    public Button btnReplace;
    public Button btnClear;
    public Button btnDwn;
    public Label lblWordCount;
    public Label lblCharacterCount;
    public Label lblNowFinds;
    public Label lblTotFinds;
    public Button Print;
    public JFXToggleButton tglCaseSense;
    public JFXToggleButton tglRegex;
    public Button btnFind;
    public Button btnReplaceAll;
    public AnchorPane txtEditorConsole;

    public void initialize(){

       setDisableFields(true);

        MenuItem m1 = new MenuItem("New");
        MenuItem m2 = new MenuItem("Open");
        MenuItem m3 = new MenuItem("Save");
        MenuItem m4 = new MenuItem("Print");
        MenuItem m5 = new MenuItem("Exit");
        MenuItem m6 = new MenuItem("Cut");
        MenuItem m7 = new MenuItem("Copy");
        MenuItem m8 = new MenuItem("Paste");
        MenuItem m9 = new MenuItem("Select All");
        MenuItem m10 = new MenuItem("About us");


        mnuFile.getItems().add(m1);
        mnuFile.getItems().add(m2);
        mnuFile.getItems().add(m3);
        mnuFile.getItems().add(m4);
        mnuFile.getItems().add(m5);

        mnuEdit.getItems().add(m6);
        mnuEdit.getItems().add(m7);
        mnuEdit.getItems().add(m8);
        mnuEdit.getItems().add(m9);

        mnuHelp.getItems().add(m10);

        txtFSpace.textProperty().addListener((observable, oldValue, newValue) -> {
             if(newValue!=null) mnuEdit.setDisable(false);
             if(newValue!=null) btnSave.setDisable(false);
             if(newValue!=null) btnCopy.setDisable(false);
             if(newValue!=null) btnCut.setDisable(false);
             if(newValue!=null) btnPaste.setDisable(false); 



        });

        m1.setOnAction(this::btnNewOnAction);

        m2.setOnAction(event -> {
            try {
                btnOpenOnAction(event);
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Please select a File").show();
            }
        });

        m3.setOnAction(event -> {
            try {
                btnSaveOnAction(event);
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Please Save the File").show();
            }
        });

        m5.setOnAction(event -> {
            if(txtFSpace.getText().isEmpty()){
                System.exit(0);
            }else {
                btnSaveOnAction(event);

            }
        });

        m6.setOnAction(this::btnCutOnAction);

        m7.setOnAction(this::btnCopyOnAction);

        m8.setOnAction(this::btnPasteOnAction);

        m9.setOnAction(event -> {
            txtFSpace.selectAll();
        });

        m10.setOnAction(event -> {
            URL resource = getClass().getResource("../view/AboutApplication.fxml");
            Parent load = null;
            try {
                load = FXMLLoader.load(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("About us");
            stage.show();

        });

    }

    private void setDisableFields(boolean disableFields) {
        mnuEdit.setDisable(disableFields);
        btnSave.setDisable(disableFields);
        btnCopy.setDisable(disableFields);
        btnCut.setDisable(disableFields);
        btnPaste.setDisable(disableFields);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        btnSave.setDisable(false);
            try {
                FileChooser fileChooserSave = new FileChooser();
                fileChooserSave.setTitle("Select a file");
                FileChooser.ExtensionFilter txt_files = new FileChooser.ExtensionFilter("*.txt", "txt files");
                File file = fileChooserSave.showSaveDialog(null);
                FileWriter fwr = new FileWriter(file);
                fwr.write(txtFSpace.getText());
                lblStatus.setText(file.getName());
                fwr.close();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Please Save the File").show();
            }

    }

    public void btnOpenOnAction(ActionEvent actionEvent) {
        txtFSpace.setDisable(false);

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a file");
            File fileOpen = fileChooser.showOpenDialog(null);
            byte[] bytes = Files.readAllBytes(Paths.get(fileOpen.getAbsolutePath()));
            txtFSpace.setText((new String(bytes)));

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please select a File").show();
        }

    }
    public void btnPrintOnAction(ActionEvent actionEvent) {
          //to be programed
    }
    
    public void btnCopyOnAction(ActionEvent actionEvent) {
        txtFSpace.copy();
    }

    public void btnCutOnAction(ActionEvent actionEvent) {
        txtFSpace.cut();
    }

    public void btnPasteOnAction(ActionEvent actionEvent) {
        txtFSpace.paste();
    }

   

    public void btnNewOnAction(ActionEvent actionEvent) {
        setDisableFields(false);
       if (!txtFSpace.getText().isEmpty()){
           btnSaveOnAction(actionEvent);
       }
        txtFSpace.clear();
        lblStatus.setText("*untitledText");


    }

    public void btnUpOnAction(ActionEvent actionEvent) {



    }

    public void btnReplaceOnAction(ActionEvent actionEvent) {

    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }

    public void btnDwnOnAction(ActionEvent actionEvent) {
    }

    public void tglCaseSensitiveOnAction(ActionEvent actionEvent) {
    }

    public void tglRegexOnAction(ActionEvent actionEvent) {
    }

    public void btnFindOnAction(ActionEvent actionEvent) {
    }

    public void btnReplaceAll(ActionEvent actionEvent) {
    }
}
