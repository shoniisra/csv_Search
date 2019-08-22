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
    public TextArea txtTags;

    @FXML
    public Button btnChooseFile;

    public void SeachButton(ActionEvent actionEvent) {

        Vector<String> searchTags=new Vector<String>();
        String txtArchivo="";
        CsvTool a=new CsvTool();

        txtArchivo=txtFileName.getText();
        searchTags=a.LeerEtiquetasPorComa(txtTags.getText());
        //searchTags=a.leerEtiquetas();
        int coincidencias=a.leerCsv(txtArchivo,searchTags);
        System.out.println("se encontró "+coincidencias+" coincidencias");
        //a.buscarCSV(searchTags);
        // a.crearCsv();
        //a.escribirCSV();


        //System.out.println(searchTags);

        Clean();
    }
    public void Clean(){
        txtFileName.setText("");
        txtFileResult.setText("");
        txtTags.setText("");

    }

    public void ChooseFile(ActionEvent actionEvent) {

    }
}
