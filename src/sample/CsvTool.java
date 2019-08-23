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

    public String subircsv(){
        System.out.println("Introduzca la ubicacion del Archivo: ");
        String pathCsv;
        Scanner teclado = new Scanner(System.in);
        pathCsv = teclado.nextLine();
        return pathCsv;

    };

    public Vector<String> LeerEtiquetasPorComa( String txt_tags){
        Vector<String> list = new Vector<String>(Arrays.asList(txt_tags.split("\\s*,\\s*")));
       // String[] list = txt_tags.split("\\s*,\\s*");
        return list;
    }

    public String[] GetSearchTags( String txt_tags){
        Vector<String> vctr_tags = new Vector<String>(Arrays.asList(txt_tags.split("\\s*,\\s*")));
        String[] array_tags = vctr_tags.toArray(new String[vctr_tags.size()]);
        return array_tags;
    }

    public Vector<String> leerEtiquetasPorConsola() {
        Vector<String> searchTags=new Vector<String>();

        String nombre;

        do{
            Scanner teclado = new Scanner(System.in);
            System.out.println("/n Introduzca Etiqueta de busqueda (caso contrario: salir): ");
            nombre = teclado.nextLine();
            if(!nombre.equals("salir")) {searchTags.add(nombre);}

        }while (!nombre.equals("salir"));
        System.out.println(searchTags);

        return  searchTags;
    }

    public int leerCsvRespectMayus(String fileName,String[] searchTags,int searchColumn,boolean respectMayus) {
        String[] csvRow = null;
         int findSomething=0;
        boolean agregado=false;
        boolean encuentraCoincidencia=true;

        try {
            crearCSV();
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

    public int leerCsv(String fileName,String[] etiquetasBusqueda,int num_column) {
        for (int i=0;i<etiquetasBusqueda.length;i++){
            etiquetasBusqueda[i]= etiquetasBusqueda[i].toLowerCase();
            System.out.print(" "+etiquetasBusqueda[i]+"  --  ");
        }
        System.out.println(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><");

        String[] fila = null;
        String[] filaSelected=null;
        int findSomething=0;
        boolean agregado=false;
        boolean encuentraCoincidencia=true;
        String columnaLowerCase;
        try {
            crearCSV();
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
             while((fila = csvReader.readNext()) != null) {
                for (String columna : fila) {
                    for (String etiqueta:etiquetasBusqueda){
                        columnaLowerCase = columna.toLowerCase();
                        encuentraCoincidencia = columnaLowerCase.contains(etiqueta);
                        if (encuentraCoincidencia) {
                            escribirCSV(fila);
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

    public void crearCSV() throws IOException {
        try (
                Writer miwriter = new FileWriter("busquedaResult.csv", false);
                CSVWriter writer = new CSVWriter(miwriter, ',', '"', "\r\n");
        ){
        }
    }

    public void escribirCSV(String[] filaCSV) throws IOException {
        try (
                Writer miwriter = new FileWriter("busquedaResult.csv", true);
                CSVWriter writer = new CSVWriter(miwriter, ',', '"', "\r\n");
        ){
            writer.writeNext(filaCSV);
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
                        escribirCSV(csvRow);
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
                    escribirCSV(csvRow);
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
            System.out.print(" "+mystringarray[i]+"  --  ");
        }
        return  mystringarray;
    }
}