package Logica;

import Entidades.ConsumoRestaurante;
import Entidades.Habitacion;
import Entidades.Menu;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class LogicaMenu {
// atributo privado de la clase
    private Connection conexion;

    public LogicaMenu(Connection conexion) {
        this.conexion = conexion;
    }
// agrega los datos del menu
    public void agregarDatosMenu(TablaModelo modelo) {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM MENU WHERE Nombre_Comida!='EXTENSION ESTADIA'");
            ResultSet resultado = declaracion.executeQuery();

            while (resultado.next()) {// maneja el resultado 
                Object datos[] = new Object[4];
                Menu menu = new Menu(resultado.getString(1), resultado.getString(2), resultado.getInt(3));
                datos[0] = menu.getNombreComida();
                datos[1] = menu.getTipoComida();
                datos[2] = menu.getPrecio();
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
// valida la informacion del menu
    public boolean validarInformacionMenu(String nombreComida, String tipoComida, String precio) {
        if (nombreComida == null || nombreComida.equals("") || tipoComida == null || tipoComida.equals("")) {
            return false;
        } else {
            try {Integer.parseInt(precio);
                
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
// crea el item
    public void crearItem(Menu menu) {
        try {
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO MENU(Nombre_Comida,Tipo_Comida,Precio) VALUES (?,?,?);");
            declaracion.setString(1, menu.getNombreComida());// maneja el resultado 
            declaracion.setString(2, menu.getTipoComida());
            declaracion.setInt(3, menu.getPrecio());
            declaracion.executeUpdate();// maneja el resultado 

            JOptionPane.showMessageDialog(null, "se creo correctamente el item del menu " + menu.getNombreComida());

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "El item con nombre: " + menu.getNombreComida() + "  NO se puede crear");
        }
    }

    public void editarItem(Menu menu, String nombreAntiguo) {
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("UPDATE MENU SET Nombre_Comida=?,Tipo_Comida=?,Precio=? WHERE Nombre_Comida=?");
            declaracion.setString(1, menu.getNombreComida());
            declaracion.setString(2, menu.getTipoComida());
            declaracion.setInt(3, menu.getPrecio());// ejecuta la orden
            declaracion.setString(4, nombreAntiguo);
            declaracion.executeUpdate();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "El item con nombre: " + menu.getNombreComida() + " NO se puede actualizar");
        }
    }

    public void eliminarItem(String nombreComida) {
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("DELETE FROM MENU WHERE Nombre_Comida=?");
            declaracion.setString(1, nombreComida);
            declaracion.executeUpdate();// ejecuta la orden
            JOptionPane.showMessageDialog(null, "El item con nombre: " + nombreComida + " se elimino correctamente");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el item con nombre: " + nombreComida);
        }
    }
// verifica la cantidad
    public boolean verificarCantidad(String cantidad) {
        if (cantidad == null || cantidad.equals("")) {
            return false;
        } else {
            try {
                Integer.parseInt(cantidad);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
    // añade el consumo del menu
    public void añadirConsumo(ConsumoRestaurante consumo, TablaModelo modelo){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(consumo.getFechaConsumo());
        Object objeto[] = new Object[5];
        objeto[0]=consumo.getIdAlojamiento();
        objeto[1]=consumo.getNombreComida();
        objeto[2]=consumo.getPrecioComida();
        objeto[3]=consumo.getCantidad();
        objeto[4]=fecha;
        modelo.addRow(objeto);
    }
    // inserta datos en la tabla
    public void insertarConsumo(ConsumoRestaurante consumo){
        long tiempo= consumo.getFechaConsumo().getTime();
        java.sql.Date fechaInicialSql= new java.sql.Date(tiempo);
        
        try {
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO CONSUMO_RESTAURANTE(Id_Alojamiento,Nombre_Comida,Precio_Comida,Cantidad,Fecha_Consumo) VALUES (?,?,?,?,?);");
            declaracion.setInt(1, consumo.getIdAlojamiento());
            declaracion.setString(2, consumo.getNombreComida());
            declaracion.setInt(3,consumo.getPrecioComida());
            declaracion.setInt(4, consumo.getCantidad());
            declaracion.setDate(5, fechaInicialSql);
            declaracion.executeUpdate();// maneja el resultado 


        } catch (HeadlessException | SQLException e) {
        }
    }
}
