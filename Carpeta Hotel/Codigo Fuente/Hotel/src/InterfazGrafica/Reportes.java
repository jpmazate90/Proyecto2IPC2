package InterfazGrafica;

import Entidades.Cliente;
import Exportacion.HTML;
import Logica.HabitacionBooleana;
import Logica.LogicaHabitacion;
import Logica.LogicaHotel;
import Logica.MostrarInformacion;
import Logica.Sesion;
import Logica.TablaModelo;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Reportes extends javax.swing.JInternalFrame {
//atributos de la clase
    private Connection conexion;
    private String usuario;
    private String[] reportes;
    private TablaModelo modeloIngresosHotelConsumo;
    private TablaModelo modeloIngresosHotelAlojamiento;
    private MostrarInformacion informacion;
    private LogicaHotel hotel;
    private LogicaHabitacion habitacion;
    private TablaModelo modeloOcupadas;
    private TablaModelo modeloDisponibles;
    private TablaModelo modeloClientes;
// constructor de la clase
    public Reportes(Connection conexion, String usuario) {
        initComponents();
        this.conexion = conexion;
        this.usuario = usuario;
        informacion = new MostrarInformacion(conexion);
        habitacion = new LogicaHabitacion(conexion);
        hotel = new LogicaHotel(conexion);
        modeloIngresosHotelAlojamiento = new TablaModelo();
        modeloIngresosHotelConsumo = new TablaModelo();
        modeloOcupadas = new TablaModelo();
        modeloDisponibles = new TablaModelo();
        modeloClientes = new TablaModelo();
        asignarReportes();
        ocultarFiltros();
        agregarModelos();
    }
//asigna el arreglo de los reportes
    public void asignarReportes() {
        this.reportes = Sesion.arregloReportes();
    }

    public void agregarModelos() {// agrega las columnas de los modelos
        modeloIngresosHotelConsumo.addColumn("ID CONSUMO");
        modeloIngresosHotelConsumo.addColumn("ID ALOJAMIENTO");
        modeloIngresosHotelConsumo.addColumn("NOMBRE COMIDA");
        modeloIngresosHotelConsumo.addColumn("PRECIO COMIDA");// agrega las columnas de los modelos
        modeloIngresosHotelConsumo.addColumn(" CANTIDAD");
        modeloIngresosHotelConsumo.addColumn("FECHA CONSUMO");// agrega las columnas de los modelos
        modeloIngresosHotelConsumo.addColumn("TOTAL");
        modeloIngresosHotelAlojamiento.addColumn("ID ALOJAMIENTO");
        modeloIngresosHotelAlojamiento.addColumn("ID RESERVACION");// agrega las columnas de los modelos
        modeloIngresosHotelAlojamiento.addColumn("FECHA ENTRADA");
        modeloIngresosHotelAlojamiento.addColumn("FECHA SALIDA");// agrega las columnas de los modelos
        modeloIngresosHotelAlojamiento.addColumn("PRECIO");
        modeloIngresosHotelAlojamiento.addColumn("TOTAL DEL ALOJAMIENTO");
        modeloOcupadas.addColumn("ID HABITACION");// agrega las columnas de los modelos
        modeloOcupadas.addColumn("HABITACION");
        modeloOcupadas.addColumn("NIVEL");// agrega las columnas de los modelos
        modeloOcupadas.addColumn("OCUPADA");
        modeloOcupadas.addColumn("ID RESERVACION");
        modeloOcupadas.addColumn("DPI CLIENTE");
        modeloOcupadas.addColumn("NOMBRE");// agrega las columnas de los modelos
        modeloOcupadas.addColumn("APELLIDO");// agrega las columnas de los modelos
        modeloDisponibles.addColumn("ID Habitacion");
        modeloDisponibles.addColumn("HABITACION");
        modeloDisponibles.addColumn("NIVEL");// agrega las columnas de los modelos
        modeloDisponibles.addColumn("OCUPADA");
        modeloClientes.addColumn("DPI");// agrega las columnas de los modelos
        modeloClientes.addColumn("NOMBRE");
        modeloClientes.addColumn("APELLIDO");// agrega las columnas de los modelos
        modeloClientes.addColumn("NIT");// agrega las columnas de los modelos
    }

    public void ocultarFiltros() {// oculta los filtros
        labelFechaInicio.setVisible(true);
        labelFechaFinal.setVisible(true);
        dateFechaInicio.setVisible(true);
        dateFechaFinal.setVisible(true);// oculta los filtros
        botonReporte.setVisible(true);
        labelClienteDPI.setVisible(false);
        textoClienteDPI.setVisible(false);
        labelIDHabitacion.setVisible(false);// oculta los filtros
        textoIDHabitacion.setVisible(false);
        labelFechaEspecifica.setVisible(false);
        dateFechaEspecifica.setVisible(false);
        botonExportar.setVisible(false);// oculta los filtros
        labelTotal1.setVisible(false);// oculta los filtros
        labelTotal2.setVisible(false);
        totalTabla1.setVisible(false);
        totalTabla2.setVisible(false);
    }

    public void irReporte() {// agarra el valor del combo box
        String reporte = (String) comboBoxReportes.getSelectedItem();
        if (reporte.equals(reportes[0])) {// entra al reporte 0
            reporteIngresosHotel();
        }
        if (reporte.equals(reportes[1])) {// entra al reporte 1
            reporteIngresosCliente();
        }
        if (reporte.equals(reportes[2])) {// entra al reporte 2
            reporteIngresosHabitacion();

        }
        if (reporte.equals(reportes[3])) {// entra al reporte 3
            reporteHabitacionMasPopular();

        }
        if (reporte.equals(reportes[4])) {// entra al reporte 4
            reporteHabitacionesOcupadas();
        }
        if (reporte.equals(reportes[5])) {// entra al reporte 5
            reporteHabitacionesDisponibles();
        }
        if (reporte.equals(reportes[6])) {// entra al reporte 6
            reporteConsumosHechosRestaurante();
        }
        if (reporte.equals(reportes[7])) {// entra al reporte 7
            reporteVerClientes();
        }
    }
// borra los datos d elas tablas
    public void borrarDatosTablas() {
        habitacion.borrarDatos(modeloIngresosHotelConsumo);
        habitacion.borrarDatos(modeloIngresosHotelAlojamiento);
    }
// hace el reporte 1
    public void reporteIngresosHotel() {
        borrarDatosTablas();
        Date fechaInicial = dateFechaInicio.getDate();
        Date fechaFinal = dateFechaFinal.getDate();
        boolean sePuede = informacion.verificarFechas(fechaInicial, fechaFinal);
        if (sePuede == true) {// hace el reporte 1
            informacion.reporteIngresosHotel(fechaInicial, fechaFinal, modeloIngresosHotelConsumo, modeloIngresosHotelAlojamiento);
            tabla1.setModel(modeloIngresosHotelConsumo);
            tabla2.setModel(modeloIngresosHotelAlojamiento);// hace el reporte 1
            int totalConsumo = agregarTotal(modeloIngresosHotelConsumo, 6);
            int totalAlojamiento = agregarTotal(modeloIngresosHotelAlojamiento, 5);
            totalTabla1.setText(Integer.toString(totalConsumo));
            totalTabla2.setText(Integer.toString(totalAlojamiento));// hace el reporte 1
            labelTotal2.setText("TOTAL");
            labelTotal1.setVisible(true);
            labelTotal2.setVisible(true);// hace el reporte 1
            totalTabla1.setVisible(true);
            totalTabla2.setVisible(true);
            tabla1.setVisible(true);
            tabla2.setVisible(true);// hace el reporte 1
            botonExportar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No se puede realizar las fechas estan incorrectas");
        }
    }
// HACE el reporte 2
    public void reporteIngresosCliente() {
        borrarDatosTablas();// HACE el reporte 2
        Date fechaInicial = dateFechaInicio.getDate();// HACE el reporte 2
        Date fechaFinal = dateFechaFinal.getDate();
        String dpiCliente = textoClienteDPI.getText();
        boolean sePuede = informacion.verificarFechas(fechaInicial, fechaFinal);
        Cliente cliente = hotel.obtenerClientePorDPI(dpiCliente);
        if (sePuede == true) {// HACE el reporte 2
            informacion.reporteIngresosCliente(fechaInicial, fechaFinal, modeloIngresosHotelConsumo, modeloIngresosHotelAlojamiento, dpiCliente);
            tabla1.setModel(modeloIngresosHotelConsumo);// HACE el reporte 2
            tabla2.setModel(modeloIngresosHotelAlojamiento);
            int totalConsumo = agregarTotal(modeloIngresosHotelConsumo, 6);
            int totalAlojamiento = agregarTotal(modeloIngresosHotelAlojamiento, 5);
            totalTabla1.setText(Integer.toString(totalConsumo));
            totalTabla2.setText(Integer.toString(totalAlojamiento));
            labelTotal2.setText("TOTAL");
            labelTotal1.setVisible(true);
            labelTotal2.setVisible(true);// HACE el reporte 2
            totalTabla1.setVisible(true);
            totalTabla2.setVisible(true);
            tabla1.setVisible(true);// HACE el reporte 2
            tabla2.setVisible(true);
            botonExportar.setVisible(true);// HACE el reporte 2
        } else {
            JOptionPane.showMessageDialog(null, "No se puede realizar la operacion datos incorrectos");
        }
    }
// hace el reporte 3
    public void reporteIngresosHabitacion() {
        borrarDatosTablas();// hace el reporte 3
        String idHabitacion = textoIDHabitacion.getText();
        boolean sePuede = informacion.verificarID(idHabitacion);
        if (sePuede == true) {// hace el reporte 3
            informacion.reporteIngresosHabitacion(modeloIngresosHotelConsumo, modeloIngresosHotelAlojamiento, idHabitacion);
            tabla1.setModel(modeloIngresosHotelConsumo);
            tabla2.setModel(modeloIngresosHotelAlojamiento);// hace el reporte 3
            int totalConsumo = agregarTotal(modeloIngresosHotelConsumo, 6);// hace el reporte 3
            int totalAlojamiento = agregarTotal(modeloIngresosHotelAlojamiento, 5);
            totalTabla1.setText(Integer.toString(totalConsumo));
            totalTabla2.setText(Integer.toString(totalAlojamiento));
            labelTotal1.setVisible(true);// hace el reporte 3
            labelTotal2.setVisible(true);
            totalTabla1.setVisible(true);// hace el reporte 3
            totalTabla2.setVisible(true);
            labelTotal2.setText("TOTAL");
            tabla1.setVisible(true);// hace el reporte 3
            tabla2.setVisible(true);
            botonExportar.setVisible(true);
        } else {// hace el reporte 3
            JOptionPane.showMessageDialog(null, "No se puede realizar la operacion datos incorrectos");
        }
    }
// hace el reporte 4
    public void reporteHabitacionMasPopular() {
        borrarDatosTablas();// hace el reporte 4
        informacion.reporteHabitacionMasPopular(modeloIngresosHotelAlojamiento);
        String habitacionPopular = informacion.nombreHabitacionMasPopular();
        tabla2.setModel(modeloIngresosHotelAlojamiento);// hace el reporte 4
        int totalAlojamiento = agregarTotal(modeloIngresosHotelAlojamiento, 5);
        totalTabla2.setText(Integer.toString(totalAlojamiento));// hace el reporte 4
        labelTotal1.setVisible(false);// hace el reporte 4
        labelTotal2.setVisible(true);
        labelTotal2.setText(" ID DE LA HABITACION MAS POPULAR: ");
        totalTabla1.setVisible(false);// hace el reporte 4
        totalTabla2.setVisible(true);
        totalTabla2.setText(habitacionPopular);
        tabla1.setVisible(false);
        tabla2.setVisible(true);// hace el reporte 4
        botonExportar.setVisible(true);// hace el reporte 4
    }
// hace el reporte 5
    public void reporteHabitacionesOcupadas() {
        borrarDatosTablas();
        Date fecha = dateFechaEspecifica.getDate();// hace el reporte 5
        boolean sePuede = informacion.verificarFechaEspecifica(fecha);
        if (sePuede == true) {// hace el reporte 5
            ArrayList<HabitacionBooleana> habitaciones = hotel.verHabitaciones(false, fecha);
            verDatosAIntroducir(habitaciones, false);
            labelTotal1.setVisible(false);// hace el reporte 5
            labelTotal2.setVisible(false);
            totalTabla1.setVisible(false);
            totalTabla2.setVisible(false);// hace el reporte 5
            labelTotal2.setText("TOTAL");
            tabla1.setVisible(true);
            tabla2.setVisible(false);// hace el reporte 5
            botonExportar.setVisible(true);
        } else {// hace el reporte 5
            JOptionPane.showMessageDialog(null, "NO SE SELECCIONO NINGUNA FECHA");
        }
    }
// hace el reporte 6
    public void reporteHabitacionesDisponibles() {
        borrarDatosTablas();
        Date fecha = dateFechaEspecifica.getDate();
        boolean sePuede = informacion.verificarFechaEspecifica(fecha);
        if (sePuede == true) {
            ArrayList<HabitacionBooleana> habitaciones = hotel.verHabitaciones(true, fecha);
            verDatosAIntroducir(habitaciones, true);
            labelTotal1.setVisible(false);// hace el reporte 6
            labelTotal2.setVisible(false);
            totalTabla1.setVisible(false);
            labelTotal2.setText("TOTAL");// hace el reporte 6
            totalTabla2.setVisible(false);
            tabla1.setVisible(true);
            tabla2.setVisible(false);// hace el reporte 6
            botonExportar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "NO SE SELECCIONO NINGUNA FECHA");
        }
    }
// hace el reporte 
    public void reporteConsumosHechosRestaurante() {
        borrarDatosTablas();
        Date fechaInicial = dateFechaInicio.getDate();
        Date fechaFinal = dateFechaFinal.getDate();
        boolean sePuede = informacion.verificarFechas(fechaInicial, fechaFinal);
        if (sePuede == true) {
            informacion.reporteConsumosHechosRestaurante(fechaInicial, fechaFinal, modeloIngresosHotelConsumo);
            tabla1.setModel(modeloIngresosHotelConsumo);
            int totalConsumo = agregarTotal(modeloIngresosHotelConsumo, 6);
            totalTabla1.setText(Integer.toString(totalConsumo));
            labelTotal1.setVisible(true);// hace el reporte 
            labelTotal2.setVisible(false);// hace el reporte 
            totalTabla1.setVisible(true);
            totalTabla2.setVisible(false);
            labelTotal2.setText("TOTAL");
            tabla1.setVisible(true);// hace el reporte 
            tabla2.setVisible(false);
            botonExportar.setVisible(true);// hace el reporte 
        } else {
            JOptionPane.showMessageDialog(null, "No se puede realizar la operacion datos incorrectos");
        }
    }
// hace el reporte 8
    public void reporteVerClientes() {
        hotel.introducirClientes(modeloClientes);
        tabla1.setModel(modeloClientes);// hace el reporte 8
        labelTotal1.setVisible(false);
        labelTotal2.setVisible(false);
        totalTabla1.setVisible(false);// hace el reporte 8
        totalTabla2.setVisible(false);
        tabla1.setVisible(true);
        tabla2.setVisible(false);// hace el reporte 8
        botonExportar.setVisible(true);
        labelTotal2.setText("TOTAL");// hace el reporte 8
    }
// introduce los datos d ela habitacion booleana
    public void verDatosAIntroducir(ArrayList<HabitacionBooleana> habitaciones, boolean ver) {
        if (ver == false) {
            hotel.introducirDatosHabitacionesOcupadas(habitaciones, modeloOcupadas);
            tabla1.setModel(modeloOcupadas);
        } else {
            hotel.introducirDatosHabitacionesDisponibles(habitaciones, modeloDisponibles);
            tabla1.setModel(modeloDisponibles);
        }
    }
// agrega el total 
    public int agregarTotal(TablaModelo modelo, int posicion) {
        int total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int subtotal = (Integer) modelo.getValueAt(i, posicion);
            total += subtotal;
        }
        return total;
    }

    public void mostrarFiltros() {// muestra algunos filtros dependiendo de la comveniencia
        String reporte = (String) comboBoxReportes.getSelectedItem();
        if (reporte.equals(reportes[0])) {
            labelFechaInicio.setVisible(true);
            labelFechaFinal.setVisible(true);
            dateFechaInicio.setVisible(true);
            dateFechaFinal.setVisible(true);
            botonReporte.setVisible(true);
            labelClienteDPI.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            textoClienteDPI.setVisible(false);
            labelIDHabitacion.setVisible(false);
            textoIDHabitacion.setVisible(false);
            labelFechaEspecifica.setVisible(false);
            dateFechaEspecifica.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            botonExportar.setVisible(false);
            labelTotal1.setVisible(false);
            labelTotal2.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            totalTabla1.setVisible(false);
            totalTabla2.setVisible(false);
        }
        if (reporte.equals(reportes[1])) {
            labelFechaInicio.setVisible(true);
            labelFechaFinal.setVisible(true);// muestra algunos filtros dependiendo de la comveniencia
            dateFechaInicio.setVisible(true);
            dateFechaFinal.setVisible(true);
            botonReporte.setVisible(true);// muestra algunos filtros dependiendo de la comveniencia
            labelClienteDPI.setVisible(true);
            textoClienteDPI.setVisible(true);
            labelIDHabitacion.setVisible(false);
            textoIDHabitacion.setVisible(false);
            labelFechaEspecifica.setVisible(false);
            dateFechaEspecifica.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            botonExportar.setVisible(false);
            labelTotal1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelTotal2.setVisible(false);
            totalTabla1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            totalTabla2.setVisible(false);

        }
        if (reporte.equals(reportes[2])) {// muestra algunos filtros dependiendo de la comveniencia
            labelFechaInicio.setVisible(false);
            labelFechaFinal.setVisible(false);
            dateFechaInicio.setVisible(false);
            dateFechaFinal.setVisible(false);
            botonReporte.setVisible(true);// muestra algunos filtros dependiendo de la comveniencia
            labelClienteDPI.setVisible(false);
            textoClienteDPI.setVisible(false);
            labelIDHabitacion.setVisible(true);
            textoIDHabitacion.setVisible(true);
            labelFechaEspecifica.setVisible(false);
            dateFechaEspecifica.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            botonExportar.setVisible(false);
            labelTotal1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelTotal2.setVisible(false);
            totalTabla1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            totalTabla2.setVisible(false);
        }
        if (reporte.equals(reportes[3])) {
            labelFechaInicio.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelFechaFinal.setVisible(false);
            dateFechaInicio.setVisible(false);
            dateFechaFinal.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            botonReporte.setVisible(true);
            labelClienteDPI.setVisible(false);
            textoClienteDPI.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelIDHabitacion.setVisible(false);
            textoIDHabitacion.setVisible(false);
            labelFechaEspecifica.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            dateFechaEspecifica.setVisible(false);
            botonExportar.setVisible(false);
            labelTotal1.setVisible(false);
            labelTotal2.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            totalTabla1.setVisible(false);
            totalTabla2.setVisible(false);
        }
        if (reporte.equals(reportes[4])) {// muestra algunos filtros dependiendo de la comveniencia
            labelFechaInicio.setVisible(false);
            labelFechaFinal.setVisible(false);
            dateFechaInicio.setVisible(false);
            dateFechaFinal.setVisible(false);
            botonReporte.setVisible(true);
            labelClienteDPI.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            textoClienteDPI.setVisible(false);
            labelIDHabitacion.setVisible(false);
            textoIDHabitacion.setVisible(false);
            labelFechaEspecifica.setVisible(true);
            dateFechaEspecifica.setVisible(true);
            botonExportar.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelTotal1.setVisible(false);
            labelTotal2.setVisible(false);
            totalTabla1.setVisible(false);
            totalTabla2.setVisible(false);
        }
        if (reporte.equals(reportes[5])) {// muestra algunos filtros dependiendo de la comveniencia
            labelFechaInicio.setVisible(false);
            labelFechaFinal.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            dateFechaInicio.setVisible(false);
            dateFechaFinal.setVisible(false);
            botonReporte.setVisible(true);
            labelClienteDPI.setVisible(false);
            textoClienteDPI.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelIDHabitacion.setVisible(false);
            textoIDHabitacion.setVisible(false);
            labelFechaEspecifica.setVisible(true);
            dateFechaEspecifica.setVisible(true);
            botonExportar.setVisible(false);
            labelTotal1.setVisible(false);
            labelTotal2.setVisible(false);
            totalTabla1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia// muestra algunos filtros dependiendo de la comveniencia
            totalTabla2.setVisible(false);
        }// muestra algunos filtros dependiendo de la comveniencia
        if (reporte.equals(reportes[6])) {// muestra algunos filtros dependiendo de la comveniencia
            labelFechaInicio.setVisible(true);
            labelFechaFinal.setVisible(true);
            dateFechaInicio.setVisible(true);
            dateFechaFinal.setVisible(true);
            botonReporte.setVisible(true);
            labelClienteDPI.setVisible(false);
            textoClienteDPI.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelIDHabitacion.setVisible(false);
            textoIDHabitacion.setVisible(false);
            labelFechaEspecifica.setVisible(false);
            dateFechaEspecifica.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            botonExportar.setVisible(false);
            labelTotal1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            labelTotal2.setVisible(false);
            totalTabla1.setVisible(false);
            totalTabla2.setVisible(false);
        }
        if (reporte.equals(reportes[7])) {// muestra algunos filtros dependiendo de la comveniencia
            labelFechaInicio.setVisible(false);
            labelFechaFinal.setVisible(false);
            dateFechaInicio.setVisible(false);
            dateFechaFinal.setVisible(false);
            botonReporte.setVisible(true);
            labelClienteDPI.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            textoClienteDPI.setVisible(false);
            labelIDHabitacion.setVisible(false);
            textoIDHabitacion.setVisible(false);
            labelFechaEspecifica.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            dateFechaEspecifica.setVisible(false);
            botonExportar.setVisible(false);
            labelTotal1.setVisible(false);
            labelTotal2.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
            totalTabla1.setVisible(false);
            totalTabla2.setVisible(false);
        }

    }

    public void exportarHTML() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        File archivo = HTML.usarFileChooser();
        if (archivo.getName().equals("null.html") == false) {// exporta a html segun el reporte
            String reporte = (String) comboBoxReportes.getSelectedItem();
            if (reporte.equals(reportes[0])) {// entra al reporte 0// exporta a html segun el reporte
                HTML.generarTitulo(archivo, "REPORTE INGRESOS HOTEL");
                HTML.generarReporte0(archivo, modeloIngresosHotelConsumo, modeloIngresosHotelAlojamiento, totalTabla1.getText(), totalTabla2.getText(), formato.format(dateFechaInicio.getDate()), formato.format(dateFechaFinal.getDate()), this.usuario);
            }
            if (reporte.equals(reportes[1])) {// entra al reporte 1
                HTML.generarTitulo(archivo, "REPORTE INGRESOS HOTEL POR MEDIO DE UN CLIENTE");
                HTML.generarReporte1(archivo, modeloIngresosHotelConsumo, modeloIngresosHotelAlojamiento, totalTabla1.getText(), totalTabla2.getText(), formato.format(dateFechaInicio.getDate()), formato.format(dateFechaFinal.getDate()), textoClienteDPI.getText(), this.usuario);

            }
            if (reporte.equals(reportes[2])) {// entra al reporte 2
                HTML.generarTitulo(archivo, "REPORTE INGRESOS HOTEL POR MEDIO DE UNA HABITACION");
                HTML.generarReporte2(archivo, modeloIngresosHotelConsumo, modeloIngresosHotelAlojamiento, totalTabla1.getText(), totalTabla2.getText(), textoIDHabitacion.getText(), this.usuario);

            }
            if (reporte.equals(reportes[3])) {// entra al reporte 3
                HTML.generarTitulo(archivo, "REPORTE HABITACION MAS POPULAR");
                HTML.generarReporte3(archivo, modeloIngresosHotelAlojamiento, totalTabla2.getText(), this.usuario);

            }
            if (reporte.equals(reportes[4])) {// entra al reporte 4
                HTML.generarTitulo(archivo, "REPORTE HABITACIONES OCUPADAS");
                HTML.generarReporte4(archivo, modeloOcupadas, formato.format(dateFechaEspecifica.getDate()), this.usuario);
            }// exporta a html segun el reporte
            if (reporte.equals(reportes[5])) {// entra al reporte 5
                HTML.generarTitulo(archivo, "REPORTE HABITACIONES DISPONIBLES");
                HTML.generarReporte5(archivo, modeloDisponibles, formato.format(dateFechaEspecifica.getDate()), this.usuario);
            }// exporta a html segun el reporte
            if (reporte.equals(reportes[6])) {// entra al reporte 6
                HTML.generarTitulo(archivo, "REPORTE CONSUMOS HECHOS EN EL RESTAURANTE");
                HTML.generarReporte6(archivo, modeloIngresosHotelConsumo, formato.format(dateFechaInicio.getDate()), formato.format(dateFechaFinal.getDate()), totalTabla1.getText(), this.usuario);
// exporta a html segun el reporte
            }
            if (reporte.equals(reportes[7])) {// entra al reporte 7
                HTML.generarTitulo(archivo, "REPORTE DE CLIENTES");
                HTML.generarReporte7(archivo, modeloClientes, this.usuario);

            }
        } else {// si hubo un error dice que lo hubo
            JOptionPane.showMessageDialog(null, "No se creo el archivo");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboBoxReportes = new javax.swing.JComboBox<>();
        labelFechaInicio = new javax.swing.JLabel();
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        labelFechaFinal = new javax.swing.JLabel();
        dateFechaFinal = new com.toedter.calendar.JDateChooser();
        botonReporte = new javax.swing.JButton();
        labelClienteDPI = new javax.swing.JLabel();
        textoClienteDPI = new javax.swing.JTextField();
        labelIDHabitacion = new javax.swing.JLabel();
        textoIDHabitacion = new javax.swing.JTextField();
        labelFechaEspecifica = new javax.swing.JLabel();
        dateFechaEspecifica = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        botonExportar = new javax.swing.JButton();
        labelTotal1 = new javax.swing.JLabel();
        labelTotal2 = new javax.swing.JLabel();
        totalTabla2 = new javax.swing.JLabel();
        totalTabla1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("REPORTE");

        comboBoxReportes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "REPORTE_INGRESOS_HOTEL", "REPORTE_INGRESOS_DE_UN_CLIENTE", "REPORTE_INGRESOS_HABITACION", "REPORTE_HABITACION_MAS_POPULAR", "HABITACION_OCUPADA", "HABITACION_DISPONIBLE", "REPORTE_CONSUMOS_RESTAURANTE", "REPORTE_CLIENTES" }));
        comboBoxReportes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxReportesItemStateChanged(evt);
            }
        });

        labelFechaInicio.setText("FECHA INICIO:");

        labelFechaFinal.setText("FECHA FINAL");

        botonReporte.setText("REPORTE");
        botonReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReporteActionPerformed(evt);
            }
        });

        labelClienteDPI.setText("Cliente DPI:");

        labelIDHabitacion.setText("Id habitacion:");

        labelFechaEspecifica.setText("Fecha Especifica:");

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla1);

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla2);

        botonExportar.setText("EXPORTAR HTML");
        botonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExportarActionPerformed(evt);
            }
        });

        labelTotal1.setText("TOTAL:");

        labelTotal2.setText("TOTAL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelFechaInicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(labelFechaFinal)
                        .addGap(18, 18, 18)
                        .addComponent(dateFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonReporte)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelClienteDPI)
                                .addGap(49, 49, 49)
                                .addComponent(textoClienteDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelFechaEspecifica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateFechaEspecifica, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelIDHabitacion)
                                        .addGap(40, 40, 40)
                                        .addComponent(textoIDHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 249, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonExportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTotal2)
                .addGap(18, 18, 18)
                .addComponent(totalTabla2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelTotal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFechaInicio, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(dateFechaFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelFechaFinal))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelClienteDPI)
                        .addComponent(textoClienteDPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelIDHabitacion)
                            .addComponent(textoIDHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFechaEspecifica)
                            .addComponent(dateFechaEspecifica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(botonReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotal1)
                    .addComponent(totalTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonExportar)
                    .addComponent(labelTotal2)
                    .addComponent(totalTabla2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxReportesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxReportesItemStateChanged
        mostrarFiltros();
    }//GEN-LAST:event_comboBoxReportesItemStateChanged

    private void botonReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteActionPerformed
        irReporte();
    }//GEN-LAST:event_botonReporteActionPerformed

    private void botonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExportarActionPerformed
        exportarHTML();
    }//GEN-LAST:event_botonExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonExportar;
    private javax.swing.JButton botonReporte;
    private javax.swing.JComboBox<String> comboBoxReportes;
    private com.toedter.calendar.JDateChooser dateFechaEspecifica;
    private com.toedter.calendar.JDateChooser dateFechaFinal;
    private com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelClienteDPI;
    private javax.swing.JLabel labelFechaEspecifica;
    private javax.swing.JLabel labelFechaFinal;
    private javax.swing.JLabel labelFechaInicio;
    private javax.swing.JLabel labelIDHabitacion;
    private javax.swing.JLabel labelTotal1;
    private javax.swing.JLabel labelTotal2;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField textoClienteDPI;
    private javax.swing.JTextField textoIDHabitacion;
    private javax.swing.JLabel totalTabla1;
    private javax.swing.JLabel totalTabla2;
    // End of variables declaration//GEN-END:variables
}
