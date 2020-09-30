package InterfazGrafica;

import Entidades.Cliente;
import Exportacion.PDF;
import static Exportacion.PDF.usarFileChooser;
import Logica.LogicaHabitacion;
import Logica.LogicaHotel;
import Logica.TablaModelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class CheckOut extends javax.swing.JDialog {
// atributos privados de las clases
    private Connection conexion;
    private String idAlojamiento;
    private TablaModelo modelo;
    private LogicaHabitacion habitacion;
    private LogicaHotel hotel;
    private TableRowSorter tablaSorteada;
// constructor de las calses
    public CheckOut(java.awt.Frame parent, boolean modal, Connection conexion, String idAlojamiento) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        this.idAlojamiento = idAlojamiento;
        modelo = new TablaModelo();
        habitacion = new LogicaHabitacion(conexion);
        hotel = new LogicaHotel(conexion);
        agregarDatos();
        agregarInformacionTabla();
    }
// agrega los datos de las columnas 
    public void agregarDatos() {
        modelo.addColumn("ID CONSUMO");
        modelo.addColumn("ID ALOJAMIENTO");
        modelo.addColumn("NOMBRE COMIDA");
        modelo.addColumn("PRECIO COMIDA");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("FECHA CONSUMO");
        tablaConsumos.setModel(modelo);
    }// agrega la informacion de las tablas de los consumos

    public void agregarInformacionTabla() {
        habitacion.introducirConsumosALojamiento(idAlojamiento, modelo);
        agregarTotal();
    }
// finaliza el check out
    public void finalizarCheckOut() {
        Cliente cliente = hotel.obtenerCliente(idAlojamiento);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Ocurrio un problema con la finalizacion del check out por favor intentalo de nuevo");
        } else {// obtiene lo que hay que pagar por un alojamiento
            int totalAlojamiento=hotel.obtenerTotalPagoAlojamientoPorIdALojamiento(idAlojamiento);
            hotel.cambiarCheckOut(idAlojamiento,true);// cambia el chek out a true
            File archivo = usarFileChooser(".pdf"); //pide el archivo al usuario
            PDF.exportaFactura(modelo, Integer.parseInt(labelTotal.getText()),cliente.getNombre()+" "+cliente.getApellido(),cliente.getNit(),archivo,totalAlojamiento);
            JOptionPane.showMessageDialog(null, "Se pago el total de: "+labelTotal.getText());
            this.setVisible(false);// quita la ventana
        }
    }

    public void agregarTotal() {
        int total = 0;// agrega el totoal
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int precio = (Integer) modelo.getValueAt(i, 3);
            int cantidad = (Integer) modelo.getValueAt(i, 4);
            int subtotal = precio * cantidad;
            total += subtotal;

        }
        labelTotal.setText(Integer.toString(total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoAlojamiento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaConsumos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CHECK OUT");

        jLabel2.setText("ALOJAMIENTO:");

        tablaConsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaConsumos);

        jLabel3.setText("FILTRO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel4.setText("TOTAL:");

        jButton1.setText("PAGAR Y FINALIZAR EL CHECK OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(521, 521, 521))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(38, 38, 38)
                                    .addComponent(textoAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(48, 48, 48)
                                .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(33, 33, 33)
                                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton1)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addComponent(jButton1)
                .addGap(90, 90, 90))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        finalizarCheckOut();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
                             
// sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 2));
            }
        });

        tablaSorteada = new TableRowSorter(modelo);
        tablaConsumos.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTable tablaConsumos;
    private javax.swing.JLabel textoAlojamiento;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
}
