/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logicayrepresentacion.grafos.practicaestaciones.vista;

import com.logicayrepresentacion.grafos.practicaestaciones.CantidadEstacionesException;
import com.logicayrepresentacion.grafos.practicaestaciones.Costo;
import com.logicayrepresentacion.grafos.practicaestaciones.DatosEstacion;
import com.logicayrepresentacion.grafos.practicaestaciones.Estacion;
import com.logicayrepresentacion.grafos.practicaestaciones.Grafo;
import com.logicayrepresentacion.grafos.practicaestaciones.Ruta;
import static com.logicayrepresentacion.grafos.practicaestaciones.vista.Lienzo.DIAMETRO;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 57300
 */
public class App extends javax.swing.JFrame {

    private DatosEstacion datosEstacion;
    private int lados = 0;
    private Costo [][] matrizCostos;
    private Costo [][] matrizCostosMenores;
    private String [][] rutas;
    private Lienzo lienzo1;

    /**
     * Creates new form App
     */
    public App() {
        initComponents();

        // Carga los datos de las estaciones leyendo el archivo estaciones.txt
        // de la ruta de ejecución
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("estaciones.txt"));
            String linea;
            linea = bufferedReader.readLine();
            int cantidadEstaciones = Integer.parseInt(linea);
            datosEstacion = new DatosEstacion(cantidadEstaciones);

            while ((linea = bufferedReader.readLine()) != null) {
                String[] partes = linea.split(",");
                String ciudad1 = partes[0];
                String ciudad2 = partes[1];
                int distancia = Integer.parseInt(partes[2]);
                datosEstacion.addAdyacencia(ciudad1, ciudad2, distancia);
                lados++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CantidadEstacionesException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Carga los datos de las posiciones del grafico desde el archivo
        // grafico
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("grafico.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombreEstacion = partes[0];
                String coordenada1Str = partes[1];
                String coordenada2Str = partes[2];
                double coordenada1 = Double.parseDouble(coordenada1Str);
                double coordenada2 = Double.parseDouble(coordenada2Str);
                Estacion estacion = datosEstacion.buscar(nombreEstacion);
                estacion.setForma(new Ellipse2D.Double(coordenada1, coordenada2, DIAMETRO + 50, DIAMETRO));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        lienzo1.setObjArbol(datosEstacion);
        lienzo1.repaint();
        
        //crear las matrices de costos menores y rutas
        
        matrizCostos = datosEstacion.getGrafo().convertirMatriz();
        matrizCostosMenores = datosEstacion.getGrafo().menoresCostos(matrizCostos);
        rutas = datosEstacion.getGrafo().rutasCortas(matrizCostosMenores);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Rutas = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        estacion1 = new javax.swing.JTextField();
        estacion2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        estacionInicio = new javax.swing.JTextField();
        EnviarMensaje = new javax.swing.JButton();
        ReiniciarRuta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(924, 924));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mapa de estaciones");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Rutas");

        Rutas.setText("Encontrar Ruta");
        Rutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RutasActionPerformed(evt);
            }
        });

        jLabel7.setText("Estacion Inicial:");

        jLabel8.setText("Estacion Final: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Mensaje");

        jLabel3.setText("Para enviar mensaje, digite la estacion de origen");

        estacionInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        EnviarMensaje.setText("Enviar Mensaje");
        EnviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarMensajeActionPerformed(evt);
            }
        });

        ReiniciarRuta.setText("Reiniciar rutas");
        ReiniciarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rutas)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReiniciarRuta)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(estacionInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EnviarMensaje))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(estacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(estacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addComponent(jLabel1))))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(estacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(estacion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Rutas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addComponent(estacionInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EnviarMensaje)
                .addGap(133, 133, 133)
                .addComponent(ReiniciarRuta)
                .addGap(419, 419, 419))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        fileMenu.setMnemonic('f');
        fileMenu.setText("Herramientas");

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Guardar Ubicaciones");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(618, 618, 618))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * Se utiliza para almacenar las posiciones de las estaciones y poderlas
     * volver a cargar
     *
     * @param evt
     */
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("grafico.txt"));
            for (Estacion estacion : datosEstacion.getEstaciones()) {
                writer.write(estacion.getNombre() + ","
                        + estacion.getForma().getX() + ","
                        + estacion.getForma().getY());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void ReiniciarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarRutaActionPerformed
        // TODO add your handling code here:
        estacionInicio.setText("");
        lienzo1.repaint();
    }//GEN-LAST:event_ReiniciarRutaActionPerformed

    private void EnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarMensajeActionPerformed
        // TODO add your handling code here:

        try {
            Estacion estacion = datosEstacion.buscar(estacionInicio.getText());
            Costo[][] matriz = datosEstacion.getGrafo().convertirMatriz();
            Costo[][] menores = datosEstacion.getGrafo().menoresCostos(matriz);
            int numEstacion = estacion.getId();

            /*int[][] ruta = datosEstacion.getGrafo().enviarMensaje(menores, numEstacion, lados);
            Graphics metro = lienzo1.getGraphics();
            for (int i = 0; i < ruta.length-1; i++) {
                int e1 = ruta[i][0];
                int e2 = ruta[i][1];
                lienzo1.pintarArista(metro,e1 ,e2);
                JOptionPane.showMessageDialog(null, "La estacion "+datosEstacion.getEstaciones()[e1].getNombre()+" envia mensaje a la estacion "+datosEstacion.getEstaciones()[e2].getNombre());
            }*/

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ingrese una estacion valida para enviar el mensaje");
            estacionInicio.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese una estacion valida para enviar el mensaje");
            estacionInicio.setText("");
        }

    }//GEN-LAST:event_EnviarMensajeActionPerformed

    private void RutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RutasActionPerformed
        // TODO add your handling code here:

        //seleccionar ruta de matriz rutas cortas
        Estacion e1 = datosEstacion.buscar(estacion1.getText());
        int idE1 = e1.getId();
        Estacion e2 = datosEstacion.buscar(estacion2.getText());
        int idE2 = e2.getId();

        String rutaSolicitada = rutas[idE1][idE2];
        System.out.println(rutaSolicitada);
        //int [][] ruta = datosEstacion.getGrafo().generarRuta(rutaSolicitada);
        Graphics g = lienzo1.getGraphics();
        //lienzo1.pintarRuta(g, ruta);

        // Al traer la matriz, ya estan todas la rutas posibles, se podria hacer una sola busqueda como atributo, para que no
        //lo haga cada vez que se calcule algo y luego validar si son adyacentes para pintar la ruta o solo una arista.

    }//GEN-LAST:event_RutasActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EnviarMensaje;
    private javax.swing.JButton ReiniciarRuta;
    private javax.swing.JButton Rutas;
    private javax.swing.JTextField estacion1;
    private javax.swing.JTextField estacion2;
    private javax.swing.JTextField estacionInicio;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}
