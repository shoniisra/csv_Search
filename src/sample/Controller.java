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
        int num_column=0,num_coincidencias=0;
        String txtArchivo=txtFileName.getText();
        String txtTags=txtAreaTags.getText();
        Boolean bol_respectMayus=ckMayus.isSelected();
        Boolean bol_respectColumn=ckColumn.isSelected();
        CsvTool csvCrtl=new CsvTool();
        String[] searchTags;

        if(txtArchivo.isEmpty()||txtTags.isEmpty()){
            ShowAlert("Error: Campos Incompletos");
            return;
        }

        if(bol_respectColumn){
            num_column=(int) spColumn.getValue();
        }

        searchTags=csvCrtl.GetSearchTags(txtTags);


            num_coincidencias=csvCrtl.leerCsvRespectMayus(txtArchivo,searchTags,num_column,bol_respectMayus);


        System.out.println("se encontró "+num_coincidencias+" coincidencias");

        //searchTags=csvCrtl.LeerEtiquetasPorComa(txtTags);
        //searchTags=csvCrtl.leerEtiquetasPorConsola();


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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10000, 0);
        this.spColumn.setValueFactory(valueFactory);
        if(ckColumn.isSelected()){
            spColumn.setVisible(true);
            spColumn.setDisable(false);
        }else{
            spColumn.setDisable(true);
            spColumn.setVisible(false);

        }
    }
}
