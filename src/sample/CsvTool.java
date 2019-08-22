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

    public Vector<String> leerEtiquetas() {
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

    public void leerCsv(String fileName,Vector<String> searchTags) {
        //Vector<String> searchTags=new Vector<String>();
        String[] etiquetasBusqueda = searchTags.toArray(new String[searchTags.size()]);
        System.out.println("Leyendo archivo: "+fileName);
        System.out.println("Buscando: "+etiquetasBusqueda);
        String[] fila = null;
        String[] filaSelected=null;
        boolean agregado=false;
        boolean encuentraCoincidencia=true;
        try {
            crearCSV();
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
             while((fila = csvReader.readNext()) != null) {
                for (String columna : fila) {
                    for (String etiqueta:etiquetasBusqueda){
                        encuentraCoincidencia = columna.contains(etiqueta);
                        if (encuentraCoincidencia) {
                            escribirCSV(fila);
                            agregado=true;
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
        }catch (Exception o){
            System.out.println("Error al leer archivo");
        }
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

    public void buscarCSV(Vector<String> searchTags) {
    }

    public void crearCsv() {
    }
}