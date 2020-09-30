
package InterfazGrafica;

import Logica.LogicaHotel;
import Logica.TablaModelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class VerReservaciones extends javax.swing.JDialog {
// atributos de la clase
    private Connection conexion;
    private TablaModelo modelo;
    private LogicaHotel hotel;
    private TableRowSorter tablaSorteada;
    // constructor
    public VerReservaciones(java.awt.Frame parent, boolean modal,Connection conexion) {
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        hotel = new LogicaHotel(conexion);
        modelo = new TablaModelo();
        agregarDatos();
    }// columnas
    public void agregarDatos(){
        modelo.addColumn("ID");modelo.addColumn("DPI CLIENTE");modelo.addColumn("ID HABITACION");
        modelo.addColumn("FECHA ENTRADA"); modelo.addColumn("FECHA SALIDA");
        modelo.addColumn("PRECIO");modelo.addColumn("CHECK IN");
        tablaReservaciones.setModel(modelo);
        hotel.introducirReservaciones(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReservaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("RESERVACIONES HECHAS");

        jLabel2.setText("FILTRO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

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
        jScrollPane1.setViewportView(tablaReservaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(textoFiltro)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
        // sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 0));
            }
        });// sortea la tabla
// sortea la tabla
        tablaSorteada = new TableRowSorter(modelo);
        tablaReservaciones.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReservaciones;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
}
