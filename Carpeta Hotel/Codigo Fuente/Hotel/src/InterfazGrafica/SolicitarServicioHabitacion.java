
package InterfazGrafica;

import Entidades.ConsumoRestaurante;
import Logica.LogicaHabitacion;
import Logica.LogicaMenu;
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

public class SolicitarServicioHabitacion extends javax.swing.JDialog {
    // atributo privados
    private  Connection conexion;
    private String idAlojamiento;
    private TablaModelo modeloMenu;
    private TablaModelo modeloServicio;
    private LogicaMenu menu;
    private TableRowSorter tablaSorteada;
    private LogicaHabitacion habitacion;
    
    // construcgor
    public SolicitarServicioHabitacion(java.awt.Frame parent, boolean modal, Connection conexion,String idAlojamiento) {
        super(parent, modal);
        initComponents();
        modeloMenu = new TablaModelo();
        modeloServicio = new TablaModelo();
        menu = new LogicaMenu(conexion);
        habitacion= new LogicaHabitacion(conexion);
        this.conexion=conexion;
        this.idAlojamiento=idAlojamiento;
        labelAlojamiento.setText(idAlojamiento);
        agregarColumnas();
        tablaMenu.setModel(modeloMenu);
        tablaPedido.setModel(modeloServicio);
        agregarDatosMenu();
    }// agrega las columnas
    
    public void agregarColumnas(){
        modeloMenu.addColumn("Nombre Comida");modeloMenu.addColumn("Tipo Comida");modeloMenu.addColumn("Precio");
        modeloServicio.addColumn("ID ALOJAMIENTO");modeloServicio.addColumn(" NOMBRE COMIDA");modeloServicio.addColumn("PRECIO COMIDA");
        modeloServicio.addColumn("CANTIDAD");modeloServicio.addColumn("FECHA CONSUMO");
    }
    // agreg el menua la tabla
    public void agregarDatosMenu(){
        menu.agregarDatosMenu(modeloMenu);
    }
    //elimina el item
    public void eliminarItem(){
        try{
        modeloServicio.removeRow(tablaPedido.getSelectedRow());
        }catch(Exception e){
            
        }
    }
    // finaliza el pedido
    public void finalizarPedido(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < modeloServicio.getRowCount(); i++) {
            try {
                Date fechaConsumo = formato.parse(modeloServicio.getValueAt(i, 4).toString());
                ConsumoRestaurante consumo = new ConsumoRestaurante(Integer.parseInt(modeloServicio.getValueAt(i, 0).toString()),modeloServicio.getValueAt(i, 1).toString(),Integer.parseInt(modeloServicio.getValueAt(i, 2).toString()),Integer.parseInt(modeloServicio.getValueAt(i, 3).toString()),fechaConsumo);
                menu.insertarConsumo(consumo);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            
        }// crea el consumo
        JOptionPane.showMessageDialog(null, "Se agrego el consumo correctamente");
        this.setVisible(false);
    }
    // agrega el pedido
    public void agregarPedido(){
        String nombreComida=null;
        String tipoComida=null;
        String precio=null; // agrega el pedido
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaMenu.getSelectedRow();// recoge la selecion
         nombreComida = modeloMenu.getValueAt(seleccion, 0).toString();// recoge el id de etapa
         tipoComida = modeloMenu.getValueAt(seleccion, 1).toString();// recoge el id de etapa
         precio = modeloMenu.getValueAt(seleccion, 2).toString();// recoge el id de etapa
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
            boolean estaBien=menu.verificarCantidad(textoCantidad.getText());
            if(estaBien==true){
                Date fechaActual = new Date(); // agrega el pedido
                ConsumoRestaurante consumo = new ConsumoRestaurante(Integer.parseInt(this.idAlojamiento), nombreComida, Integer.parseInt(precio), Integer.parseInt(textoCantidad.getText()), fechaActual);
                menu.añadirConsumo(consumo, modeloServicio);
                
            }else{ // agrega el pedido
                JOptionPane.showMessageDialog(null,"No se introdujeron enteros en cantidad");
            }
        }else{ // agrega el pedido
            JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMenu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        labelAlojamiento = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoCantidad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPedido = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("SERVICIO HABITACION");

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

        jLabel3.setText("ID ALOJAMIENTO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel4.setText("MENU");

        jLabel5.setText("CANTIDAD:");

        jButton1.setText("AGREGAR AL PEDIDO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaPedido);

        jLabel6.setText("PEDIDO");

        jButton2.setText("FINALIZAR PEDIDO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ELIMINAR ITEM");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(264, 264, 264)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(labelAlojamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(148, 148, 148)
                            .addComponent(jLabel4))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton1)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(342, 342, 342)
                            .addComponent(jLabel6))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAlojamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(214, Short.MAX_VALUE))
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
        });

        tablaSorteada = new TableRowSorter(modeloMenu);
        tablaMenu.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarPedido();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminarItem();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        finalizarPedido();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAlojamiento;
    private javax.swing.JTable tablaMenu;
    private javax.swing.JTable tablaPedido;
    private javax.swing.JTextField textoCantidad;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
}
