/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paquetaxo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel
 */
public class Operaciones {

    private DefaultTableModel DefaultTM;

    public Operaciones() {

    }

    public DefaultTableModel setColumnas() {
        DefaultTM = new DefaultTableModel();
        DefaultTM.addColumn("Nombre de la Imagen");
        DefaultTM.addColumn("PID");
        DefaultTM.addColumn("Nombre de Sesión");
        DefaultTM.addColumn("Numero de Sesión");
        DefaultTM.addColumn("Uso de Memoria");
        DefaultTM.addColumn("Estado del Proceso");

        return DefaultTM;
    }

    int makeRandom(int random) {
        int randomInt = 0;
        for (int i = 0; i < 3; i++) {
            randomInt = (int) (random * Math.random());
            //System.out.println("pseudo random int between 1 and 10 : " + randomInt);
        }
        return randomInt;
    }

    public DefaultTableModel getDatos() {
        setColumnas();
        
        int condicion = 0;
        File file = new File("ListaProcesos.csv");
        if(file.exists()){
            condicion = 1;
            System.out.println("existe");
        } else{
            condicion = 0;
            System.out.println("no existe");
        }
        
        switch (condicion) {
            // Si no existe el archivo
            case 0:
                try {
                    String line;
                    Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");
                    // Se pueden usar los parámetros en tasklist  /fo csv /nh para sacar la info en formato CSV
                    BufferedReader input
                            = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    while ((line = input.readLine()) != null) {
                        /* Agrega un numero random*/
                        int x = makeRandom(5);
                        /*Formatea las comas para que no cause problema el tamaño 
                de memoria que también lleva coma */
                        line = line + "," + "\"" + x + "\"";
                        line = line.replace(",", "");
                        line = line.replace("\"\"", "\",\"");
                        /* Separa el chorizo de datos */
                        String[] dataLine = line.split(",");
                        DefaultTM.addRow(dataLine);
                    }

                    input.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 1:
                String inputFileName;
                File inputFile;
                String firstRow;
                Vector<Vector<String>> vectorVectorStringsData = new Vector<Vector<String>>();
                Vector<String> vectorStrings = new Vector<String>();
                Vector<String> vectorColumnIdentifiers = new Vector<String>();
                String[] columnIdentifiers;
                JTable jTable;

                inputFileName = "ListaProcesos.csv";
                inputFile = new File(inputFileName);
                try (FileReader fr = new FileReader(inputFile);
                        BufferedReader br = new BufferedReader(fr)) {
                    firstRow = br.readLine().trim();
                    if (firstRow != null) {
                        // headers:
                        columnIdentifiers = firstRow.split(",");

                        vectorColumnIdentifiers = new Vector<String>();
                        for (int j = 0; j < columnIdentifiers.length; j++) {
                            vectorColumnIdentifiers.add(columnIdentifiers[j]);
                        }
                    }
                    // rows
                    Object[] tableLines = br.lines().toArray();
                    // data rows
                    for (int i = 0; i < tableLines.length; i++) {
                        //System.out.println("4");
                        String line = tableLines[i].toString().trim();
                        String[] dataRow = line.split(",");
                        vectorStrings = new Vector<String>();
                        for (int j = 0; j < dataRow.length; j++) {
                            vectorStrings.add(dataRow[j]);
                        }
                        vectorVectorStringsData.add(vectorStrings);
                    }

                    fr.close();
                } catch (IOException ioe) {
                    System.out.println("Error: " + ioe.getMessage());
                }

                DefaultTM.setDataVector(vectorVectorStringsData, vectorColumnIdentifiers);
                jTable = new JTable(DefaultTM);
        }


        // Si existe el archivo
        return DefaultTM;
    }

    public DefaultTableModel setDatos(String linea) {
        String[] dataLine = linea.split(",");
        DefaultTM.addRow(dataLine);

        return DefaultTM;
    }

    /*public DefaultTableModel getDatos(int estado) {
        switch (estado) {
            case 0:
                try {
                    setColumnas();

                    String line;
                    Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");
                    // Se pueden usar los parámetros en tasklist  /fo csv /nh para sacar la info en formato CSV
                    BufferedReader input
                            = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    while ((line = input.readLine()) != null) {
                        String[] dataLine = line.split(",");
                        DefaultTM.addRow(dataLine);
                    }
                    input.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case 1:
                
                break;
        }
        
        return DefaultTM;
    }*/
}
