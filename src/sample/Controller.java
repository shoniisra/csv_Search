package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;

import java.util.Vector;

public class Controller {

    @FXML
    public TextField txtArchivoInicio,txtArchivoResultado;
    @FXML
    public TextArea txtTags;

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
        System.out.println("Vector de ingreso "+searchTags);
        //searchTags=a.leerEtiquetas();
        a.leerCsv(txtArchivo,searchTags);
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
}
