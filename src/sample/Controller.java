package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Vector;

public class Controller {

    @FXML
    public TextField txtFileName,txtFileResult;

    @FXML
    public TextArea txtAreaTags;

    @FXML
    public Button btnChooseFile;

    public void SeachButton(ActionEvent actionEvent) {

        String txtArchivo=txtFileName.getText();
        String txtTags=txtAreaTags.getText();
        String txtResult=txtFileResult.getText();

        if(txtArchivo.isEmpty()||txtTags.isEmpty()||txtResult.isEmpty()){
            System.out.println("campos incompletos");
            return;
        }

        CsvTool csvCrtl=new CsvTool();
        String[] searchTagss;
        Vector<String> searchTags=new Vector<String>();

        //txtArchivo=GetFile();
        searchTags=csvCrtl.LeerEtiquetasPorComa(txtTags);
        //searchTags=a.leerEtiquetas();
        int coincidencias=csvCrtl.leerCsv(txtArchivo,searchTags);
        System.out.println("se encontró "+coincidencias+" coincidencias");
        //a.buscarCSV(searchTags);
        // a.crearCsv();
        //a.escribirCSV();


        //System.out.println(searchTags);

        CleanButton();
    }
    public void CleanButton(){
        txtFileName.setText("");
        txtFileResult.setText("");
        txtAreaTags.setText("");

    }

    public void ChooseFile(ActionEvent actionEvent) {

    }
}
