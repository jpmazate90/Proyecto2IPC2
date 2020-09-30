package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MostrarInformacion {
// atributos privados de la clase
    private Connection conexion;
    private LogicaHabitacion habitacion;
    private String habitacionPopular;

    public MostrarInformacion(Connection conexion) {
        this.conexion = conexion;
        habitacion = new LogicaHabitacion(conexion);
    }
// verifica dos fechas
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

    }// verifica una fecha especifia
    public boolean verificarFechaEspecifica(Date fecha){
        if(fecha==null|| fecha.equals("")){
            return false;
        }else{
            return true;
        }
    }
    // verifica un id
    public boolean verificarID(String id){
        if(id==null || id.equals("")){
            return false;
        }else{
            return true;
        }
    }
    // genera el reporte de ingresos al hotel
    public void reporteIngresosHotel(Date fechaInicial, Date fechaFinal,TablaModelo modeloConsumos, TablaModelo modeloAlojamientos){
        introducirConsumosHotelFechas(fechaInicial, fechaFinal, modeloConsumos);
        introducirPagosALojamientoHotelFechas(fechaInicial, fechaFinal, modeloAlojamientos);
        
    }
// mira los consumo de unas fechas
    public void introducirConsumosHotelFechas(Date fechaInicial, Date fechaFinal,TablaModelo modelo) {
        long tiempo = fechaInicial.getTime();
        java.sql.Date fechaInicialSql = new java.sql.Date(tiempo);
        long tiempo2= fechaFinal.getTime();
        java.sql.Date fechaFinalSQL = new java.sql.Date(tiempo2);
        try {// mira los consumo de unas fechas
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM CONSUMO_RESTAURANTE WHERE Fecha_Consumo>=? AND Fecha_Consumo<=?");
            declaracion.setDate(1,fechaInicialSql);
            declaracion.setDate(2,fechaFinalSQL);
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {// mira los consumo de unas fechas
                Object objeto[] = new Object[7];
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);// mira los consumo de unas fechas
                objeto[2] = resultado.getString(3);
                objeto[3] = resultado.getInt(4);
                objeto[4] = resultado.getInt(5);
                objeto[5] = resultado.getDate(6);// mira los consumo de unas fechas
                int total = (Integer)objeto[3]*(Integer)objeto[4];
                objeto[6]= total;
                modelo.addRow(objeto);// mira los consumo de unas fechas
            } // maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // pago de alojamiento en rango fchas
    public void introducirPagosALojamientoHotelFechas(Date fechaInicial, Date fechaFinal,TablaModelo modelo){
        long tiempo = fechaInicial.getTime();
        java.sql.Date fechaInicialSql = new java.sql.Date(tiempo);
        long tiempo2= fechaFinal.getTime();
        java.sql.Date fechaFinalSQL = new java.sql.Date(tiempo2);
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT ALOJAMIENTO.Id,ALOJAMIENTO.Id_Reservacion,RESERVACION.Fecha_Entrada, RESERVACION.Fecha_Salida,RESERVACION.Precio FROM RESERVACION JOIN ALOJAMIENTO WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND RESERVACION.Fecha_Entrada>=? AND RESERVACION.Fecha_Entrada<=? AND RESERVACION.Check_In=1;");
            declaracion.setDate(1,fechaInicialSql);
            declaracion.setDate(2,fechaFinalSQL);// pago de alojamiento en rango fchas
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {
                Object objeto[] = new Object[6];// pago de alojamiento en rango fchas
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);
                objeto[2] = resultado.getDate(3);
                objeto[3] = resultado.getDate(4);// pago de alojamiento en rango fchas
                objeto[4] = resultado.getInt(5);
                String fechaInicialProbar=objeto[2].toString();
                String fechaFinalProbar=objeto[3].toString();// pago de alojamiento en rango fchas
                String precioProbar = objeto[4].toString();// pago de alojamiento en rango fchas
                int total=habitacion.hacerTotalAlojamiento(fechaInicialProbar, fechaFinalProbar, precioProbar);
                objeto[5]= total;
                modelo.addRow(objeto);
            } // maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }// genera el reporte de ingresos de un cliente
    public void reporteIngresosCliente(Date fechaInicial, Date fechaFinal,TablaModelo modeloConsumos, TablaModelo modeloAlojamientos,String dpiCliente){
        introducirConsumosClienteFechas(fechaInicial, fechaFinal, modeloConsumos, dpiCliente);
        introducirPagosALojamientoClienteFechas(fechaInicial, fechaFinal, modeloAlojamientos, dpiCliente);
    }// mira los consumo de unas fechas
    public void introducirConsumosClienteFechas(Date fechaInicial, Date fechaFinal,TablaModelo modelo,String dpiCliente) {
        long tiempo = fechaInicial.getTime();
        java.sql.Date fechaInicialSql = new java.sql.Date(tiempo);
        long tiempo2= fechaFinal.getTime();
        java.sql.Date fechaFinalSQL = new java.sql.Date(tiempo2);
        try {// mira los consumo de unas fechas
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT CONSUMO_RESTAURANTE.Id_Consumo,CONSUMO_RESTAURANTE.Id_Alojamiento,CONSUMO_RESTAURANTE.Nombre_Comida,CONSUMO_RESTAURANTE.Precio_Comida,CONSUMO_RESTAURANTE.Cantidad,CONSUMO_RESTAURANTE.Fecha_Consumo FROM CONSUMO_RESTAURANTE JOIN ALOJAMIENTO JOIN RESERVACION WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND ALOJAMIENTO.Id=CONSUMO_RESTAURANTE.Id_Alojamiento AND RESERVACION.Dpi_Cliente=? AND  Fecha_Consumo>=? AND Fecha_Consumo<=?");
            declaracion.setString(1, dpiCliente);
            declaracion.setDate(2,fechaInicialSql);
            declaracion.setDate(3,fechaFinalSQL);
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {// mira los consumo de unas fechas
                Object objeto[] = new Object[7];
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);
                objeto[2] = resultado.getString(3);
                objeto[3] = resultado.getInt(4);// mira los consumo de unas fechas
                objeto[4] = resultado.getInt(5);
                objeto[5] = resultado.getDate(6);
                int total = (Integer)objeto[3]*(Integer)objeto[4];
                objeto[6]= total;// mira los consumo de unas fechas
                modelo.addRow(objeto);
            } // maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }// pago de alojamiento en rango fchas
    public void introducirPagosALojamientoClienteFechas(Date fechaInicial, Date fechaFinal,TablaModelo modelo,String dpiCliente){
        long tiempo = fechaInicial.getTime();
        java.sql.Date fechaInicialSql = new java.sql.Date(tiempo);
        long tiempo2= fechaFinal.getTime();
        java.sql.Date fechaFinalSQL = new java.sql.Date(tiempo2);// pago de alojamiento en rango fchas
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT ALOJAMIENTO.Id,ALOJAMIENTO.Id_Reservacion,RESERVACION.Fecha_Entrada, RESERVACION.Fecha_Salida,RESERVACION.Precio FROM RESERVACION JOIN ALOJAMIENTO WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND RESERVACION.Fecha_Entrada>=? AND RESERVACION.Fecha_Entrada<=? AND RESERVACION.Dpi_Cliente=? AND RESERVACION.Check_In=1;");
            declaracion.setDate(1,fechaInicialSql);
            declaracion.setDate(2,fechaFinalSQL);// pago de alojamiento en rango fchas
            declaracion.setString(3, dpiCliente);
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {
                Object objeto[] = new Object[6];
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);
                objeto[2] = resultado.getDate(3);// pago de alojamiento en rango fchas
                objeto[3] = resultado.getDate(4);
                objeto[4] = resultado.getInt(5);
                String fechaInicialProbar=objeto[2].toString();
                String fechaFinalProbar=objeto[3].toString();// pago de alojamiento en rango fchas
                String precioProbar = objeto[4].toString();
                int total=habitacion.hacerTotalAlojamiento(fechaInicialProbar, fechaFinalProbar, precioProbar);
                objeto[5]= total;
                modelo.addRow(objeto);
            } // maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // genera el reporte de habitacion
    public void reporteIngresosHabitacion(TablaModelo modeloConsumos, TablaModelo modeloAlojamientos,String idHabitacion){
        introducirConsumosHabitacion(modeloConsumos, idHabitacion);
        introducirPagosALojamientoHabitacion(modeloAlojamientos, idHabitacion);
    }// mira los consumo de unas fechas
    public void introducirConsumosHabitacion(TablaModelo modelo,String idHabitacion) {
        int idHabitacionInt = Integer.parseInt(idHabitacion);
        try {// mira los consumo de unas fechas
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT CONSUMO_RESTAURANTE.Id_Consumo,CONSUMO_RESTAURANTE.Id_Alojamiento,CONSUMO_RESTAURANTE.Nombre_Comida,CONSUMO_RESTAURANTE.Precio_Comida,CONSUMO_RESTAURANTE.Cantidad,CONSUMO_RESTAURANTE.Fecha_Consumo FROM CONSUMO_RESTAURANTE JOIN ALOJAMIENTO JOIN RESERVACION WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND ALOJAMIENTO.Id=CONSUMO_RESTAURANTE.Id_Alojamiento AND RESERVACION.Id_Habitacion=?");
            declaracion.setInt(1, idHabitacionInt);// mira los consumo de unas fechas
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {
                Object objeto[] = new Object[7];// mira los consumo de unas fechas
                objeto[0] = resultado.getInt(1);
                objeto[1] = resultado.getInt(2);// mira los consumo de unas fechas
                objeto[2] = resultado.getString(3);
                objeto[3] = resultado.getInt(4);
                objeto[4] = resultado.getInt(5);// mira los consumo de unas fechas
                objeto[5] = resultado.getDate(6);
                int total = (Integer)objeto[3]*(Integer)objeto[4];
                objeto[6]= total;// mira los consumo de unas fechas
                modelo.addRow(objeto);
            } // maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void introducirPagosALojamientoHabitacion(TablaModelo modelo,String idHabitacion){
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT ALOJAMIENTO.Id,ALOJAMIENTO.Id_Reservacion,RESERVACION.Fecha_Entrada, RESERVACION.Fecha_Salida,RESERVACION.Precio FROM RESERVACION JOIN ALOJAMIENTO WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND RESERVACION.Id_Habitacion=? AND RESERVACION.Check_In=1;");
            declaracion.setString(1, idHabitacion);
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {
                Object objeto[] = new Object[6];
                objeto[0] = resultado.getInt(1);// pago de alojamiento en rango fchas
                objeto[2] = resultado.getDate(3);
                objeto[3] = resultado.getDate(4);
                objeto[4] = resultado.getInt(5);// pago de alojamiento en rango fchas
                String fechaInicialProbar=objeto[2].toString();
                String fechaFinalProbar=objeto[3].toString();
                String precioProbar = objeto[4].toString();// pago de alojamiento en rango fchas
                int total=habitacion.hacerTotalAlojamiento(fechaInicialProbar, fechaFinalProbar, precioProbar);
                objeto[5]= total;
                modelo.addRow(objeto);
            } // maneja el resultado 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // reporte de la habitacion mas popular
    public void reporteHabitacionMasPopular(TablaModelo modelo){
        ArrayList<ControlVeces> control = new ArrayList<>();
        ControlVeces controlador;
        try {// pago de alojamiento en rango fchas
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT ALOJAMIENTO.Id,ALOJAMIENTO.Id_Reservacion,RESERVACION.Fecha_Entrada, RESERVACION.Fecha_Salida,RESERVACION.Precio, RESERVACION.Id_Habitacion FROM RESERVACION JOIN ALOJAMIENTO WHERE RESERVACION.Id=ALOJAMIENTO.Id_Reservacion AND RESERVACION.Check_In=1;");
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {// pago de alojamiento en rango fchas
                String nombre = Integer.toString(resultado.getInt(6));
                    int casilla = numeroObjeto(control,nombre);
                    if(casilla>=0){// maneja el resultado// pago de alojamiento en rango fchas
                        control.get(casilla).setVeces(control.get(casilla).getVeces()+1);
                    }else{// maneja el resultado
                        controlador = new ControlVeces(nombre);// pago de alojamiento en rango fchas
                        control.add(controlador);
                    }
            } // maneja el resultado 
            ordenamiento(control);
        int numero = control.size()-1;// el de hasta arriba es el que mas elementos tiene 
        String idHabitacionMasPopular = control.get(numero).getNombre();
        this.habitacionPopular= idHabitacionMasPopular;
        introducirDatosHabitacionMasPopular(modelo, idHabitacionMasPopular);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch(Exception e){
            
        }
    }
    // el nombre de la habitacon mas popular
    public String nombreHabitacionMasPopular(){
        return this.habitacionPopular;
    }
    // reporte de consumos hecchos en el restaurante
    public void reporteConsumosHechosRestaurante(Date fechaInicial, Date fechaFinal, TablaModelo modelo){
        introducirConsumosHotelFechas(fechaInicial, fechaFinal, modelo);
    }
    // los datos de la habitacion mas popular
    public void introducirDatosHabitacionMasPopular(TablaModelo modelo,String idHabitacion){
        introducirPagosALojamientoHabitacion(modelo, idHabitacion);
    }
    
    // numero de objetos que tienen 
    public int numeroObjeto(ArrayList<ControlVeces> controlador, String nombre){
        int casilla = -1;
        for(int i=0; i<controlador.size();i++){
            if(controlador.get(i).getNombre().equals(nombre)){
                return i;
            }
        }
        return casilla;
    }// es un quick sort que sirve para ordenar un arreglo
    public static ArrayList<ControlVeces> ordenamiento(ArrayList<ControlVeces> arregloNumerosDesordenados){
        ControlVeces variableAuxiliar;
        boolean cambios=false;
        // hace un ordenamiento de los muebles mas vendidos y menos 
        while(true){
            cambios=false;
            // si unno es menor que el otro hace cambio 
            for(int i=1;i<arregloNumerosDesordenados.size();i++){
                if(arregloNumerosDesordenados.get(i).getVeces()<arregloNumerosDesordenados.get(i-1).getVeces()){
                    variableAuxiliar=arregloNumerosDesordenados.get(i);
                    arregloNumerosDesordenados.set(i, arregloNumerosDesordenados.get(i-1));
                    arregloNumerosDesordenados.set(i-1, variableAuxiliar);
                    cambios=true;
                }
            }
            if(cambios==false){
                break;
            }
        } 
        return arregloNumerosDesordenados;
    }
}
