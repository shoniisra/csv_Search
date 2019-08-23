package sample;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Vector;

public class CsvTool {

    private Boolean comillas;

    public String[] GetSearchTags( String txt_tags){
        Vector<String> vctr_tags = new Vector<String>(Arrays.asList(txt_tags.split("\\s*,\\s*")));
        String[] array_tags = vctr_tags.toArray(new String[vctr_tags.size()]);
        return array_tags;
    }

    public int readCsv(String fileName,String[] searchTags,int searchColumn,boolean respectMayus) {
        String[] csvRow;
        int findSomething=0;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            createCSV();
                while((csvRow = csvReader.readNext()) != null) {
                    findSomething+=CompareRow(csvRow,searchTags,searchColumn, respectMayus);
                }
            csvReader.close();
        }catch (Exception o){
            findSomething=-1;
        }
        return findSomething;
    }

    public void createCSV() throws IOException {
        try (
                Writer miwriter = new FileWriter("searchResult.csv", false);
                CSVWriter writer = new CSVWriter(miwriter, ',', '"', "\r\n");
        ){
        }
    }

    public void writeCSV(String[] rowCSV) throws IOException {
        if(this.comillas){
            try (
                    Writer miwriter = new FileWriter("searchResult.csv", true);
                    CSVWriter writer = new CSVWriter(miwriter, ',', '"', "\r\n");
            ){
                writer.writeNext(rowCSV);

            }
        }else{
            try (
                    Writer miwriter = new FileWriter("searchResult.csv", true);
                    CSVWriter writer = new CSVWriter(miwriter, ',', CSVWriter.NO_QUOTE_CHARACTER, "\r\n");
            ){
                writer.writeNext(rowCSV);

            }
        }


    }

    public int CompareRow(String[] csvRow,String[] searchTags,int searchColumn,boolean respectMayus) throws IOException {
        int findSomething=0;
        boolean agregado=false;
        boolean encuentraCoincidencia;
        String[] auxcsvRox= (String []) csvRow.clone();
        String[] auxsearchTags= (String []) searchTags.clone();

        if(!respectMayus){
             auxcsvRox=ArrayToLowerCase(auxcsvRox);
             auxsearchTags=ArrayToLowerCase(auxsearchTags);
        }

        if(searchColumn==0){
            for (String columna : auxcsvRox) {
                for (String etiqueta:auxsearchTags){
                    encuentraCoincidencia = columna.contains(etiqueta);
                    if (encuentraCoincidencia) {
                        writeCSV(csvRow);
                        agregado=true;
                        findSomething++;
                        break;
                    }
                }
                if(agregado){ break; }
                agregado=false;
            }
        }else{
            String columna=auxcsvRox[searchColumn-1];
            for (String etiqueta:auxsearchTags){
                encuentraCoincidencia = columna.contains(etiqueta);
                if (encuentraCoincidencia) {
                    writeCSV(csvRow);
                    findSomething++;
                    break;
                }

            }
        }

        return findSomething;
    }

    private String[] ArrayToLowerCase(String[] mystringarray) {
        for (int i=0;i<mystringarray.length;i++){
            mystringarray[i]= mystringarray[i].toLowerCase();
        }
        return  mystringarray;
    }

    public void setComillas(Boolean comillas) {
        this.comillas = comillas;
    }


}