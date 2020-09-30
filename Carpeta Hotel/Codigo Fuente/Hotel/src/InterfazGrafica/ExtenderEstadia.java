package InterfazGrafica;

import Entidades.Reservacion;
import Logica.LogicaHabitacion;
import Logica.TablaModelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class ExtenderEstadia extends javax.swing.JDialog {
// atributos privados de las calses
    private TableRowSorter tablaSorteada;
    private TablaModelo modelo;
    private Connection conexion;
    private LogicaHabitacion habitacion;

    public ExtenderEstadia(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        modelo = new TablaModelo();
        this.conexion = conexion;
        habitacion = new LogicaHabitacion(conexion);
        modelo.addColumn("ID ALOJAMIENTO");
        modelo.addColumn("ID RESERVACION");
        modelo.addColumn("CHECK OUT");// agrega los datos a las columnas
        tablaAlojamientos.setModel(modelo);
        agregarDatos();
    }

    public void agregarDatos() {// agrega los alojamientos activos 
        habitacion.agregarDatosAlojamientosActivos(modelo);
    }

    public void extenderEstadia() {// extiende la estadia de los alojamientos
        boolean sePuede = habitacion.verificarExtencionFecha(textoReservacion.getText(),dateNueva.getDate());
        if (sePuede == true) {
            Reservacion reservacion = habitacion.obtenerReservacionEspecifica(textoReservacion.getText());
            boolean cumpleFecha=habitacion.verificarRangoFechas(reservacion, dateNueva.getDate());
            if(cumpleFecha==true){// mira si cumple y enttra
                habitacion.actualizarEstadia(reservacion, dateNueva.getDate());
                habitacion.agregarDineroExtensionEstadia(reservacion, dateNueva.getDate());
                
            }else{// si no esta disponible la habitacion
                JOptionPane.showMessageDialog(null, "No esta disponible la habitacion hasta ese dia");
            }
        }else{// si no estan correctos los datos
            JOptionPane.showMessageDialog(null, "No esta correctos los datos");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlojamientos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoAlojamiento = new javax.swing.JLabel();
        textoReservacion = new javax.swing.JLabel();
        checkBox = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        dateNueva = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("EXTENDER ESTADIA");

        tablaAlojamientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaAlojamientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlojamientosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlojamientos);

        jLabel2.setText("Filtro:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel3.setText("ID ALOJAMIENTO:");

        jLabel4.setText("ID RESERVACION:");

        jLabel5.setText("CHECK OUT:");

        checkBox.setEnabled(false);

        jButton1.setText("EXTENDER ESTADIA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(258, 258, 258)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkBox)
                                    .addComponent(textoReservacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textoAlojamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                        .addGap(125, 125, 125)))))
                        .addGap(424, 424, 424))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textoReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(checkBox))
                .addGap(18, 18, 18)
                .addComponent(dateNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jButton1)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
        // sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)" + textoFiltro.getText(), 0));
            }
        });// sortea la tabla
// sortea la tabla
        tablaSorteada = new TableRowSorter(modelo);
        tablaAlojamientos.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

    private void tablaAlojamientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlojamientosMouseClicked
        //muestra lo que se selecciono de la tabla en las casillas
        int seleccion = tablaAlojamientos.getSelectedRow();
        textoAlojamiento.setText(String.valueOf(tablaAlojamientos.getValueAt(seleccion, 0)));
        textoReservacion.setText(String.valueOf(tablaAlojamientos.getValueAt(seleccion, 1)));
        try {
            checkBox.setSelected(Boolean.parseBoolean((String) tablaAlojamientos.getValueAt(seleccion, 2)));
        } catch (Exception e) {
            checkBox.setSelected(false);
        }

    }//GEN-LAST:event_tablaAlojamientosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        extenderEstadia();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBox;
    private com.toedter.calendar.JDateChooser dateNueva;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlojamientos;
    private javax.swing.JLabel textoAlojamiento;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JLabel textoReservacion;
    // End of variables declaration//GEN-END:variables
}
