package InterfazGrafica;

import Logica.LogicaHabitacion;
import Logica.TablaModelo;
import java.awt.Frame;
import java.sql.Connection;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerificarDisponibilidad extends javax.swing.JDialog {
// atributos de la clase
    private TablaModelo modelo;
    private LogicaHabitacion habitacion;
    private Connection conexion;
    private Date fechaInicialPedida;
    private Date fechaFinalPedida;
    private int idPedido;
    private int precioPedido;
    private Frame frame;
// verificar disponibilidad
    public VerificarDisponibilidad(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        this.frame=parent;
        this.conexion = conexion;
        habitacion = new LogicaHabitacion(this.conexion);
        agregarDatos();
    }
// agrega los datos
    public void agregarDatos() {
        modelo = new TablaModelo();
        modelo.addColumn("ID");
        modelo.addColumn("Tipo Habitacion");
        modelo.addColumn("Nivel");
        modelo.addColumn("Precio");
        tablaHabitaciones.setModel(modelo);
        llenarTabla();
        botonReservar.setVisible(false);
    }
// llena la tabla
    public void llenarTabla() {
        habitacion.agregarDatosHabitacion(modelo);
    }
// comprueba la disponibilidad
    public void comprobarDisponibilidad() {
        Date fechaInicial = this.fechaInicial.getDate();
        Date fechaFinal = this.fechaFinal.getDate();
        boolean fechasCorrectas = habitacion.verificarFechas(fechaInicial, fechaFinal);
        String id = "";
        String tipoHabitacion;// comprueba la disponibilidad
        String nivel;
        String precio = null;// comprueba la disponibilidad
        boolean sePuede = true;
        if (fechasCorrectas == true) {
            try {
                int seleccion = tablaHabitaciones.getSelectedRow();
                id = modelo.getValueAt(seleccion, 0).toString();// comprueba la disponibilidad
                tipoHabitacion = modelo.getValueAt(seleccion, 1).toString();
                nivel = (String) modelo.getValueAt(seleccion, 2).toString();
                precio = (String) modelo.getValueAt(seleccion, 3).toString();
            } catch (Exception e) {
                e.printStackTrace();// comprueba la disponibilidad
                sePuede = false;// comprueba la disponibilidad
            }
            if (sePuede == true) {// comprueba la disponibilidad
                boolean estaLibre = habitacion.verificarDisponibilidad(fechaInicial, fechaFinal, id);
                if (estaLibre == true) {
                    botonReservar.setVisible(true);// comprueba la disponibilidad
                    JOptionPane.showMessageDialog(null, "Si esta libre la habitacion en esos dias");
                    this.fechaInicialPedida= (Date)fechaInicial.clone();
                    this.fechaFinalPedida = (Date)fechaFinal.clone();
                    this.idPedido=Integer.parseInt(id);
                    this.precioPedido=Integer.parseInt(precio);
                    System.out.println(fechaInicialPedida);// comprueba la disponibilidad
                    System.out.println(fechaFinalPedida);
                    

                } else {// comprueba la disponibilidad
                    JOptionPane.showMessageDialog(null, "No esta libre la habitacion en esas fechas");
                    botonReservar.setVisible(false);
                }
            } else {// comprueba la disponibilidad
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna habitacion");
            }
        } else {// comprueba la disponibilidad
            JOptionPane.showMessageDialog(null, "No se puede crear: la fechas iniciales y finales estan mal colocadas");
        }
    }
    // reserva la habitacion
    public void reservarHabitacion(){
        ReservarHabitacion reservacion = new ReservarHabitacion(frame,false, conexion, fechaInicialPedida, fechaFinalPedida, this.precioPedido,this.idPedido);
        reservacion.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fechaInicial = new com.toedter.calendar.JDateChooser();
        fechaFinal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHabitaciones = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        botonReservar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("VERIFICAR DISPONIBILIDAD HABITACION");

        tablaHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaHabitaciones);

        jLabel2.setText("Fecha Inicial:");

        jLabel3.setText("Fecha Final:");

        jButton1.setText("COMPROBAR DISPONIBILIDAD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botonReservar.setText("RESERVAR HABITACION");
        botonReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReservarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(79, 79, 79)
                                .addComponent(fechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(fechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonReservar)
                            .addComponent(jButton1))))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fechaFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonReservar)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        comprobarDisponibilidad();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReservarActionPerformed
        reservarHabitacion();
        this.setVisible(false);
    }//GEN-LAST:event_botonReservarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonReservar;
    private com.toedter.calendar.JDateChooser fechaFinal;
    private com.toedter.calendar.JDateChooser fechaInicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHabitaciones;
    // End of variables declaration//GEN-END:variables
}
