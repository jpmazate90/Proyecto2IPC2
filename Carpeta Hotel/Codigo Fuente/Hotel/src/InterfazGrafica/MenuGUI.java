
package InterfazGrafica;

import Entidades.Menu;
import Enumerados.EnumeradoMenu;
import Enumerados.EnumeradosUsuarios;
import Logica.LogicaHabitacion;
import Logica.LogicaMenu;
import Logica.TablaModelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class MenuGUI extends javax.swing.JDialog {
//atributos de la clase
    private Connection conexion;
    private TablaModelo modelo;
    private LogicaMenu menu;
    private LogicaHabitacion habitacion;
    private String nombreComida;
    private TableRowSorter tablaSorteada;
    /// constructor de la clase
    public MenuGUI(java.awt.Frame parent, boolean modal,Connection conexion) {
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        modelo= new TablaModelo();
        menu= new LogicaMenu(conexion);
        habitacion = new LogicaHabitacion(conexion);
        modelo.addColumn("Nombre Comida");modelo.addColumn("Tipo Comida"); modelo.addColumn("Precio");
        tablaMenu.setModel(modelo);
        agregarDatos();
        ocultarBotones();
    }
    // agrega los datos
    public void agregarDatos(){
        menu.agregarDatosMenu(modelo);
    }
    
    // crea los items 
    public void crearItem(){
        boolean sePuede=menu.validarInformacionMenu(textoCrearComida.getText(), textoCrearTipo.getSelectedItem().toString(), textoCrearPrecio.getText());
        if(sePuede==true){
            Menu item = new Menu(textoCrearComida.getText(), textoCrearTipo.getSelectedItem().toString(), Integer.parseInt(textoCrearPrecio.getText()));
            menu.crearItem(item);// crea el objeto entidad
            habitacion.borrarDatos(modelo);
            agregarDatos();
        }else{
            JOptionPane.showMessageDialog(null,"Los datos no estan correctos");
        }
    }
    // elimina un item
    public void eliminarItem(){
        String nombreComida=null;
        String tipoComida = null;
        String precio=null;
        boolean huboFalla=false;// agrra los datos
        try {// recoge valores a las tablas 
         int seleccion = tablaMenu.getSelectedRow();// recoge la selecion
         nombreComida = (String)modelo.getValueAt(seleccion, 0);// recoge el id de etapa
         tipoComida = (String)modelo.getValueAt(seleccion, 1);// recoge el id de etapa
         precio = modelo.getValueAt(seleccion, 2).toString();// recoge el id de etapa
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
            menu.eliminarItem(nombreComida);
            habitacion.borrarDatos(modelo);
            agregarDatos();
        }else{
            JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
        
    }
    public void editarItem(){
        borrarDatos();
        String nombreComida=null;
        String tipoComida = null;
        String precio=null;
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaMenu.getSelectedRow();// recoge la selecion
         nombreComida = (String)modelo.getValueAt(seleccion, 0);// recoge el id de etapa
         tipoComida = (String)modelo.getValueAt(seleccion, 1);// recoge el id de etapa
         precio = modelo.getValueAt(seleccion, 2).toString();// recoge el id de etapa
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
              textoComida.setText(nombreComida);
              textoPrecio.setText(precio);
              String en= EnumeradoMenu.COMIDA.toString();
              if(tipoComida.equals(EnumeradoMenu.COMIDA.toString())){
                  comboBoxTipo.setSelectedIndex(0);
              }else{
                  comboBoxTipo.setSelectedIndex(1);
              }
              this.nombreComida=nombreComida;
              habilitarBotonesEditar();
        }else{
                JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
    }
    
    public void cambiarInformacion(){
        boolean sePuede=menu.validarInformacionMenu(textoComida.getText(), comboBoxTipo.getSelectedItem().toString(), textoPrecio.getText());
        if(sePuede==true){// cambia la informacion del menu
            Menu item = new Menu(textoComida.getText(), comboBoxTipo.getSelectedItem().toString(), Integer.parseInt(textoPrecio.getText()));
            menu.editarItem(item, this.nombreComida);
            habitacion.borrarDatos(modelo);// actualiza los datos
            agregarDatos();
            ocultarBotones();
        }else{
            JOptionPane.showMessageDialog(null,"Los datos no estan correctos");
        }
    }
    // habitilta los botones pertinentes
    public void habilitarBotonesCreacion(){
        textoCrearComida.setEnabled(true);
        textoCrearTipo.setEnabled(true);
        textoCrearPrecio.setEnabled(true);
        textoCrearComida.setVisible(true);
        textoCrearTipo.setVisible(true);
        textoCrearPrecio.setVisible(true);
        botonAñadir.setVisible(true);
        textoComida.setVisible(false);
        textoPrecio.setVisible(false);
        comboBoxTipo.setVisible(false);
        botonEditar.setVisible(false);
    }
    // habitilta los botones pertinentes
    public void borrarDatos(){
        textoCrearComida.setText("");
        textoCrearPrecio.setText("");
        textoComida.setText("");
        textoPrecio.setText("");
        comboBoxTipo.setVisible(false);
        botonEditar.setVisible(false);
    }// habitilta los botones pertinentes
    public void habilitarBotonesEditar(){
        textoCrearComida.setEnabled(false);
        textoCrearTipo.setEnabled(false);
        textoCrearPrecio.setEnabled(false);
        textoCrearComida.setVisible(false);
        textoCrearTipo.setVisible(false);// habitilta los botones pertinentes
        textoCrearPrecio.setVisible(false);
        botonAñadir.setVisible(false);// habitilta los botones pertinentes
        textoComida.setVisible(true);
        textoPrecio.setVisible(true);// habitilta los botones pertinentes
        comboBoxTipo.setVisible(true);
        botonEditar.setVisible(true);
    }
    // habitilta los botones pertinentes
    public void ocultarBotones(){
// habitilta los botones pertinentes
        textoCrearComida.setVisible(false);
        textoCrearTipo.setVisible(false);
        textoCrearPrecio.setVisible(false);
        textoComida.setVisible(false);// habitilta los botones pertinentes
        textoPrecio.setVisible(false);
        comboBoxTipo.setVisible(false);
        botonEditar.setVisible(false);// habitilta los botones pertinentes
        botonAñadir.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMenu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoComida = new javax.swing.JTextField();
        comboBoxTipo = new javax.swing.JComboBox<>();
        textoPrecio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        textoCrearComida = new javax.swing.JTextField();
        textoCrearPrecio = new javax.swing.JTextField();
        textoCrearTipo = new javax.swing.JComboBox<>();
        botonAñadir = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("MENU ");

        tablaMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaMenu);

        jLabel2.setText("FILTRO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel3.setText("NOMBRE COMIDA:");

        jLabel4.setText("TIPO COMIDA:");

        jLabel5.setText("PRECIO:");

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMIDA", "BEBIDA" }));

        jButton1.setText("EDITAR ITEM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ELIMINAR ITEM");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("CREAR ITEM");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        textoCrearTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMIDA", "BEBIDA" }));

        botonAñadir.setText("AÑADIR ITEM");
        botonAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirActionPerformed(evt);
            }
        });

        botonEditar.setText("EDITAR ITEM");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(330, 330, 330))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoComida)
                                    .addComponent(comboBoxTipo, 0, 208, Short.MAX_VALUE)
                                    .addComponent(textoPrecio)))
                            .addComponent(botonEditar))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAñadir)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textoCrearComida)
                                .addComponent(textoCrearPrecio)
                                .addComponent(textoCrearTipo, 0, 212, Short.MAX_VALUE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoComida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCrearComida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCrearTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCrearPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAñadir)
                    .addComponent(botonEditar))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        habilitarBotonesCreacion();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        editarItem();
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
        tablaMenu.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       eliminarItem();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAñadir;
    private javax.swing.JButton botonEditar;
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMenu;
    private javax.swing.JTextField textoComida;
    private javax.swing.JTextField textoCrearComida;
    private javax.swing.JTextField textoCrearPrecio;
    private javax.swing.JComboBox<String> textoCrearTipo;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JTextField textoPrecio;
    // End of variables declaration//GEN-END:variables
}
