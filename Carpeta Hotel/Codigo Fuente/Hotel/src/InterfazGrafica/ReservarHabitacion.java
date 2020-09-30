
package InterfazGrafica;

import Entidades.Cliente;
import Logica.LogicaHabitacion;
import java.awt.Frame;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ReservarHabitacion extends javax.swing.JDialog {
    // atributos privados
    private Connection conexion;
    private SimpleDateFormat formato;
    private Frame frame;
    private LogicaHabitacion habitacion;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int precio;
    private int id;
// constructor
    public ReservarHabitacion(java.awt.Frame parent, boolean modal,Connection conexion,Date fechaEntrada, Date fechaSalida,int precio, int id) {
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        this.frame=frame;
        habitacion = new LogicaHabitacion(conexion);
        formato = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaEntrada=fechaEntrada;
        this.fechaSalida=fechaSalida;
        this.precio=precio;
        this.id=id;
        textoFechaEntrada.setText(formato.format(fechaEntrada));
        textoFechaSalida.setText(formato.format(fechaSalida));
        textoID.setText(Integer.toString(id));
        textoPrecio.setText(Integer.toString(precio));
        
    }
    // crea la reservacion
    public void reservar(){
        boolean noEstaVacio=habitacion.verificarCampoDpiCliente(textoDpi.getText());
        if(noEstaVacio==true){// crea la reservacion
            Cliente cliente = habitacion.buscarInformacionCliente(textoDpi.getText());
            if(cliente== null){// crea la reservacion
                JOptionPane.showMessageDialog(null, "No esta registrado el numero de dpi"
                        + "\npor favor propocione los datos del cliente");
                
                IngresarCliente ingreso = new IngresarCliente(frame, true, conexion, textoDpi.getText());
                ingreso.setVisible(true);
                reservar();// crea la reservacion
            }else{
                habitacion.crearReservacion(cliente,this.fechaEntrada,this.fechaSalida,Integer.toString(id), this.precio);
                this.setVisible(false);
            }
        }else{// crea la reservacion
            JOptionPane.showMessageDialog(null, "No se introdujo ningun dato en cliente");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        textoDpi = new javax.swing.JTextField();
        textoID = new javax.swing.JTextField();
        textoFechaEntrada = new javax.swing.JTextField();
        textoFechaSalida = new javax.swing.JTextField();
        textoPrecio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("RESERVACION HABITACION");

        jLabel2.setText("DPI CLIENTE:");

        jLabel3.setText("ID HABITACION:");

        jLabel4.setText("FECHA ENTRADA:");

        jLabel5.setText("FECHA SALIDA:");

        jLabel6.setText("PRECIO:");

        jButton1.setText("HACER RESERVACION:");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textoID.setEnabled(false);

        textoFechaEntrada.setEnabled(false);

        textoFechaSalida.setEnabled(false);

        textoPrecio.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jButton1))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(textoDpi)
                    .addComponent(textoID)
                    .addComponent(textoFechaEntrada)
                    .addComponent(textoFechaSalida)
                    .addComponent(textoPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoDpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(textoFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       reservar();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField textoDpi;
    private javax.swing.JTextField textoFechaEntrada;
    private javax.swing.JTextField textoFechaSalida;
    private javax.swing.JTextField textoID;
    private javax.swing.JTextField textoPrecio;
    // End of variables declaration//GEN-END:variables
}
