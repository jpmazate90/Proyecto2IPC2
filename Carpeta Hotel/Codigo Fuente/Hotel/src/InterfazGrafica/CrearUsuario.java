
package InterfazGrafica;

import Entidades.Usuario;
import Logica.Sesion;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CrearUsuario extends javax.swing.JDialog {
// valores de la calse
    private Sesion sesion;
    private Connection conexion;
    private JFrame frame;
// cnstructor de la clase
    public CrearUsuario(java.awt.Frame parent, boolean modal,Connection conexion,JFrame frame) {
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        this.frame=frame;
        sesion = new Sesion(conexion,frame);
    }

    // verifica si el usuario existe 
    public void verificarUsuario(){
        Usuario usuario = new Usuario(textoUsuario.getText(), textoContraseña.getText(), textoNombre.getText(), textoApellido.getText(), (String)comboBoxTipo.getSelectedItem());
        String datos[]={textoNombre.getText(),textoApellido.getText(),textoUsuario.getText(), textoContraseña.getText(),(String)comboBoxTipo.getSelectedItem()};
        boolean estanLlenos=sesion.validarEspaciosUsuario(datos);
        if(estanLlenos==true){// si existe entonces agarra los valors
            boolean sePuedeCrear=sesion.validarUsuario(usuario.getUsuario());
            if(sePuedeCrear==true){// si todo cumple entra y crea el usuario
                sesion.crearUsuario(usuario);
                this.setVisible(false);
            }else{// si hubo un problema lo muestra
                JOptionPane.showMessageDialog(null,"No se puede crear el usuario");
            }
            
        }else{// si hubo un problema lo muestra
            JOptionPane.showMessageDialog(null, "Hay casillas vacias, por favor llenalos correctamente");
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
        textoUsuario = new javax.swing.JTextField();
        textoContraseña = new javax.swing.JTextField();
        textoNombre = new javax.swing.JTextField();
        textoApellido = new javax.swing.JTextField();
        comboBoxTipo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CREACION DE UN USUARIO");

        jLabel2.setText("USUARIO:");

        jLabel3.setText("CONTRASEÑA:");

        jLabel4.setText("NOMBRE:");

        jLabel5.setText("APELLIDOS:");

        jLabel6.setText("TIPO USUARIO:");

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GERENTE", "RECEPCIONISTA", "EMPLEADO RESTAURANTE" }));

        jButton1.setText("CREAR USUARIO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(75, 75, 75))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(39, 39, 39)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jButton1))
                                .addGap(39, 39, 39)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textoContraseña)
                            .addComponent(textoUsuario)
                            .addComponent(textoNombre)
                            .addComponent(textoApellido)
                            .addComponent(comboBoxTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, 250, Short.MAX_VALUE))))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(textoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jButton1)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verificarUsuario();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField textoApellido;
    private javax.swing.JTextField textoContraseña;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
