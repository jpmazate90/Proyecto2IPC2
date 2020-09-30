
package InterfazGrafica;

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

public class HabitacionesGUI extends javax.swing.JDialog {
    // atributos privados de las clase
    
    private TablaModelo modelo;
    private Connection conexion;
    private LogicaHotel hotel;
    private LogicaHabitacion habitacion;
    private TableRowSorter tablaSorteada;

    public HabitacionesGUI(java.awt.Frame parent, boolean modal,Connection conexion) {
        super(parent, modal);
        initComponents();// contrusctor
        this.conexion=conexion;
        modelo = new TablaModelo();
        hotel = new LogicaHotel(conexion);
        habitacion = new LogicaHabitacion(conexion);
        informacionModelo();
        agregarHabitaciones();
        ocultarBotones();
    }
    // agrgea los columnas al modelo
    public void informacionModelo(){
        modelo.addColumn("ID");modelo.addColumn("TIPO HABITACION");
        modelo.addColumn("NIVEL"); modelo.addColumn("PRECIO");
        tablaHabitaciones.setModel(modelo);
    }
    // agrega las habitacones a la tabla
    public void agregarHabitaciones(){
        hotel.agregarHabitaciones(modelo);
    }
    // borra las habitaciones
    
    public void borrarHabitaciones(){
        habitacion.borrarDatos(modelo);
    }
    // oculta los botones
    public void ocultarBotones(){
        textoID.setVisible(false);
        textoNivel.setVisible(false);
        textoPrecio.setVisible(false);
        textoTipo.setVisible(false);
        botonEditar.setVisible(false);
    }
    // borra los datos de los textos
    public void borrarDatos(){
        textoID.setText("");
        textoNivel.setText("");
        textoPrecio.setText("");
        textoTipo.setText("");
    }
    // muestra los botones
    public void mostrarBotones(){
        textoID.setVisible(true);
        textoNivel.setVisible(true);
        textoPrecio.setVisible(true);
        textoTipo.setVisible(true);
        botonEditar.setVisible(true);
    }
    // agrega los datos de los labesl
    public void agregarDatosLabels(){
        String id=null;
        String tipoHabitacion = null;
        String nivel=null;
        String precio=null;
        boolean huboFalla=false;
        try {// recoge valores a las tablas 
         int seleccion = tablaHabitaciones.getSelectedRow();// recoge la selecion
         id = (String)modelo.getValueAt(seleccion, 0).toString();// recoge el id de etapa
         tipoHabitacion = modelo.getValueAt(seleccion, 1).toString();// recoge el id de etapa
         nivel = modelo.getValueAt(seleccion, 2).toString();// recoge el id de etapa
         precio = modelo.getValueAt(seleccion, 3).toString();// recoge el id de etapa
        } catch (Exception e) {
            
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
        if(huboFalla==false){
              textoID.setText(id);
              textoTipo.setText(tipoHabitacion);
              textoNivel.setText(nivel);
              textoPrecio.setText(precio);
              mostrarBotones();
        }else{
                JOptionPane.showMessageDialog(null,"No se selecciono ningun item");
        }
    }
    // muestra la informacion del precio
    public void cambiarInformacionPrecio(){
        boolean sePuede=hotel.verificarPrecio(textoPrecio.getText());
        if(sePuede==true){// y si cumple entra
            hotel.actualizarPrecio(textoPrecio.getText(),textoID.getText());
            borrarHabitaciones();// actualiza
            agregarHabitaciones();
        }else{
            JOptionPane.showMessageDialog(null,"No se introdujo un entero correcto en el precio");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHabitaciones = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoID = new javax.swing.JTextField();
        textoTipo = new javax.swing.JTextField();
        textoNivel = new javax.swing.JTextField();
        textoPrecio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("HABITACIONES");

        jLabel2.setText("ID:");

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

        jLabel3.setText("FILTRO:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel4.setText("TIPO HABITACION:");

        jLabel5.setText("NIVEL:");

        jLabel6.setText("PRECIO:");

        textoID.setEnabled(false);

        textoTipo.setEnabled(false);

        textoNivel.setEnabled(false);

        jButton1.setText("EDITAR PRECIO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(253, 253, 253)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(51, 51, 51)
                                .addComponent(textoFiltro))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(botonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textoID)
                                        .addComponent(textoTipo)
                                        .addComponent(textoNivel)
                                        .addComponent(textoPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(textoNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(textoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEditar)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      agregarDatosLabels();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
       cambiarInformacionPrecio();
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
        tablaHabitaciones.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHabitaciones;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JTextField textoID;
    private javax.swing.JTextField textoNivel;
    private javax.swing.JTextField textoPrecio;
    private javax.swing.JTextField textoTipo;
    // End of variables declaration//GEN-END:variables
}
