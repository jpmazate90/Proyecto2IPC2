
package InterfazGrafica;

import Entidades.Menu;
import Entidades.Usuario;
import Enumerados.EnumeradoMenu;
import Enumerados.EnumeradosUsuarios;
import Logica.LogicaHabitacion;
import Logica.LogicaHotel;
import Logica.Sesion;
import Logica.TablaModelo;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class UsuarioGUI extends javax.swing.JDialog {
    // atributos privados
    private Connection conexion;
    private JFrame frame;
    private TablaModelo modelo;
    private LogicaHotel hotel;
    private LogicaHabitacion habitacion;
    private Sesion sesion;
    private String usuario;
    private TableRowSorter tablaSorteada;
// costructor dela clase
    public UsuarioGUI(java.awt.Frame parent, boolean modal,Connection conexion, JFrame frame) {
        super(parent, modal);
        initComponents();
        this.frame=frame;
        this.conexion=conexion;
        modelo = new TablaModelo();
        hotel = new LogicaHotel(conexion);
        sesion = new Sesion(conexion, frame);
        habitacion = new LogicaHabitacion(conexion);
        ocultarBotones();
        agregarColumnas();
        agregarUsuarios();
    }

// agrega los datos a las columnas
    public void agregarColumnas(){
        modelo.addColumn("USUARIO");modelo.addColumn("CONTRASEÑA");modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");modelo.addColumn("TIPO USUARIO");
        tablaUsuarios.setModel(modelo);
    }
    // agrega los usuaros
    public void agregarUsuarios(){
        hotel.agregarUsuarios(modelo);
    }
    // borra los usuarios
    public void borrarUsuarios(){
        habitacion.borrarDatos(modelo);
    }
    // oculta los botoens
    public void ocultarBotones(){

        labelApellido.setVisible(false);
        labelContraseña.setVisible(false);
        labelNombre.setVisible(false);
        labelTipo.setVisible(false);// oculta los botoens
        labelUsuario.setVisible(false);
        comboBoxTipo.setVisible(false);
        botonEditar.setVisible(false);// oculta los botoens
        textoApellido.setVisible(false);
        textoContraseña.setVisible(false);// oculta los botoens
        textoNombre.setVisible(false);
        textoUsuario.setVisible(false);// oculta los botoens
        borrarDatos();
    }
    
    public void mostrarBotones(){//muestra los botones
        labelApellido.setVisible(true);//muestra los botones
        labelContraseña.setVisible(true);
        labelNombre.setVisible(true);
        labelTipo.setVisible(true);//muestra los botones
        labelUsuario.setVisible(true);
        comboBoxTipo.setVisible(true);
        botonEditar.setVisible(true);
        textoApellido.setVisible(true);//muestra los botones
        textoContraseña.setVisible(true);
        textoNombre.setVisible(true);//muestra los botones
        textoUsuario.setVisible(true);
    }
    
    public void borrarDatos(){// borra los datos
        textoApellido.setText("");
        textoContraseña.setText("");
        textoNombre.setText("");
        textoUsuario.setText("");
    }
    
    public void editarUsuario(){
        borrarDatos();
        String usuario=null;
        String contraseña = null;//edita a l ususario
        String nombre=null;
        String apellido=null;
        String tipoUsuario = null;
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaUsuarios.getSelectedRow();// recoge la selecion
         usuario = (String)modelo.getValueAt(seleccion, 0);// recoge el id de etapa
         contraseña = (String)modelo.getValueAt(seleccion, 1);// recoge el id de etapa
         nombre = modelo.getValueAt(seleccion, 2).toString();// recoge el id de etapa
         apellido = modelo.getValueAt(seleccion, 3).toString();// recoge el id de etapa
         tipoUsuario = modelo.getValueAt(seleccion, 4).toString();// recoge el id de etapa
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
              textoApellido.setText(apellido);
              textoContraseña.setText(contraseña);
              textoNombre.setText(nombre);//muestra los botones
              textoUsuario.setText(usuario);
              String en= EnumeradoMenu.COMIDA.toString();
              if(tipoUsuario.equals(EnumeradosUsuarios.GERENTE.toString())){
                  comboBoxTipo.setSelectedIndex(0);
              }else if(tipoUsuario.equals(EnumeradosUsuarios.RECEPCIONISTA.toString())){
                  comboBoxTipo.setSelectedIndex(1);
              }else{//muestra los botones
                  comboBoxTipo.setSelectedIndex(2);
              }
              this.usuario=usuario;
              mostrarBotones();
        }else{
                JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
    }//muestra los botones
    
     public void cambiarInformacion(){
        Usuario usuario = new Usuario(textoUsuario.getText(), textoContraseña.getText(), textoNombre.getText(), textoApellido.getText(), (String)comboBoxTipo.getSelectedItem());
        String datos[]={textoNombre.getText(),textoApellido.getText(),textoUsuario.getText(), textoContraseña.getText(),(String)comboBoxTipo.getSelectedItem()};
        boolean estanLlenos=sesion.validarEspaciosUsuario(datos);
        if(estanLlenos==true){// si existe entonces agarra los valors
            hotel.actualizarUsuario(this.usuario, usuario);//muestra los botones
            borrarUsuarios();
            agregarUsuarios();
            //muestra los botones
        }else{// si hubo un problema lo muestra
            JOptionPane.showMessageDialog(null, "Hay casillas vacias, por favor llenalos correctamente");
        }
    }
     
     public void eliminarItem(){//muestra los botones
        String usuario=null;
        String contraseña = null;
        String nombre=null;
        String apellido=null;
        String tipoUsuario = null;//muestra los botones
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaUsuarios.getSelectedRow();// recoge la selecion
         usuario = (String)modelo.getValueAt(seleccion, 0);// recoge el id de etapa
         contraseña = (String)modelo.getValueAt(seleccion, 1);// recoge el id de etapa
         nombre = modelo.getValueAt(seleccion, 2).toString();// recoge el id de etapa
         apellido = modelo.getValueAt(seleccion, 3).toString();// recoge el id de etapa
         tipoUsuario = modelo.getValueAt(seleccion, 4).toString();// recoge el id de etapa
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
            hotel.eliminarUsuario(usuario);
            habitacion.borrarDatos(modelo);
            agregarUsuarios();
        }else{
            JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();
        labelContraseña = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelApellido = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();
        textoContraseña = new javax.swing.JTextField();
        textoNombre = new javax.swing.JTextField();
        textoApellido = new javax.swing.JTextField();
        comboBoxTipo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("USUARIOS");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaUsuarios);

        jLabel2.setText("FILTRO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        labelUsuario.setText("USUARIO:");

        labelContraseña.setText("CONTRASEÑA:");

        labelNombre.setText("NOMBRE:");

        labelApellido.setText("APELLIDO:");

        labelTipo.setText("TIPO USUARIO:");

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GERENTE", "RECEPCIONISTA", "EMPLEADO RESTAURANTE" }));

        jButton1.setText("CREAR USUARIO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("EDITAR USUARIO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ELIMINAR USUARIO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        botonEditar.setText("EDITAR");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelUsuario)
                                    .addComponent(labelContraseña)
                                    .addComponent(labelNombre)
                                    .addComponent(labelApellido)
                                    .addComponent(labelTipo))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(botonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboBoxTipo, 0, 192, Short.MAX_VALUE)
                                    .addComponent(textoApellido)
                                    .addComponent(textoUsuario)
                                    .addComponent(textoContraseña)
                                    .addComponent(textoNombre))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoFiltro))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContraseña)
                    .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApellido)
                    .addComponent(textoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipo)
                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonEditar)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CrearUsuario usuario = new CrearUsuario(this.frame, true, conexion, frame);
        usuario.setVisible(true);
        borrarUsuarios();
        agregarUsuarios();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    editarUsuario();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        cambiarInformacion();
        ocultarBotones();
    }//GEN-LAST:event_botonEditarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminarItem();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
        // sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 0));
            }
        });

        tablaSorteada = new TableRowSorter(modelo);
        tablaUsuarios.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEditar;
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField textoApellido;
    private javax.swing.JTextField textoContraseña;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
