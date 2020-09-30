
package InterfazGrafica;

import Logica.Sesion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
//atributos
public class MenuPrincipal extends javax.swing.JFrame {
    private Connection conexion;
    private Sesion sesion;

    // contructor
    public MenuPrincipal(Connection conexion) throws SQLException {
        initComponents();
        this.conexion=conexion;
        sesion = new Sesion(conexion,this);
        JOptionPane.showMessageDialog(null, "conectado:" + conexion.getCatalog());
    }
    // agrega al frame
    public void agregarFrame(String tipoUsuario, String usuario){
        sesion.agregarFrame(tipoUsuario, usuario, this.escritorio);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Inicio Sesion");

        jMenuItem2.setText("Inicio Sesion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Cerrar Sesion");
        editMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenuActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Cerrar Sesion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem1);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       sesion.limpiar(escritorio);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        InicioSesion inicio = new InicioSesion(conexion,this,this);
        this.escritorio.add(inicio);
        inicio.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void editMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuActionPerformed
        sesion.limpiar(escritorio);
    }//GEN-LAST:event_editMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
