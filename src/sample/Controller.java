package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Controller {

    @FXML
    private TextField txtFileName,txtFileResult;

    @FXML
    private TextArea txtAreaTags;

    @FXML
    private Button btnChooseFile;

    @FXML
    private CheckBox ckMayus,ckColumn,ckQuote;

    @FXML
    private Spinner spColumn;

    List<String> lstFile;


    public void SeachButton(ActionEvent actionEvent) throws IOException {
        int num_column=0,num_coincidencias=0;
        String txtArchivo=txtFileName.getText();
        String txtTags=txtAreaTags.getText();
        String txtresult=txtFileResult.getText();
        Boolean bol_respectMayus=ckMayus.isSelected();
        Boolean bol_respectColumn=ckColumn.isSelected();
        CsvTool csvCrtl=new CsvTool();
        String[] searchTags;
        if(txtArchivo.isEmpty()||txtTags.isEmpty()){
            ShowAlert("Error: Campos Incompletos");
            return;
        }

        if(bol_respectColumn) {
            num_column=(int) spColumn.getValue();
        }
        csvCrtl.setComillas(ckQuote.isSelected());
        searchTags=csvCrtl.GetSearchTags(txtTags);
        num_coincidencias=csvCrtl.readCsv(txtArchivo,txtresult,searchTags,num_column,bol_respectMayus);

        if(num_coincidencias==-1){
            ShowAlert("Error: No se pudo Acceder al Archivo");
            return;
        }else if(num_coincidencias==0){
            ShowAlert(" No se Encontraron Coincidencias");
        }else{
            ShowAlert("Búsqueda Exitosa, se han encontrado "+num_coincidencias+" coincidencias");
            CleanButton();
        }
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

    @FXML
    public void ChooseFile(ActionEvent actionEvent) {
        FileChooser fc=new FileChooser();
        fc.setTitle("Escoger Archivo");
        int aux=0;
        fc.getExtensionFilters().add(aux,new ExtensionFilter("Archivo Csv","*.csv"));
        File f=fc.showOpenDialog(null);
        if(f!=null){
            txtFileName.setText(f.getAbsolutePath());
        }
    }

    @FXML
    public void ChooseSaveFile(){

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Archivo");

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Extension *.csv", "*.csv");
            fileChooser.setSelectedExtensionFilter(extFilter);
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);
            if( file!=null) {
                String extension = "";
                String filepath = file.getAbsolutePath();

                int i = filepath.lastIndexOf('.');
                if (i > 0) {
                    extension = filepath.substring(i + 1);
                }

                if (extension.equals("txt") || extension.equals("csv")) {

                } else {
                    filepath = filepath + ".csv";
                }
                txtFileResult.setText(filepath);

            }
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
