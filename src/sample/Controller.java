package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Vector;

public class Controller {

    @FXML
    private TextField txtFileName,txtFileResult;

    @FXML
    private TextArea txtAreaTags;

    @FXML
    private Button btnChooseFile;

    @FXML
    private CheckBox ckMayus,ckColumn;

    @FXML
    private Spinner spColumn;

    public void SeachButton(ActionEvent actionEvent) {

        String txtArchivo=txtFileName.getText();
        String txtTags=txtAreaTags.getText();
        Boolean bolMayus=ckMayus.isSelected();
        Boolean bolColumn=ckColumn.isSelected();
        CsvTool csvCrtl=new CsvTool();
        String[] searchTags;

        if(txtArchivo.isEmpty()||txtTags.isEmpty()){
            System.out.println("Campos incompletos");
            ShowAlert("Campos Inconpletos");

            return;
        }



        searchTags=csvCrtl.GetSearchTags(txtTags);

        int coincidencias=csvCrtl.leerCsv(txtArchivo,searchTags);

        //searchTags=csvCrtl.LeerEtiquetasPorComa(txtTags);
        //searchTags=csvCrtl.leerEtiquetasPorConsola();

        System.out.println("se encontró "+coincidencias+" coincidencias");
        //a.buscarCSV(searchTags);
        // a.crearCsv();
        //a.escribirCSV();


        //System.out.println(searchTags);

        CleanButton();
    }

    public void ShowAlert(String alertMsg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CSV - Alerta de Error");
        alert.setHeaderText(null);
        alert.setContentText(alertMsg);

        alert.showAndWait();
    }
    public void CleanButton(){
        txtFileName.setText("");
        txtFileResult.setText("");
        txtAreaTags.setText("");

    }

    public void ChooseFile(ActionEvent actionEvent) {

    }

    public void ActivarSpinner(ActionEvent actionEvent) {
        if(ckColumn.isSelected()){
            spColumn.setVisible(true);
            spColumn.setDisable(false);
        }else{
            spColumn.setDisable(true);
            spColumn.setVisible(false);
        }
    }
}
