
package InterfazGrafica;

import Entidades.Reservacion;
import Logica.LogicaHabitacion;
import Logica.TablaModelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class RealizarCheckIn extends javax.swing.JDialog {
    //atributos de la clase
    private Connection conexion;
    private TablaModelo modelo;
    private TableRowSorter tablaSorteada;
    private LogicaHabitacion habitacion;

    public RealizarCheckIn(java.awt.Frame parent, boolean modal,Connection conexion) {
        // conttructor
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        modelo = new TablaModelo();
        habitacion = new LogicaHabitacion(conexion);
        modelo.addColumn("ID");modelo.addColumn("Dpi CLiente");modelo.addColumn("Id habitacion"); modelo.addColumn("fechaEntrada");
        modelo.addColumn("Fecha Salida"); modelo.addColumn("Precio Habitacion");modelo.addColumn("Check In");
        tablaReservaciones.setModel(modelo);
        
        liberarReservaciones();
        agregarDatosTabla();
    }
    // agrega los datos a las tablas
    public void agregarDatosTabla(){
        habitacion.agregarReservacionesActivas(modelo);
    }
    // libera las reservacions
    public void liberarReservaciones(){
        habitacion.liberarReservacion();
    }// crea el alojamiento
    public void crearAlojamiento(){
        String idReservacion = textoReservacion.getText();
        String fechaSalida= textoFechaSalida.getText();
        String fechaEntrada= textoFechaEntrada.getText();
        String precio = textoPrecio.getText();
        habitacion.cambiarCheckIn(idReservacion,true);
        int total=habitacion.hacerTotalAlojamiento(fechaEntrada, fechaSalida, precio);
        habitacion.crearAlojamiento(idReservacion,total);
        this.setVisible(false);
    }
    //cancela la reservacion
    public void cancelarReservacion(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaEntradaDate = formato.parse(textoFechaEntrada.getText());
            Date fechaSalidaDate = formato.parse(textoFechaSalida.getText());
            Reservacion reservacion= new Reservacion(Integer.parseInt(textoReservacion.getText()),textoDPI.getText(),
            Integer.parseInt(textoHabitacion.getText()),fechaEntradaDate,fechaSalidaDate,Integer.parseInt(textoPrecio.getText()),checkBox.isSelected());
            habitacion.cambiarCheckIn(Integer.toString(reservacion.getId()),false);
            JOptionPane.showMessageDialog(null,"Se cancelo correctamente la reservacion con id "+reservacion.getId());
            this.setVisible(false);
        } catch (ParseException ex) {
            
        }
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoReservacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        textoDPI = new javax.swing.JTextField();
        textoHabitacion = new javax.swing.JTextField();
        textoFechaEntrada = new javax.swing.JTextField();
        textoFechaSalida = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReservaciones = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        checkBox = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        textoPrecio = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REALIZAR CHECK-IN");

        jLabel2.setText("ID RESERVACION:");

        textoReservacion.setEnabled(false);

        jLabel4.setText("DPI:");

        jLabel5.setText("HABITACION:");

        jLabel6.setText("FECHA ENTRADA:");

        jLabel7.setText("FECHA SALIDA:");

        jButton1.setText("REALIZAR CHECK IN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textoDPI.setEnabled(false);

        textoHabitacion.setEnabled(false);

        textoFechaEntrada.setEnabled(false);

        textoFechaSalida.setEnabled(false);

        tablaReservaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaReservaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaReservacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaReservaciones);

        jLabel8.setText("Filtro por DPI:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel9.setText("CHECK IN");

        checkBox.setEnabled(false);

        jLabel10.setText("PRECIO");

        textoPrecio.setEnabled(false);

        jButton2.setText("CANCELAR RESERVACION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(410, 410, 410)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 478, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textoFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel4))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(textoReservacion)
                                                .addComponent(textoDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel9))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(checkBox)
                                                .addComponent(textoFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                                .addComponent(textoPrecio))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoDPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textoFechaEntrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textoFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(checkBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean sePuede=habitacion.validarDatosCheckIn(textoReservacion.getText(),textoFechaEntrada.getText());
        if(sePuede==true){
            crearAlojamiento();
        }else{
            JOptionPane.showMessageDialog(null, "No se puede hacer el check in");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
  // sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 1));
            }
        });// sortea la tabla
// sortea la tabla
        tablaSorteada = new TableRowSorter(modelo);
        tablaReservaciones.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

    private void tablaReservacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaReservacionesMouseClicked
        //muestra lo que se selecciono de la tabla en las casillas
        int seleccion = tablaReservaciones.getSelectedRow();
        textoReservacion.setText(String.valueOf(tablaReservaciones.getValueAt(seleccion, 0)));
        textoDPI.setText(String.valueOf(tablaReservaciones.getValueAt(seleccion, 1)));
        textoHabitacion.setText(String.valueOf(tablaReservaciones.getValueAt(seleccion, 2)));
        textoFechaEntrada.setText(String.valueOf(tablaReservaciones.getValueAt(seleccion, 3)));
        textoFechaSalida.setText(String.valueOf(tablaReservaciones.getValueAt(seleccion, 4)));
        try{checkBox.setSelected(Boolean.parseBoolean((String) tablaReservaciones.getValueAt(seleccion, 6)));
        }catch(Exception e){
            checkBox.setSelected(false);
        }
        textoPrecio.setText(String.valueOf(tablaReservaciones.getValueAt(seleccion, 5)));
       
    }//GEN-LAST:event_tablaReservacionesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        boolean sePuede=habitacion.validarDatos(textoReservacion.getText());
        if(sePuede==true){
            cancelarReservacion();
        }else{
            JOptionPane.showMessageDialog(null, "No se puede hacer la cancelacion faltan datos");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReservaciones;
    private javax.swing.JTextField textoDPI;
    private javax.swing.JTextField textoFechaEntrada;
    private javax.swing.JTextField textoFechaSalida;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JTextField textoHabitacion;
    private javax.swing.JTextField textoPrecio;
    private javax.swing.JTextField textoReservacion;
    // End of variables declaration//GEN-END:variables
}
