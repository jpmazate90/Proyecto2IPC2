
package InterfazGrafica;

import Logica.HabitacionBooleana;
import Logica.LogicaHotel;
import Logica.TablaModelo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

public class VerHabitaciones extends javax.swing.JDialog {
    // atributos
    private Connection conexion;
    private boolean ver;
    private TablaModelo modelo;
    private TablaModelo modeloDisponibles;
    private LogicaHotel hotel;
// constructor
    public VerHabitaciones(java.awt.Frame parent, boolean modal, Connection conexion,boolean ver,String texto) {
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        labelTexto.setText(texto);
        modelo = new TablaModelo();
        modeloDisponibles = new TablaModelo();
        hotel = new LogicaHotel(conexion);
        this.ver=ver;
        agregarDatos();
    }
    // agrega los datos de las columnas
    public void agregarDatos(){
        modelo.addColumn("ID HABITACION");modelo.addColumn("HABITACION");modelo.addColumn("NIVEL");modelo.addColumn("OCUPADA"); modelo.addColumn("ID RESERVACION");
        modelo.addColumn("DPI CLIENTE");modelo.addColumn("NOMBRE"); modelo.addColumn("APELLIDO");
        modeloDisponibles.addColumn("ID Habitacion");modeloDisponibles.addColumn("HABITACION");modeloDisponibles.addColumn("NIVEL");
        modeloDisponibles.addColumn("OCUPADA");
        Date fecha = new Date();
        ArrayList<HabitacionBooleana> habitaciones = hotel.verHabitaciones(ver,fecha);
        verDatosAIntroducir(habitaciones);
        
    }
    // mira los datos a introducir
    public void verDatosAIntroducir(ArrayList<HabitacionBooleana> habitaciones){
        if(ver==true){
            hotel.introducirDatosHabitacionesOcupadas(habitaciones, modelo);
            tablaHabitaciones.setModel(modelo);
        }else{
            hotel.introducirDatosHabitacionesDisponibles(habitaciones, modeloDisponibles);
            tablaHabitaciones.setModel(modeloDisponibles);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHabitaciones = new javax.swing.JTable();
        labelTexto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        labelTexto.setText("HABITACIONES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(labelTexto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(labelTexto)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTexto;
    private javax.swing.JTable tablaHabitaciones;
    // End of variables declaration//GEN-END:variables
}
