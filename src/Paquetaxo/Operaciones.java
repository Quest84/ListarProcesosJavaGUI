/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paquetaxo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel
 */
public class Operaciones {

    private DefaultTableModel DefaultTM;

    public Operaciones() {

    }

    private DefaultTableModel setColumnas() {
        DefaultTM = new DefaultTableModel();
        DefaultTM.addColumn("Nombre de la Imagen");
        DefaultTM.addColumn("PID");
        DefaultTM.addColumn("Nombre de Sesión");
        DefaultTM.addColumn("Numero de Sesión");
        DefaultTM.addColumn("Uso de Memoria");
        DefaultTM.addColumn("Estado del Proceso");

        return DefaultTM;
    }

    int makeRandom() {
        int randomInt = 0;
        for (int i = 0; i < 3; i++) {
            randomInt = (int) (5.0 * Math.random());
            System.out.println("pseudo random int between 1 and 10 : " + randomInt);
        }
        return randomInt;
    }

    public DefaultTableModel getDatos() {
        try {
            setColumnas();

            String line;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");
            // Se pueden usar los parámetros en tasklist  /fo csv /nh para sacar la info en formato CSV
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                int x = makeRandom();
                line = line + "," + x;
                line = line.replace("\",", ";");
                String[] dataLine = line.split(";");
                DefaultTM.addRow(dataLine);
            }

            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

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
