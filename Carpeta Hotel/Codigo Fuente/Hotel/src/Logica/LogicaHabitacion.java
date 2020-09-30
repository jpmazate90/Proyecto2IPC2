package Logica;

import Entidades.Alojamiento;
import Entidades.Cliente;
import Entidades.Habitacion;
import Entidades.Reservacion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LogicaHabitacion {
// atributos de la clase
    private Connection conexion;

    public LogicaHabitacion(Connection conexion) {
        this.conexion = conexion;
    }
// agrgea los datos de la habitacion
    public void agregarDatosHabitacion(DefaultTableModel modelo) {

        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM HABITACION");
            ResultSet resultado = declaracion.executeQuery();

            while (resultado.next()) {// maneja el resultado 
                Object datos[] = new Object[4];
                Habitacion habitacion = new Habitacion(resultado.getInt(1), resultado.getString(2), resultado.getInt(3), resultado.getInt(4));
                datos[0] = habitacion.getId();
                datos[1] = habitacion.getTipoHabitacion();
                datos[2] = habitacion.getNivel();
                datos[3] = habitacion.getPrecio();
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {

        }
    }
// verifica las fechas 
    public boolean verificarFechas(Date fechaInicial, Date fechaFinal) {
        if (fechaInicial == null || fechaFinal == null) {
            return false;
        } else {
            int valor = fechaInicial.compareTo(fechaFinal);
            if (valor < 0) {
                return true;
            } else {
                return false;
            }
        }

    }
// verifica la disponibilidad
    public boolean verificarDisponibilidad(Date fechaInicial, Date fechaFinal, String id) {
        int idNumero = Integer.parseInt(id);
        long tiempo = fechaInicial.getTime();
        java.sql.Date fechaSql = new java.sql.Date(tiempo);
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM RESERVACION WHERE Id_Habitacion=? AND (Check_In IS NULL OR Check_In=1)");
            declaracion.setInt(1, idNumero);
            ResultSet resultado = declaracion.executeQuery();
// maneja el resultado
            while (resultado.next()) {// maneja el resultado 
                Date fechaInicialTabla = resultado.getDate(4);
                Date fechaFinalTabla = resultado.getDate(5);
                if (compararFechas(fechaInicial, fechaFinal, fechaInicialTabla, fechaFinalTabla) == false) {
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
// compara las fechas y mria si estan congruentes
    public boolean compararFechas(Date fechaInicial, Date fechaFinal, Date fechaInicialTabla, Date fechaFinalTabla) {
        int valor = fechaInicial.compareTo(fechaFinalTabla);
        if (valor >= 0) {
            return true;

        } else {
            int valorInicialConInicialTabla = fechaInicial.compareTo(fechaInicialTabla);
            int valorFinal = fechaFinal.compareTo(fechaInicialTabla);
            if (valorFinal <= 0 && valorInicialConInicialTabla < 0) {
                return true;
            } else {
                return false;
            }
        }
    }
// verifica los cambos del cliente
    public boolean verificarCampoDpiCliente(String cliente) {
        if (cliente == null || cliente.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public Cliente buscarInformacionCliente(String dpi) {
        Cliente cliente;// busca la informacion
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE Dpi=?");
            declaracion.setString(1, dpi);
            ResultSet resultado = declaracion.executeQuery();
            resultado.next(); // maneja el resultado 
            cliente = new Cliente(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4));
            return cliente;

        } catch (SQLException ex) {
            return null;
        }

    }
// crea una reservacion
    public void crearReservacion(Cliente cliente, Date fechaInicial, Date fechaFinal, String idHabitacion, int precio) {
        long tiempo = fechaInicial.getTime();
        java.sql.Date fechaInicialSql = new java.sql.Date(tiempo);
        long tiempoFinal = fechaFinal.getTime();
        java.sql.Date fechaFinalSql = new java.sql.Date(tiempoFinal);
        try {
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO RESERVACION(Dpi_Cliente,Id_Habitacion,Fecha_Entrada,Fecha_Salida,Precio) VALUES (?,?,?,?,?);");
            declaracion.setString(1, cliente.getDpi());// maneja el resultado 
            declaracion.setInt(2, Integer.parseInt(idHabitacion));
            declaracion.setDate(3, fechaInicialSql);// maneja el resultado 
            declaracion.setDate(4, fechaFinalSql);
            declaracion.setInt(5, precio);

            declaracion.executeUpdate();// maneja el resultado 
            int numeroReservacion = verNumeroReservacion();

            JOptionPane.showMessageDialog(null, "se creo correctamente la reservacion para el cliente: " + cliente.getDpi() + "\n su numero de reservacion es: " + numeroReservacion);

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
// mira e lnumero de reservacion
    public int verNumeroReservacion() {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM RESERVACION ORDER BY Id DESC;");
            ResultSet resultado = declaracion.executeQuery();
            resultado.next(); // maneja el resultado 
            int numeroReservacion = resultado.getInt(1);
            return numeroReservacion;

        } catch (SQLException ex) {
            return 0;
        }
    }
// verifica los campos de un cliente
    public boolean verificarCamposCreacionCliente(String nombre, String apellido, String nit) {
        if (nombre == null || nombre.equals("") || apellido == null || apellido.equals("") || nit == null || nit.equals("")) {
            return false;
        } else {
            try {
                Integer.parseInt(nit);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
// mira si un cliente cumple
    public void crearCliente(Cliente cliente) {
        try {
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO CLIENTE(Dpi,Nombre,Apellido,Nit) VALUES (?,?,?,?);");
            declaracion.setString(1, cliente.getDpi());// maneja el resultado 
            declaracion.setString(2, cliente.getNombre());
            declaracion.setString(3, cliente.getApellido());// maneja el resultado 
            declaracion.setString(4, cliente.getNit());

            declaracion.executeUpdate();// maneja el resultado 

            JOptionPane.showMessageDialog(null, "se creo correctamente la informacion del cliente " + cliente.getNombre());

        } catch (HeadlessException | SQLException e) {
        }
    }
// agrega los datos de reservaciones activas
    public void agregarReservacionesActivas(TablaModelo modelo) {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM RESERVACION WHERE Check_In IS NULL");
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[7];
            while (resultado.next()) {
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getString(2);
                objeto[2] = resultado.getInt(3);
                objeto[3] = resultado.getDate(4).toString();
                objeto[4] = resultado.getDate(5).toString();
                objeto[5] = resultado.getInt(6);
                objeto[6] = resultado.getBoolean(7);
                modelo.addRow(objeto);
            }// maneja el resultado 

        } catch (SQLException ex) {

        }
    }
// valid alos datos dle chek in
    public boolean validarDatosCheckIn(String texto, String fechaInicio) {
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = formato.format(fechaActual);
        try {
            if (texto == null || texto.equals("")) {
                return false;
            } else {
                Date fecha = formato.parse(fechaInicio);
                int comparacion = fechaActual.compareTo(fecha);
                if (fechaInicio.equals(fechaString)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (ParseException ex) {
            return false;
        }

    }
// valida un campo
    public boolean validarDatos(String texto) {
        if (texto == null || texto.equals("")) {
            return false;
        } else {
            return true;
        }
    }
// crea u nalojamiento
    public void crearAlojamiento(String idReservacion, int total) {
        boolean checkOut = false;
        try {
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO ALOJAMIENTO(Id_Reservacion,Check_Out) VALUES (?,?);");
            declaracion.setString(1, idReservacion);// maneja el resultado 
            declaracion.setBoolean(2, checkOut);
            declaracion.executeUpdate();// maneja el resultado 
            int numero = verNumeroAlojamiento();
            JOptionPane.showMessageDialog(null, "se registro correctamente el alojamiento"
                    + "\nSu numero de alojamiento es: " + numero);
            JOptionPane.showMessageDialog(null, "El total a pagar es: " + total);

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
// mra el numero de alojamiento
    public int verNumeroAlojamiento() {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM ALOJAMIENTO ORDER BY Id DESC;");
            ResultSet resultado = declaracion.executeQuery();
            resultado.next(); // maneja el resultado 
            int numeroAlojamiento = resultado.getInt(1);
            return numeroAlojamiento;

        } catch (SQLException ex) {
            return 0;
        }
    }
// cambia el check in 
    public void cambiarCheckIn(String idReservacion, boolean checkIn) {
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("UPDATE RESERVACION SET Check_In=? WHERE Id=?");
            declaracion.setBoolean(1, checkIn);
            declaracion.setInt(2, Integer.parseInt(idReservacion));
            declaracion.executeUpdate();

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
// borra los datos
    public void borrarDatos(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            if (modelo.getRowCount() > 0) {
                borrarDatos(modelo);
            }
        }
    }
// hace el total de alojamietno
    public int hacerTotalAlojamiento(String fechaInicial, String fechaFinal, String precio) {
        int precioInt = Integer.parseInt(precio);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicialDate = formato.parse(fechaInicial);
            Date fechaFinalDate = formato.parse(fechaFinal);
            int dias = (int) ((fechaFinalDate.getTime() - fechaInicialDate.getTime()) / 86400000);
            int total = precioInt * dias;
            return total;
        } catch (ParseException ex) {
            return 0;
        }
    }
// libera una reservacion
    public void liberarReservacion() {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM RESERVACION WHERE Check_In IS NULL");
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[7];
            while (resultado.next()) {
                Reservacion reservacion = new Reservacion(resultado.getInt(1), resultado.getString(2),
                        resultado.getInt(3), resultado.getDate(4), resultado.getDate(5), resultado.getInt(6), resultado.getBoolean(7));
                continuacionLiberarReservacion(reservacion);
            }// maneja el resultado 

        } catch (SQLException ex) {

        }
    }
// continua con la liberacion de la reservaciion
    public void continuacionLiberarReservacion(Reservacion reservacion) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
        int resultado = fechaActual.compareTo(reservacion.getFechaEntrada());
        if (resultado > 0) {
            String fechaActualString = formato.format(fechaActual);
            String fechaReservacion = formato.format(reservacion.getFechaEntrada());
            if (fechaActualString.equals(fechaReservacion) == false) {
                cambiarCheckIn(Integer.toString(reservacion.getId()), false);
            }
        }
    }
// agrega los datos de alojamientos activos
    public void agregarDatosAlojamientosActivos(TablaModelo modelo) {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM ALOJAMIENTO WHERE Check_Out=0");
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[3];
            while (resultado.next()) {
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);
                objeto[2] = resultado.getBoolean(3);
                modelo.addRow(objeto);
            }// maneja el resultado 

        } catch (SQLException ex) {

        }
    }
// obtiene las reservacion especifica
    public Reservacion obtenerReservacionEspecifica(String id) {
        int idInt = Integer.parseInt(id);
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM RESERVACION WHERE Id=?");
            declaracion.setInt(1, idInt);
            ResultSet resultado = declaracion.executeQuery();
            resultado.next(); // maneja el resultado 
            Reservacion reservacion = new Reservacion(resultado.getInt(1), resultado.getString(2),
                    resultado.getInt(3), resultado.getDate(4), resultado.getDate(5), resultado.getInt(6), resultado.getBoolean(7));
            return reservacion;
        } catch (SQLException ex) {
            return null;
        }
    }
// verifica la extencion de la fecha
    public boolean verificarExtencionFecha(String texto, Date fecha) {
        if (texto == null || texto.equals("")) {
            return false;
        } else {
            if (fecha == null) {
                return false;
            } else {
                return true;
            }
        }
    }
// verifica el rango de fechas
    public boolean verificarRangoFechas(Reservacion reservacion, Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        int resultado = fecha.compareTo(reservacion.getFechaSalida());
        if (resultado < 0) {
            return false;
//            String fechaString = formato.format(fecha);
//            String fechaReservacion= formato.format(reservacion.getFechaSalida());
//            if(fechaString.equals(fechaString)){
//                return false;
//            }else{
//                return true;
//            }
        } else {
            return verificarDisponibilidad(reservacion.getFechaSalida(), fecha, Integer.toString(reservacion.getIdHabitacion()));
        }
    }
// actualiza la estadia
    public void actualizarEstadia(Reservacion reservacion, Date fecha) {
        long tiempo = fecha.getTime();// crea una fecha de mysql 
        java.sql.Date fechaSql = new java.sql.Date(tiempo);
        try {// prepara la orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("UPDATE RESERVACION SET Fecha_Salida=? WHERE Id=?");
            declaracion.setDate(1, fechaSql);
            declaracion.setInt(2, reservacion.getId());
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se extendio la estadia de la reservacion: " + reservacion.getId());

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
// agrega los datos de alojamientos del check int
    public void agregarDatosAlojamientosCheckIn(TablaModelo modelo) {
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT ALOJAMIENTO.Id,ALOJAMIENTO.Id_Reservacion,ALOJAMIENTO.Check_Out,RESERVACION.Fecha_Salida FROM ALOJAMIENTO JOIN RESERVACION WHERE ALOJAMIENTO.Id_Reservacion=RESERVACION.Id AND ALOJAMIENTO.Check_Out=0");
            ResultSet resultado = declaracion.executeQuery();
            Object objeto[] = new Object[4];
            while (resultado.next()) {
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);
                objeto[2] = resultado.getBoolean(3);
                objeto[3] = resultado.getDate(4);
                modelo.addRow(objeto);
            }// maneja el resultado 

        } catch (SQLException ex) {

        }
    }
// agrega dinero a la extension de la estadia
    public void agregarDineroExtensionEstadia(Reservacion reservacion, Date fechaNueva) {
        Alojamiento alojamiento;
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM ALOJAMIENTO WHERE Id_Reservacion=?");

            declaracion.setInt(1, reservacion.getId());
            ResultSet resultado = declaracion.executeQuery();
            resultado.next(); // maneja el resultado 
            alojamiento = new Alojamiento(resultado.getInt(1), resultado.getInt(2), resultado.getBoolean(3));
            introducirDineroExtension(alojamiento, reservacion, fechaNueva);

        } catch (SQLException ex) {

        }
    }
// introduce dinero de la extension
    public void introducirDineroExtension(Alojamiento alojamiento, Reservacion reservacion, Date fechaNueva) {
        Date fechaActual = new Date();
        long tiempo = fechaActual.getTime();
        java.sql.Date fechaInicialSql = new java.sql.Date(tiempo);
        String extension = "EXTENSION ESTADIA";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicialString = formato.format(reservacion.getFechaSalida());
        String fechaFinalString = formato.format(fechaNueva);
        int total = hacerTotalAlojamiento(fechaInicialString, fechaFinalString, Integer.toString(reservacion.getPrecio()));
        int cantidad = 1;
        try {
            PreparedStatement declaracion;
            // dependiendo el tipo que se escogio escoge uno u otro
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO CONSUMO_RESTAURANTE(Id_Alojamiento,Nombre_Comida,Precio_Comida,Cantidad,Fecha_Consumo) VALUES (?,?,?,?,?);");
            declaracion.setInt(1, alojamiento.getId());
            declaracion.setString(2, extension);
            declaracion.setInt(3, total);
            declaracion.setInt(4, cantidad);
            declaracion.setDate(5, fechaInicialSql);
            declaracion.executeUpdate();// maneja el resultado 

            JOptionPane.showMessageDialog(null, "El total a pagar es : " + total);
        } catch (HeadlessException | SQLException e) {
           
        }
    }
// introduce el consumo de alojamientos
    public void introducirConsumosALojamiento(String idALojamiento, TablaModelo modelo) {
        int idAlojamientoInt = Integer.parseInt(idALojamiento);
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM CONSUMO_RESTAURANTE WHERE Id_Alojamiento=?");

            declaracion.setInt(1, idAlojamientoInt);
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {
                Object objeto[] = new Object[6];
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);
                objeto[2] = resultado.getString(3);
                objeto[3] = resultado.getInt(4);
                objeto[4] = resultado.getInt(5);
                objeto[5] = resultado.getDate(6);
                modelo.addRow(objeto);
            } // maneja el resultado 

        } catch (SQLException ex) {

        }
    }
// mira si se puede hacer check out 
    public boolean verSiPuedeHacerCheckOut(String fechaSalida) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
//        String prueba = "2018-10-19";
        String fechaActualString = formato.format(fechaActual);
//        fechaActualString = prueba;
        if (fechaSalida.equals(fechaActualString)) {
            return true;
        } else {
            return false;
        }

    }
}
