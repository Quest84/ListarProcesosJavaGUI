/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paquetaxo;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Manuel
 */
public class Ventana extends javax.swing.JFrame {

    private final Operaciones OP;

    public Ventana() {
        setTitle("Listado de Procesos del Sistema");
        initComponents();
        OP = new Operaciones();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        getCell();
    }

    private void listar() {
        jtb_Tabla.setModel(OP.getDatos());
    }

    private void getCell() {
        ListSelectionModel cellSelectionModel = jtb_Tabla.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = jtb_Tabla.getSelectedRows();
                int[] selectedColumns = jtb_Tabla.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        if (selectedColumns[j] == 0 || selectedColumns[j] == 1) {
                            selectedData = (String) jtb_Tabla.getValueAt(selectedRow[i], selectedColumns[j]);
                        }
                    }
                }
                jtf_EliminarProceso.setText(selectedData);
                System.out.println(selectedData);
            }
        });
    }

    private void matar() {
        String Proceso = jtf_EliminarProceso.getText();
        if (Proceso.equals("")) {
            //System.out.println("Nada por elimnar");
            listar();
        } else {
            try {
                System.out.println(Proceso);
                Runtime.getRuntime().exec("taskkill /F /IM " + Proceso);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(this, "Proceso finalizado");
        listar();
    }

    private void crear() {
        boolean condicion = false;
        String nombre = "";
        String NomSesion = "";

        if (jtf_Nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ingresa un nombre");
        } else {
            nombre = jtf_Nombre.getText();
            condicion = true;
        }

        int PID = OP.makeRandom(1000);

        if (jtf_NomSesion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ingresa un Nombre de Sesión");
            condicion = false;
        } else {
            NomSesion = jtf_NomSesion.getText();
            condicion = true;
        }

        int NumSesion = OP.makeRandom(1);

        int Memoria = OP.makeRandom(100000);

        int Estado = OP.makeRandom(5);

        if (condicion == true) {
            String chorizo = "\"" + nombre + "\"," + "\"" + PID + "\"," + "\"" + NomSesion + "\"," + "\"" + NumSesion + "\","
                    + "\"" + Memoria + " KB\"," + "\"" + Estado + "\",";

            limpiarCajas();

            System.out.println(chorizo);

            OP.setDatos(chorizo);

        } else {
            // do nothing
        }

    }

    private void limpiarCajas() {
        jtf_Nombre.setText("");
        jtf_PID.setText("");
        jtf_NomSesion.setText("");
        jtf_NumSesion.setText("");
        jtf_Memoria.setText("");
        jtf_EstadoProceso.setText("");
    }

    /*private void descargar() {
        try {
            String line;
            PrintWriter out = new PrintWriter("ListaProcesos.csv");
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv");
            // Se pueden usar los parámetros en tasklist  /fo csv /nh para sacar la info en formato CSV /nv para quitar el nombre de columnas
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) { 
                //String[] dataLine = line.split(",");
                try {
                    out.println(line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out.close();
            JOptionPane.showMessageDialog(this, "Lista de procesos descargada\nRevisa la raiz del proyecto");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    private void convertir() {
        try {
            TableModel model = jtb_Tabla.getModel();
            PrintWriter out = new PrintWriter("ListaProcesos.csv");
            
            

            for (int i = 0; i < model.getColumnCount(); i++) {
                out.write(model.getColumnName(i) + ",");
            }

            out.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    out.write(model.getValueAt(i, j).toString() + ",");
                }
                out.write("\n");
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_Tabla = new javax.swing.JTable();
        btn_CargarProcesos = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jtb_NuevoProceso = new javax.swing.JButton();
        jtf_Nombre = new javax.swing.JTextField();
        jtb_EliminarProceso = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtf_EliminarProceso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jtf_PID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtf_NomSesion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtf_NumSesion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtf_Memoria = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtf_EstadoProceso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtb_Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_Tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtb_Tabla);

        btn_CargarProcesos.setText("Cargar Procesos");
        btn_CargarProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CargarProcesosActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        jtb_NuevoProceso.setText("Nuevo Proceso");
        jtb_NuevoProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtb_NuevoProcesoActionPerformed(evt);
            }
        });

        jtb_EliminarProceso.setText("Eliminar Proceso");
        jtb_EliminarProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtb_EliminarProcesoActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nombre del Proceso");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtf_EliminarProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_EliminarProcesoActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre del Proceso a Eliminar");

        jButton1.setText("Descargar Lista de Procesos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtf_PID.setEnabled(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PID");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nombre de Sesión");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtf_NumSesion.setEnabled(false);
        jtf_NumSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_NumSesionActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Numero de Sesion");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtf_Memoria.setEnabled(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Uso de Memoria");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtf_EstadoProceso.setEnabled(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Estado de Proceso");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_CargarProcesos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jtf_Nombre)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jtf_PID)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_NomSesion)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_NumSesion)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_Memoria)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_EstadoProceso)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtb_NuevoProceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtb_EliminarProceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtf_EliminarProceso)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btn_CargarProcesos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jtf_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_PID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_NomSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_NumSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_Memoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_EstadoProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtb_NuevoProceso)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jtb_EliminarProceso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtf_EliminarProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btn_Salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        JOptionPane.showMessageDialog(this, "Manuel Ruiz IC-502");
        this.dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_CargarProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CargarProcesosActionPerformed
        listar();
        btn_CargarProcesos.setEnabled(false);
    }//GEN-LAST:event_btn_CargarProcesosActionPerformed

    private void jtb_EliminarProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtb_EliminarProcesoActionPerformed
        matar();
    }//GEN-LAST:event_jtb_EliminarProcesoActionPerformed


    private void jtf_EliminarProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_EliminarProcesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_EliminarProcesoActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        convertir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtb_NuevoProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtb_NuevoProcesoActionPerformed
        crear();
    }//GEN-LAST:event_jtb_NuevoProcesoActionPerformed

    private void jtf_NumSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_NumSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_NumSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al aplicar el estilo al sistema", JOptionPane.ERROR_MESSAGE);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CargarProcesos;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jtb_EliminarProceso;
    private javax.swing.JButton jtb_NuevoProceso;
    private javax.swing.JTable jtb_Tabla;
    private javax.swing.JTextField jtf_EliminarProceso;
    private javax.swing.JTextField jtf_EstadoProceso;
    private javax.swing.JTextField jtf_Memoria;
    private javax.swing.JTextField jtf_NomSesion;
    private javax.swing.JTextField jtf_Nombre;
    private javax.swing.JTextField jtf_NumSesion;
    private javax.swing.JTextField jtf_PID;
    // End of variables declaration//GEN-END:variables
}
