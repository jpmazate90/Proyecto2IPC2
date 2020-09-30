package Logica;

import Entidades.Cliente;
import Entidades.Habitacion;
import Entidades.Menu;
import Entidades.Reservacion;
import Entidades.Usuario;
import Exportacion.PDF;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class LogicaHotel {
// atributos de la clase
    private Connection conexion;
    private LogicaHabitacion habitacion;
// constructor de la clase
    public LogicaHotel(Connection conexion) {
        this.conexion = conexion;
        habitacion = new LogicaHabitacion(conexion);
    }
// obtiene un cliente
    public Cliente obtenerCliente(String idAlojamiento) {
        int idAlojamientoInt = Integer.parseInt(idAlojamiento);
        Cliente cliente;
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT CLIENTE.Dpi,CLIENTE.Nombre,CLIENTE.Apellido,CLIENTE.Nit FROM ALOJAMIENTO JOIN CLIENTE JOIN RESERVACION WHERE ALOJAMIENTO.Id_Reservacion=RESERVACION.Id AND RESERVACION.Dpi_Cliente=CLIENTE.Dpi AND ALOJAMIENTO.Id=?");
            declaracion.setInt(1, idAlojamientoInt);
            ResultSet resultado = declaracion.executeQuery();

            resultado.next();
            cliente = new Cliente(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4));
            return cliente;
        } catch (SQLException ex) {
            return null;
        }
    }
// cambia el check oiut
    public void cambiarCheckOut(String idReservacion, boolean checkOut) {
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("UPDATE ALOJAMIENTO SET Check_Out=? WHERE Id=?");
            declaracion.setBoolean(1, checkOut);
            declaracion.setInt(2, Integer.parseInt(idReservacion));
            declaracion.executeUpdate();

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
// mira las habitaciones
    public ArrayList<HabitacionBooleana> verHabitaciones(boolean ocupadas,Date fecha) {
        ArrayList<HabitacionBooleana> habitaciones = arregloHabitaciones();
        long tiempo = fecha.getTime();
        java.sql.Date fechaInicialSql = new java.sql.Date(tiempo);
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT HABITACION.Id,RESERVACION.Id FROM ALOJAMIENTO JOIN HABITACION JOIN RESERVACION WHERE ALOJAMIENTO.Id_Reservacion= RESERVACION.Id AND RESERVACION.Id_Habitacion=HABITACION.Id AND ALOJAMIENTO.Check_Out=0 AND RESERVACION.Fecha_Entrada<=? AND RESERVACION.Fecha_Salida>=?");
            declaracion.setDate(1, fechaInicialSql);// mira las habitaciones
            declaracion.setDate(2, fechaInicialSql);
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[3];
            while (resultado.next()) {// mira las habitaciones
                HabitacionBooleana habitacionComparacion = new HabitacionBooleana(resultado.getInt(1), resultado.getInt(2));
                for (int i = 0; i < habitaciones.size(); i++) {
                    if (habitaciones.get(i).getId() == habitacionComparacion.getId()) {
                        habitaciones.get(i).setEstaOcupada(true);// mira las habitaciones
                        habitaciones.get(i).setIdReservacion(habitacionComparacion.getIdReservacion());
                        break;
                    }
                }
            }// maneja el resultado

            return habitaciones;

        } catch (SQLException ex) {
            return null;
        }
    }
// devuelve un arraylist con las habitaciones
    public ArrayList<HabitacionBooleana> arregloHabitaciones() {
        ArrayList<HabitacionBooleana> habitaciones = new ArrayList<>();

        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM HABITACION");
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[4];// devuelve un arraylist con las habitaciones
            while (resultado.next()) {
                Habitacion habitacion = new Habitacion(resultado.getInt(1), resultado.getString(2),
                        resultado.getInt(3), resultado.getInt(4));
                HabitacionBooleana nuevaHabitacion = new HabitacionBooleana(habitacion.getId());
                habitaciones.add(nuevaHabitacion);
            }// maneja el resultado 

            return habitaciones;
        } catch (SQLException ex) {
            return null;
        }
    }
// mete los datos de las habitaciones ocupadas
    public void introducirDatosHabitacionesOcupadas(ArrayList<HabitacionBooleana> habitaciones, TablaModelo modelo) {
        PreparedStatement declaracion;// prepara la orden 
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).isEstaOcupada() == true) {
                try {// mete los datos de las habitaciones ocupadas
                    declaracion = conexion.prepareStatement("SELECT CLIENTE.Dpi, CLIENTE.Nombre,CLIENTE.Apellido,HABITACION.Tipo_Habitacion,HABITACION.Nivel FROM ALOJAMIENTO JOIN RESERVACION JOIN CLIENTE JOIN HABITACION WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND RESERVACION.Dpi_Cliente=CLIENTE.Dpi AND RESERVACION.Id_Habitacion=HABITACION.Id AND RESERVACION.Id=?");
                    declaracion.setInt(1, habitaciones.get(i).getIdReservacion());
                    ResultSet resultado = declaracion.executeQuery();
                    resultado.next();
                    Object objeto[] = new Object[8];// mete los datos de las habitaciones ocupadas
                    objeto[0]= habitaciones.get(i).getId();
                    objeto[1]= resultado.getString(4);
                    objeto[2]= resultado.getInt(5);
                    objeto[3]= habitaciones.get(i).isEstaOcupada();
                    objeto[4]= habitaciones.get(i).getIdReservacion();
                    objeto[5]= resultado.getString(1);// mete los datos de las habitaciones ocupadas
                    objeto[6]= resultado.getString(2);// mete los datos de las habitaciones ocupadas
                    objeto[7]= resultado.getString(3);
                    modelo.addRow(objeto);// mete los datos de las habitaciones ocupadas

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    // introduce los datos de las habitaciones
    public void introducirDatosHabitacionesDisponibles(ArrayList<HabitacionBooleana> habitaciones, TablaModelo modelo) {
        PreparedStatement declaracion;// prepara la orden 
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).isEstaOcupada() == false) {
                    try {// introduce los datos de las habitaciones
                    declaracion = conexion.prepareStatement("SELECT * FROM HABITACION WHERE Id=?");
                    declaracion.setInt(1, habitaciones.get(i).getId());
                    ResultSet resultado = declaracion.executeQuery();
                    resultado.next();// introduce los datos de las habitaciones
                    Object objeto[] = new Object[4];
                    objeto[0]= habitaciones.get(i).getId();
                    objeto[1]= resultado.getString(2);// introduce los datos de las habitaciones
                    objeto[2]= resultado.getInt(3);
                    objeto[3]= habitaciones.get(i).isEstaOcupada();
                    // introduce los datos de las habitaciones
                    modelo.addRow(objeto);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }// introduce los datos d elas reservaciones
    public void introducirReservaciones(TablaModelo modelo) {
        PreparedStatement declaracion;// prepara la orden 
        try {
            declaracion = conexion.prepareStatement("SELECT * FROM RESERVACION");
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {
                Object objeto[] = new Object[7];
                objeto[0] = resultado.getInt(1);// introduce los datos d elas reservaciones
                objeto[1] = resultado.getString(2);
                objeto[2] = resultado.getInt(3);
                objeto[3] = resultado.getDate(4);
                objeto[4] = resultado.getDate(5);
                objeto[5] = resultado.getInt(6);
                objeto[6] = resultado.getBoolean(7);// introduce los datos d elas reservaciones

                modelo.addRow(objeto);// introduce los datos d elas reservaciones
            }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
    }// introduce los clientes
    public void introducirClientes(TablaModelo modelo) {
        PreparedStatement declaracion;// prepara la orden 
        try {
            declaracion = conexion.prepareStatement("SELECT * FROM CLIENTE");
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {// introduce los clientes
                Object objeto[] = new Object[4];
                objeto[0] = resultado.getString(1);
                objeto[1] = resultado.getString(2);// introduce los clientes
                objeto[2] = resultado.getString(3);
                objeto[3] = resultado.getString(4);
                modelo.addRow(objeto);
            }// introduce los clientes

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
    }
    // valida la informacion del cliente
    public boolean validarInformacionCliente(String dpi, String nombre, String apellido, String nit){
        if(dpi==null || dpi.equals("")|| nombre==null||nombre.equals("")||apellido==null||apellido.equals("")|| nit==null||nit.equals("")){
            return false;
        }else{// valida la informacion del cliente
            return true;
        }
        
    }
    // edita la informacion de un cliente
    public void editarItemCliente(Cliente cliente, String nombreAntiguo) {
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("UPDATE CLIENTE SET Dpi=?,Nombre=?,Apellido=?,Nit=? WHERE Dpi=?");
            declaracion.setString(1, cliente.getDpi());
            declaracion.setString(2, cliente.getNombre());
            declaracion.setString(3,cliente.getApellido());
            declaracion.setString(4,cliente.getNit());// edita la informacion de un cliente
            declaracion.setString(5, nombreAntiguo);
            declaracion.executeUpdate();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "El cliente con nombre: " + cliente.getNombre() + " NO se puede actualizar");
        }
    }
// elimina un item del cliente
    public void eliminarItemCliente(String dpi) {
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("DELETE FROM CLIENTE WHERE Dpi=?");
            declaracion.setString(1, dpi);
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null, "El cliente con dpi: " +dpi + " se elimino correctamente");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente con dpi: " + dpi);
        }
    }
// crea un cliente
    public void crearItemCliente(Cliente cliente) {
        try {
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO CLIENTE(Dpi,Nombre,Apellido,Nit) VALUES (?,?,?,?);");
            declaracion.setString(1, cliente.getDpi());// maneja el resultado 
            declaracion.setString(2,cliente.getNombre());
            declaracion.setString(3, cliente.getApellido());
            declaracion.setString(4, cliente.getNit());
            declaracion.executeUpdate();// maneja el resultado 

            JOptionPane.showMessageDialog(null, "se creo correctamente el cliente " + cliente.getNombre());

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "El cliente con nombre: " +cliente.getNombre() + "  NO se puede crear");
        }
    }
    // agrega las habitaciones
    public void agregarHabitaciones(TablaModelo modelo){
        PreparedStatement declaracion;// prepara la orden 
        try {
            declaracion = conexion.prepareStatement("SELECT * FROM HABITACION");
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {
                Object objeto[] = new Object[4];
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getString(2);
                objeto[2] = resultado.getInt(3);
                objeto[3] = resultado.getInt(4);
                modelo.addRow(objeto);
            }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
    }// verifica el precio
    public boolean verificarPrecio(String precio){
        if(precio==null || precio.equals("")){
            return false;
        }else{
            try{
            Integer.parseInt(precio);
            return true;
            }catch(Exception e){
                return false;
            }
        }
    }
    // actualiza el precio
    public void actualizarPrecio(String precio,String id){
        int idInt=Integer.parseInt(id);
        int precioInt=Integer.parseInt(precio);// actualiza el precio
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("UPDATE HABITACION SET Precio=? WHERE Id=?");
            declaracion.setInt(1, precioInt);
            declaracion.setInt(2, idInt);// actualiza el precio
            declaracion.executeUpdate();// actualiza el precio
            JOptionPane.showMessageDialog(null, "La Habitacion con id: " + id + " se actualizo correctamente");
        } catch (HeadlessException | SQLException e) {// actualiza el precio
            JOptionPane.showMessageDialog(null, "La Habitacion con id: " + id + " NO se puede actualizar");
        }
    }
    // agrega un usuario
    public void agregarUsuarios(TablaModelo modelo){
        PreparedStatement declaracion;// prepara la orden 
        try {// agrega un usuario
            declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Usuario!='admin'");
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {// agrega un usuario
                Object objeto[] = new Object[5];
                objeto[0] = resultado.getString(1);
                objeto[1] = resultado.getString(2);// agrega un usuario
                objeto[2] = resultado.getString(3);
                objeto[3] = resultado.getString(4);
                objeto[4] = resultado.getString(5);// agrega un usuario
                modelo.addRow(objeto);
            }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } 
    }
    // actualiza el usuario
    public void actualizarUsuario(String usuarioViejo,Usuario usuario){
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("UPDATE USUARIO SET Usuario=?,Contraseña=?,Nombre=?,Apellido=?,Tipo_Usuario=? WHERE Usuario=?");
            declaracion.setString(1,usuario.getUsuario());
            declaracion.setString(2,usuario.getContraseña());
            declaracion.setString(3,usuario.getNombre()); // actualiza el usuario
            declaracion.setString(4,usuario.getApellido());
            declaracion.setString(5,usuario.getTipoUsuario());
            declaracion.setString(6,usuarioViejo); // actualiza el usuario
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null, "El usuario viejo: " + usuarioViejo + " se actualizo correctamente");
        } catch (HeadlessException | SQLException e) { // actualiza el usuario
            JOptionPane.showMessageDialog(null, "EL usuario viejo: " + usuarioViejo+ " NO se puede actualizar");
        }
    }
    // elimina al usuario
    public void eliminarUsuario(String usuario){
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("DELETE FROM USUARIO WHERE Usuario=?");
            declaracion.setString(1, usuario);
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null, "El usuario " +usuario + " se elimino correctamente");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario: " + usuario);
        }
    }
    // libera la reservacion
    public void liberarReservacion() {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT RESERVACION.Id, RESERVACION.Dpi_Cliente,RESERVACION.Id_Habitacion,RESERVACION.Fecha_Entrada,RESERVACION.Fecha_Salida,RESERVACION.Precio,RESERVACION.Check_In,ALOJAMIENTO.Id FROM ALOJAMIENTO JOIN RESERVACION WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND RESERVACION.Check_In=1 AND ALOJAMIENTO.Check_Out=0");
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[7];
            while (resultado.next()) {
                Reservacion reservacion = new Reservacion(resultado.getInt(1), resultado.getString(2),
                        resultado.getInt(3), resultado.getDate(4), resultado.getDate(5), resultado.getInt(6), resultado.getBoolean(7));
                int idALojamiento=resultado.getInt(8);
                continuacionLiberarAlojamiento(reservacion,idALojamiento);
            }// maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }// continua con la liberacion del alojamiento
    public void continuacionLiberarAlojamiento(Reservacion reservacion,int idAlojamiento) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
        int resultado = fechaActual.compareTo(reservacion.getFechaSalida());
        if (resultado > 0) {
            String fechaActualString = formato.format(fechaActual);
            String fechaReservacion = formato.format(reservacion.getFechaSalida());
            if (fechaActualString.equals(fechaReservacion) == false) {
                hacerCheckOutObligatorio(Integer.toString(idAlojamiento), false);
            }
        }
    }
    // hace el check out obligatorio
    public void hacerCheckOutObligatorio(String idAlojamiento, boolean checkIn) {
        Cliente cliente = obtenerCliente(idAlojamiento);
        TablaModelo modelo = new TablaModelo();
        agregarDatosModeloCheckOut(modelo);
        habitacion.introducirConsumosALojamiento(idAlojamiento, modelo);
        int total = agregarTotaModelo(modelo);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Ocurrio un problema con la finalizacion del check out por favor intentalo de nuevo");
        } else {
            cambiarCheckOut(idAlojamiento,true);// si ya paso la fecha desaloja
            File archivo = new File("Factura de "+cliente.getDpi()+idAlojamiento);
            int totalAlojamiento = obtenerTotalPagoAlojamientoPorIdALojamiento(idAlojamiento);
            int totalDefinitivo = total+totalAlojamiento;// agrega el total definitivo
            PDF.exportaFactura(modelo, total,cliente.getNombre()+" "+cliente.getApellido(),cliente.getNit(),archivo,totalAlojamiento);
            JOptionPane.showMessageDialog(null, "Se pago el total de: "+totalDefinitivo+" en el check out obligatorio de id alojamiento: "+idAlojamiento);
        }
    }// crea el modelo de un modelo
    public void agregarDatosModeloCheckOut(TablaModelo modelo) {
        modelo.addColumn("ID CONSUMO");
        modelo.addColumn("ID ALOJAMIENTO");
        modelo.addColumn("NOMBRE COMIDA");
        modelo.addColumn("PRECIO COMIDA");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("FECHA CONSUMO");
    }
    // agrega el toal del modelo
    public int agregarTotaModelo(TablaModelo modelo) {
        int total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int precio = (Integer) modelo.getValueAt(i, 3);
            int cantidad = (Integer) modelo.getValueAt(i, 4);
            int subtotal = precio * cantidad;
            total += subtotal;

        }
        return total;
    }
// obtiene la informacion de un cliente por su dpi
    
    public Cliente obtenerClientePorDPI(String dpi) {
        Cliente cliente;
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE Dpi=?");
            declaracion.setString(1, dpi);// obtiene la informacion de un cliente por su dpi
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// obtiene la informacion de un cliente por su dpi
            cliente = new Cliente(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4));
            return cliente;
        } catch (SQLException ex) {
            return null;
        }
    }
    // obtiene el total de alojamiento
    public int obtenerTotalPagoAlojamientoPorIdALojamiento(String idAlojamiento){
        int id=Integer.parseInt(idAlojamiento);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT RESERVACION.Id, RESERVACION.Dpi_Cliente,RESERVACION.Id_Habitacion,RESERVACION.Fecha_Entrada,RESERVACION.Fecha_Salida,RESERVACION.Precio,RESERVACION.Check_In,ALOJAMIENTO.Id FROM ALOJAMIENTO JOIN RESERVACION WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND RESERVACION.Check_In=1 AND ALOJAMIENTO.Check_Out=0 AND ALOJAMIENTO.Id=?");
            declaracion.setInt(1, id);    // obtiene el total de alojamiento
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[7];
            resultado.next();    // obtiene el total de alojamiento
               Reservacion reservacion = new Reservacion(resultado.getInt(1), resultado.getString(2),
                        resultado.getInt(3), resultado.getDate(4), resultado.getDate(5), resultado.getInt(6), resultado.getBoolean(7));
               int total= habitacion.hacerTotalAlojamiento(formato.format(reservacion.getFechaEntrada()),formato.format(reservacion.getFechaSalida()),Integer.toString( reservacion.getPrecio()));
               return total;
            // maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

}
