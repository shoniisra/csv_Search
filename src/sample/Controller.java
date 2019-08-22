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
    public TextField txtArchivoInicio,txtArchivoResultado;
    @FXML
    public TextArea txtTags;

    @FXML
    public Button btnChooseFile;

    public void Buscar(ActionEvent actionEvent) {
       // System.out.println("todo bien");
        System.out.println(txtArchivoInicio.getText());
        txtArchivoResultado.setText(txtArchivoInicio.getText());

        Vector<String> searchTags=new Vector<String>();
        String txtArchivo="";
        System.out.println("**********Lectura de Archivos csv*********");
        CsvTool a=new CsvTool();

        txtArchivo=txtArchivoInicio.getText();
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
        txtArchivoInicio.setText("");
        txtArchivoResultado.setText("");
        txtTags.setText("");

    }

    public void ChooseFile(ActionEvent actionEvent) {

    }
}
