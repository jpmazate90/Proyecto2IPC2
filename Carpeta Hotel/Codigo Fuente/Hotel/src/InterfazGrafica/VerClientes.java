
package InterfazGrafica;

import Entidades.Cliente;
import Entidades.Menu;
import Enumerados.EnumeradoMenu;
import Logica.LogicaHabitacion;
import Logica.LogicaHotel;
import Logica.TablaModelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class VerClientes extends javax.swing.JDialog {
//atributos
    private Connection conexion;
    private TablaModelo modelo;
    private LogicaHotel hotel;
    private LogicaHabitacion habitacion;
    private String dpi;
    private TableRowSorter tablaSorteada;
    //constructor
    public VerClientes(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        hotel = new LogicaHotel(conexion);
        habitacion = new LogicaHabitacion(conexion);
        modelo = new TablaModelo();
        agregarDatos();
        agregarInformacion();
        ocultarBotones();
    }
    // agrega los datos de la columna
    public void agregarDatos(){
        modelo.addColumn("DPI"); modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");modelo.addColumn("NIT");
        tablaClientes.setModel(modelo);
    }
    // agrega la informacion
    public void agregarInformacion(){
        hotel.introducirClientes(modelo);
    }
    // borra la informacion
    public void borrarInformacion(){
        habitacion.borrarDatos(modelo);
    }
    // eidtta el item
    public void editarItem(){
        borrarDatos();
        String dpi=null;
        String nombre= null;
        String apellido=null;
        String nit=null;
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaClientes.getSelectedRow();// recoge la selecion
         dpi = (String)modelo.getValueAt(seleccion, 0);// recoge el id de etapa
         nombre = (String)modelo.getValueAt(seleccion, 1);// recoge el id de etapa
         apellido = modelo.getValueAt(seleccion, 2).toString();// recoge el id de etapa
         nit = modelo.getValueAt(seleccion, 3).toString();
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
              textoDPI.setText(dpi);
              textoNombre.setText(nombre);
              textoApellido.setText(apellido);
              textoNIT.setText(nit);
              this.dpi=dpi;
              habilitarBotonesEditar();
        }else{
                JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
    }
    // cambia la informacion
    public void cambiarInformacion(){
        boolean sePuede=hotel.validarInformacionCliente(textoDPI.getText(),textoNombre.getText(),textoApellido.getText(),textoNIT.getText());
        if(sePuede==true){// cambia la informacion
            Cliente item = new Cliente(textoDPI.getText(),textoNombre.getText(),textoApellido.getText(),textoNIT.getText());
            hotel.editarItemCliente(item, dpi);
            habitacion.borrarDatos(modelo);// cambia la informacion
            agregarInformacion();
            ocultarBotones();// cambia la informacion
        }else{
            JOptionPane.showMessageDialog(null,"Los datos no estan correctos");
        }
    }
    public void eliminarItem(){
        String dpi=null;// cambia la informacion
        String nombre= null;
        String apellido=null;
        String nit=null;
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaClientes.getSelectedRow();// recoge la selecion
         dpi = (String)modelo.getValueAt(seleccion, 0);// recoge el id de etapa
         nombre = (String)modelo.getValueAt(seleccion, 1);// recoge el id de etapa
         apellido = modelo.getValueAt(seleccion, 2).toString();// recoge el id de etapa
         nit = modelo.getValueAt(seleccion, 3).toString();
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
            hotel.eliminarItemCliente(dpi);
            habitacion.borrarDatos(modelo);// cambia la informacion
            agregarInformacion();
        }else{
            JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
        
    }
    // cambia la informacion
    public void crearItem(){
        boolean sePuede=hotel.validarInformacionCliente(textoNuevoApellido.getText(),textoNuevoDPI.getText(),textoNuevoNIT.getText(),textoNuevoNombre.getText());
        if(sePuede==true){
            Cliente item = new Cliente(textoNuevoDPI.getText(),textoNuevoNombre.getText(),textoNuevoApellido.getText(),textoNuevoNIT.getText());
            hotel.crearItemCliente(item);
            habitacion.borrarDatos(modelo);// cambia la informacion
            agregarInformacion();
        }else{// cambia la informacion
            JOptionPane.showMessageDialog(null,"Los datos no estan correctos");
        }
    }
    
    public void habilitarBotonesCreacion(){
        textoNuevoApellido.setEnabled(true);
        textoNuevoDPI.setEnabled(true);// habitilta los botones
        textoNuevoNIT.setEnabled(true);
        textoNuevoNombre.setEnabled(true);// habitilta los botones
        textoNuevoApellido.setVisible(true);
        textoNuevoDPI.setVisible(true);
        textoNuevoNIT.setVisible(true);// habitilta los botones
        textoNuevoNombre.setVisible(true);
        botonAñadir.setVisible(true);// habitilta los botones
        textoApellido.setVisible(false);
        textoDPI.setVisible(false);
        textoNIT.setVisible(false);// habitilta los botones
        textoNombre.setVisible(false);
        botonEditar.setVisible(false);
    }
    public void habilitarBotonesEditar(){
        textoNuevoApellido.setEnabled(false);
        textoNuevoDPI.setEnabled(false);// habitilta los botones
        textoNuevoNIT.setEnabled(false);
        textoNuevoNombre.setEnabled(false);
        textoNuevoApellido.setVisible(false);
        textoNuevoDPI.setVisible(false);
        textoNuevoNIT.setVisible(false);
        textoNuevoNombre.setVisible(false);// habitilta los botones
        botonAñadir.setVisible(false);
        textoApellido.setVisible(true);// habitilta los botones
        textoDPI.setVisible(true);
        textoNIT.setVisible(true);
        textoNombre.setVisible(true);
        botonEditar.setVisible(true);
    }
    public void borrarDatos(){
        textoNuevoApellido.setText("");
        textoNuevoDPI.setText("");
        textoNuevoNIT.setText("");
        textoNuevoNombre.setText("");
        textoApellido.setText("");// habitilta los botones
        textoDPI.setText("");
        textoNIT.setText("");
        textoNombre.setText("");
    }
    
    public void ocultarBotones(){
        textoApellido.setVisible(false);
        textoDPI.setVisible(false);// habitilta los botones
        textoNIT.setVisible(false);
        textoNombre.setVisible(false);
        botonEditar.setVisible(false);
        botonAñadir.setVisible(false);
        textoNuevoApellido.setVisible(false);// habitilta los botones
        textoNuevoDPI.setVisible(false);
        textoNuevoNIT.setVisible(false);
        textoNuevoNombre.setVisible(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoDPI = new javax.swing.JTextField();
        textoNombre = new javax.swing.JTextField();
        textoApellido = new javax.swing.JTextField();
        textoNIT = new javax.swing.JTextField();
        textoNuevoDPI = new javax.swing.JTextField();
        textoNuevoNombre = new javax.swing.JTextField();
        textoNuevoApellido = new javax.swing.JTextField();
        textoNuevoNIT = new javax.swing.JTextField();
        botonAñadir = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CLIENTES");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaClientes);

        jLabel2.setText("FILTRO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jButton1.setText("CREAR CLIENTE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("EDITAR CLIENTE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ELIMINAR CLIENTE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("DPI:");

        jLabel4.setText("NOMBRE:");

        jLabel5.setText("APELLIDO:");

        jLabel6.setText("NIT:");

        botonAñadir.setText("AÑADIR CLIENTE");
        botonAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirActionPerformed(evt);
            }
        });

        botonEditar.setText("EDITAR CLIENTE");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(textoFiltro)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoDPI)
                                    .addComponent(textoNombre)
                                    .addComponent(textoApellido)
                                    .addComponent(textoNIT, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                            .addComponent(botonEditar))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAñadir)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textoNuevoDPI)
                                .addComponent(textoNuevoApellido)
                                .addComponent(textoNuevoNombre)
                                .addComponent(textoNuevoNIT, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoDPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNuevoDPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNuevoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textoNIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNuevoNIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAñadir)
                    .addComponent(botonEditar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminarItem();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editarItem();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        habilitarBotonesCreacion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAñadirActionPerformed
        crearItem();
    }//GEN-LAST:event_botonAñadirActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        cambiarInformacion();
    }//GEN-LAST:event_botonEditarActionPerformed

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
                       
// sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 0));
            }
        });

        tablaSorteada = new TableRowSorter(modelo);
        tablaClientes.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAñadir;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField textoApellido;
    private javax.swing.JTextField textoDPI;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JTextField textoNIT;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoNuevoApellido;
    private javax.swing.JTextField textoNuevoDPI;
    private javax.swing.JTextField textoNuevoNIT;
    private javax.swing.JTextField textoNuevoNombre;
    // End of variables declaration//GEN-END:variables
}
