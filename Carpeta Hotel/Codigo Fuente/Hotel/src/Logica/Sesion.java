
package Logica;

import Entidades.Usuario;
import Enumerados.EnumeradoMenu;
import Enumerados.EnumeradosUsuarios;
import InterfazGrafica.AreaGerente;
import InterfazGrafica.AreaRecepcionista;
import InterfazGrafica.AreaRestaurante;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Sesion {
    // atributos privados
    private Connection conexion;
    private JFrame frame;
    // constructor
    public Sesion(Connection conexion, JFrame frame){
        this.conexion=conexion;
        this.frame=frame;
    }
    // mira un usuario si existe o no
    public String verificarUsuario(String usuario, String contraseña){
        try {// prepara la orden 
            PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Usuario=?");
            declaracion.setString(1, usuario);
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// ejecuta el resultado
                String usuarioEncontrado;
                String contraseñaEncontrada;
                String areaEncontrada;// maneja el resultado 
                usuarioEncontrado=resultado.getString("Usuario");
                contraseñaEncontrada=resultado.getString("Contraseña");// maneja el resultado 
                areaEncontrada=resultado.getString("Tipo_Usuario");// maneja el resultado 
                if(usuarioEncontrado==null){
                    return null;
                }else{// maneja el resultado 
                    if(contraseñaEncontrada.equals(contraseña)){
                        return areaEncontrada;
                    }else{
                        return null;
                    }
                }
        } catch (SQLException e) {
            return null;
        }
    }// agrega al frame lo que se desa introducir
    public void agregarFrame(String tipoUsuario, String usuario, JDesktopPane escritorio){
        EnumeradosUsuarios gerente= EnumeradosUsuarios.GERENTE;
        EnumeradosUsuarios recepcionista= EnumeradosUsuarios.RECEPCIONISTA;
        EnumeradosUsuarios empleado= EnumeradosUsuarios.EMPLEADO_RESTAURANTE;
        if(tipoUsuario.equals(gerente.getUsuario())){//crea el objeto
            AreaGerente area = new AreaGerente(conexion,frame,usuario,escritorio);
            limpiar(escritorio);// si el usuario es gerente lo manda a gerencia
            escritorio.add(area);
            area.show();//crea el objeto
        }else if(tipoUsuario.equals(recepcionista.getUsuario())){
            AreaRecepcionista area = new AreaRecepcionista(conexion, frame, usuario, escritorio);
            limpiar(escritorio);// si el usuario es recepcionista lo manda a recepcion
            escritorio.add(area);
            area.show();//crea el objeto
        }else if(tipoUsuario.equals(empleado.getUsuario())){
            AreaRestaurante area = new AreaRestaurante(conexion, frame, usuario, escritorio);
            limpiar(escritorio);// si el usuario es uno de restaurante lo manda a restaurante
            escritorio.add(area);
            area.show();//crea el objeto
        }
    }
 
     public void limpiar(JDesktopPane escritorio){
        escritorio.removeAll();// limpia el escritorio
        escritorio.repaint();
    }
     
     public boolean validarEspaciosUsuario(String[] datos){
        for(int i =0; i<datos.length;i++){
            if(datos[i].equals("") || datos[i]==null){
                return false;// valida un usuario
            }
        }
        return true;
    }
     // valida un usuario 
    public boolean validarUsuario(String usuario){
        try{// valida si xiste el usuario
                //ResultSet resultado = declaracion.executeQuery("SELECT * FROM USUARIO WHERE Usuario='"+usuario+"'");
                PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Usuario=?");
                declaracion.setString(1,usuario);
                ResultSet resultado=declaracion.executeQuery();
                resultado.next();// maneja el resultado 
                String usuarioEncontrado = resultado.getString("Usuario");
                if(usuarioEncontrado==null){
                    return true;// regresa true
                }else{
                    return false;
                }
// excepciones
        }catch(NumberFormatException e){
            
            return false;
        } catch (SQLException ex) {
            
            return true;
        }
    }
    
    public void crearUsuario(Usuario usuario){
        try {// crea a un usuario
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO USUARIO(Usuario,Contraseña,Nombre,Apellido,Tipo_Usuario) VALUES (?,?,?,?,?);");
            declaracion.setString(1,usuario.getUsuario());// maneja el resultado 
            declaracion.setString(2,usuario.getContraseña());
            declaracion.setString(3,usuario.getNombre());// maneja el resultado 
            declaracion.setString(4,usuario.getApellido());
            declaracion.setString(5,usuario.getTipoUsuario());

            declaracion.executeUpdate();// maneja el resultado 
            
                JOptionPane.showMessageDialog(null,"se creo correctamente el usuario: "+usuario.getUsuario());
           
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
// crea el arreglo de reportes, los nombres
    
    public static String[] arregloReportes(){
        String reportes[]={EnumeradoMenu.REPORTE_INGRESOS_HOTEL.toString(),EnumeradoMenu.REPORTE_INGRESOS_DE_UN_CLIENTE.toString(),
        EnumeradoMenu.REPORTE_INGRESOS_HABITACION.toString(),EnumeradoMenu.REPORTE_HABITACION_MAS_POPULAR.toString(),
        EnumeradoMenu.HABITACION_OCUPADA.toString(),EnumeradoMenu.HABITACION_DISPONIBLE.toString(),
        EnumeradoMenu.REPORTE_CONSUMOS_RESTAURANTE.toString(),EnumeradoMenu.REPORTE_CLIENTES.toString()};
        return reportes;
    }
}
