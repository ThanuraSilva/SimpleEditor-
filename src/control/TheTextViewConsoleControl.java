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
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    public JFXToggleButton tglCaseSense;
    public JFXToggleButton tglRegex;
    public Button btnFind;
    public Button btnReplaceAll;
    public AnchorPane editorConsole;
    public Button btnPrint;

    private boolean textChanges = true;
    private Matcher matcher;
    private int count=0;
    //private Matcher match;

    private void findCountDetails(Matcher matcher) {
        int findCount =0;
        while (matcher.find()){
            txtFSpace.selectRange(matcher.start(), matcher.end());
            findCount++;
        }
        lblTotFinds.setText(String.valueOf(findCount));
        matcher.reset();
    }



    public void initialize(){


        setDisableFields(true);
        txtFSpace.setWrapText(false);

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
        MenuItem m11 = new MenuItem("Find");
        MenuItem m12 = new MenuItem("Replace");
        MenuItem m13 = new MenuItem("Replace All");
        MenuItem m14 = new MenuItem("Wrap Text");


        mnuFile.getItems().add(m1);
        mnuFile.getItems().add(m2);
        mnuFile.getItems().add(m3);
        mnuFile.getItems().add(m4);
        mnuFile.getItems().add(m5);

        mnuEdit.getItems().add(m6);
        mnuEdit.getItems().add(m7);
        mnuEdit.getItems().add(m8);
        mnuEdit.getItems().add(m9);
        mnuEdit.getItems().add(m11);
        mnuEdit.getItems().add(m12);
        mnuEdit.getItems().add(m13);
        mnuEdit.getItems().add(m14);

        mnuHelp.getItems().add(m10);

        txtFSpace.textProperty().addListener((observable, oldValue, newValue) -> {
             if(newValue!=null) mnuEdit.setDisable(false);
             if(newValue!=null) btnSave.setDisable(false);
             if(newValue!=null) btnCopy.setDisable(false);
             if(newValue!=null) btnCut.setDisable(false);
             if(newValue!=null) btnPaste.setDisable(false);
             getWordCount();
             getCharactCount();
        });

        txtFind.textProperty().addListener((observable, oldValue, newValue) -> {
            btnClear.setDisable(false);

        });

        txtReplace.textProperty().addListener((observable, oldValue, newValue) -> {
            btnClear.setDisable(false);
        });

//        txtFSpace.textProperty().addListener((observable, oldValue, newValue) -> {
//            textChanges=true;
//            btnFind.fire();
//
//        });

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

        m11.setOnAction(event -> {
            btnFind.setDisable(false);
            txtFind.setDisable(false);
        });

        m12.setOnAction(event -> {
            txtReplace.setDisable(false);
            btnReplace.setDisable(false);

        });

        m13.setOnAction(event -> {
            txtReplace.setDisable(false);
            btnReplace.setDisable(true);
            btnReplaceAll.setDisable(false);
        });

        m14.setOnAction(event -> {
            txtFSpace.setWrapText(true);
        });

    }

    private void getCharactCount() {
        String characters = txtFSpace.getText();
        int count=0;

        for (int i = 0; i < characters.length(); i++) {
            if(characters.charAt(i)!=' '){
                count++;
            }
        }
        lblCharacterCount.setText(String.valueOf(count));

        //check about the exception regards following

//        int length = Pattern.compile(txtFSpace.getText()).pattern().trim().length();
//        lblCharacterCount.setText(String.valueOf(length));

    }

    private void getWordCount() {
        String inputText = txtFSpace.getText();

        if(inputText==null || inputText.isEmpty()){
            return;

        }else {

            String[] splitText = inputText.split("\\s+");
            lblWordCount.setText(String.valueOf(splitText.length));
        }

    }

    private void setDisableFields(boolean disableFields) {
        mnuEdit.setDisable(disableFields);
        btnSave.setDisable(disableFields);
        btnCopy.setDisable(disableFields);
        btnCut.setDisable(disableFields);
        btnPaste.setDisable(disableFields);
        txtReplace.setDisable(disableFields);
        btnReplace.setDisable(disableFields);
        btnReplaceAll.setDisable(disableFields);
        txtFind.setDisable(disableFields);
        btnFind.setDisable(disableFields);
        btnClear.setDisable(disableFields);

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
         //
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
        btnFind.setDisable(true);
        txtFind.setDisable(true);
        txtReplace.setDisable(true);
        btnReplace.setDisable(true);
        btnReplaceAll.setDisable(true);


       if (!txtFSpace.getText().isEmpty()){
           btnSaveOnAction(actionEvent);
       }
        txtFSpace.clear();
        lblStatus.setText("*untitledText");


    }

    public void btnUpOnAction(ActionEvent actionEvent) {
        btnFind.fire();

    }

    public void btnDwnOnAction(ActionEvent actionEvent) {

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtFind.clear();
        txtReplace.clear();
        lblTotFinds.setText("");

    }

    public void tglCaseSensitiveOnAction(ActionEvent actionEvent) {
        textChanges = true;
        btnFind.fire();
    }

    public void tglRegexOnAction(ActionEvent actionEvent) {
        textChanges = true;
        btnFind.fire();

    }

    public void btnFindOnAction(ActionEvent actionEvent) {

           txtFSpace.deselect();

                if (textChanges) {
                    int flags = 0;

                    if (!tglCaseSense.isSelected()) flags = flags | Pattern.CASE_INSENSITIVE;
                    if (!tglRegex.isSelected()) flags = flags | Pattern.LITERAL;


                    matcher = Pattern.compile(txtFind.getText(), flags).
                            matcher(txtFSpace.getText());
                    findCountDetails(matcher);

                    textChanges = false;

                }
                if (matcher.find()) {
                    txtFSpace.selectRange(matcher.start(), matcher.end());
                    count++;
                    lblNowFinds.setText(String.valueOf(count));

                } else {
                    matcher.reset();
                    count = 0;
                }



    }




    public void btnReplaceAll(ActionEvent actionEvent) {

        String addedNewValue=txtFSpace.getText().replace(txtFind.getText(),txtReplace.getText());
        txtFSpace.setText(addedNewValue);

    }

    public void btnReplaceOnAction(ActionEvent actionEvent) {
        String addedNewValue=txtFSpace.getText().replaceFirst(txtFind.getText(),txtReplace.getText());
        txtFSpace.setText(addedNewValue);


    }


}
