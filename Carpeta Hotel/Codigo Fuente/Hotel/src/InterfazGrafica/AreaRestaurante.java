
package InterfazGrafica;

import Logica.LogicaHabitacion;
import Logica.LogicaRestaurante;
import Logica.TablaModelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class AreaRestaurante extends javax.swing.JInternalFrame {
    // atributos priados de la clase
    private Connection conexion;
    private JFrame frame;
    private JDesktopPane escritorio;
    private String usuario;
    private TablaModelo modelo;
    private LogicaRestaurante restaurante;
    private LogicaHabitacion habitacion;
    private TableRowSorter tablaSorteada;
// tiene el ocnstructor de la clase
    public AreaRestaurante(Connection conexion,JFrame frame,String usuarioSesion, JDesktopPane escritorio) {
        initComponents();
        this.conexion=conexion;
        this.frame=frame;// frame a inicializar
        this.escritorio=escritorio;
        restaurante = new LogicaRestaurante(conexion);
        habitacion = new LogicaHabitacion(conexion);
        this.usuario=usuarioSesion;
        modelo = new TablaModelo();// agrega datos a las columnas
        modelo.addColumn("ID ALOJAMIENTO");modelo.addColumn("ID RESERVACION");modelo.addColumn("CHECK OUT");
        tablaAlojamientos.setModel(modelo);
        labelUsuario.setText(usuarioSesion);
        agregarDatos();
    }
    // agrega los datos d elos alojamientosa ctivos
    public void agregarDatos(){
        habitacion.agregarDatosAlojamientosActivos(modelo);
    }
    // solicita el servicio de algun alojamiento
    public void solicitarServicio(){
        String idAlojamiento=null;
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaAlojamientos.getSelectedRow();// recoge la selecion
         idAlojamiento = modelo.getValueAt(seleccion, 0).toString();// recoge el id de etapa
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
           SolicitarServicioHabitacion servicio = new SolicitarServicioHabitacion(frame,true, conexion, idAlojamiento);
           servicio.setVisible(true);
        }else{// si hubo falla entra
            JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlojamientos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("AREA RESTAURANTE");

        jButton1.setText("SOLICITAR SERVICIO HABITACION");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(tablaAlojamientos);

        jLabel1.setText("ALOJAMIENTOS ACTIVOS");

        jLabel2.setText("FILTRO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel3.setText("Usuario En Sesion:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1)))
                .addContainerGap(422, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(187, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       solicitarServicio();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JTable tablaAlojamientos;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
}
