package sample;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;


public class CsvTool {

    public String[] GetSearchTags( String txt_tags){
        Vector<String> vctr_tags = new Vector<String>(Arrays.asList(txt_tags.split("\\s*,\\s*")));
        String[] array_tags = vctr_tags.toArray(new String[vctr_tags.size()]);
        return array_tags;
    }

    public int leerCsvRespectMayus(String fileName,String[] searchTags,int searchColumn,boolean respectMayus) {
        String[] csvRow = null;
         int findSomething=0;
        boolean agregado=false;
        boolean encuentraCoincidencia=true;

        try {
            createCSV();
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
                while((csvRow = csvReader.readNext()) != null) {
                    findSomething+=CompareRow(csvRow,searchTags,searchColumn, respectMayus);
                }
            csvReader.close();
        }catch (IOException e){
            System.out.println("Error al intentar leer el archivo especificado");
            findSomething=-1;
        }catch (Exception o){
            System.out.println("Error al leer archivo");
            findSomething=-1;
        }
        return findSomething;
    }

    public void createCSV() throws IOException {
        try (
                Writer miwriter = new FileWriter("busquedaResult.csv", false);
                CSVWriter writer = new CSVWriter(miwriter, ',', '"', "\r\n");
        ){
        }
    }

    public void writeCSV(String[] rowCSV) throws IOException {
        try (
                Writer miwriter = new FileWriter("busquedaResult.csv", true);
                CSVWriter writer = new CSVWriter(miwriter, ',', '"', "\r\n");
        ){
            writer.writeNext(rowCSV);
        }
    }

    public int CompareRow(String[] csvRow,String[] searchTags,int searchColumn,boolean respectMayus) throws IOException {
        String[] filaSelected=null;
        int findSomething=0;
        boolean agregado=false;
        boolean encuentraCoincidencia=true;
        if(!respectMayus){
            searchTags=ArrayToLowerCase(searchTags);
            csvRow=ArrayToLowerCase(csvRow);
        }
        if(searchColumn==0){
            for (String columna : csvRow) {
                for (String etiqueta:searchTags){
                    encuentraCoincidencia = columna.contains(etiqueta);
                    if (encuentraCoincidencia) {
                        writeCSV(csvRow);
                        agregado=true;
                        findSomething++;
                        break;
                    }

                }
                if(agregado){
                    break;
                }

                agregado=false;

            }
        }else{
            String columna=csvRow[searchColumn-1];
            for (String etiqueta:searchTags){
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
}