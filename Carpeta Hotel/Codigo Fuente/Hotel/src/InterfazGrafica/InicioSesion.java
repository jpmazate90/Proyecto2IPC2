
package InterfazGrafica;

import Logica.Sesion;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InicioSesion extends javax.swing.JInternalFrame {
// atributos 
    private Connection conexion;
    private Sesion sesion;
    private MenuPrincipal menu;
    private JFrame frame;
    //constructor
    public InicioSesion(Connection conexion, MenuPrincipal menu,JFrame frame) {
        initComponents();
        this.conexion=conexion;
        this.frame=frame;
        sesion = new Sesion(this.conexion,frame);
        this.menu= menu;
    }
    // valida a un usuario
    public void validarUsuario(){
        String tipoUsuario=sesion.verificarUsuario(textoUsuario.getText(), textoContraseña.getText());
        if(tipoUsuario==null){
            JOptionPane.showMessageDialog(null,"Los datos no estan correctos");
        }else{// si es asi entonces agrega al frame
            menu.agregarFrame(tipoUsuario,textoUsuario.getText());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();
        botonIniciarSesion = new javax.swing.JButton();
        textoContraseña = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("Ingrese Su Usuario Y Contraseña");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        botonIniciarSesion.setText("Iniciar Sesion");
        botonIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonIniciarSesion)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(textoContraseña))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(44, 44, 44)
                                .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonIniciarSesion)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarSesionActionPerformed
        validarUsuario();
    }//GEN-LAST:event_botonIniciarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField textoContraseña;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
