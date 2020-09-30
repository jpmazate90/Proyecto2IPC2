
package Entidades;

import java.util.Date;

public class Reservacion {
    // atributos de la clase
    private int id;
    private String dpiCliente;
    private int idHabitacion;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int precio;
    private boolean checkIn;
// constructor de la clase
    public Reservacion(int id, String dpiCliente, int idHabitacion, Date fechaEntrada, Date fechaSalida, int precio, boolean checkIn) {
        this.id = id;
        this.dpiCliente = dpiCliente;
        this.idHabitacion = idHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precio = precio;
        this.checkIn = checkIn;
    }
// gettesrs y setters
    public int getId() {
        return id;
    }
// gettesrs y setters
    public void setId(int id) {
        this.id = id;
    }
// gettesrs y setters
    public String getDpiCliente() {
        return dpiCliente;
    }
// gettesrs y setters
    public void setDpiCliente(String dpiCliente) {
        this.dpiCliente = dpiCliente;
    }// gettesrs y setters

    public int getIdHabitacion() {
        return idHabitacion;
    }
// gettesrs y setters
    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
// gettesrs y setters
    public Date getFechaEntrada() {
        return fechaEntrada;
    }
// gettesrs y setters
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
// gettesrs y setters
    public Date getFechaSalida() {
        return fechaSalida;
    }// gettesrs y setters

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
// gettesrs y setters
    public int getPrecio() {
        return precio;
    }
// gettesrs y setters
    public void setPrecio(int precio) {
        this.precio = precio;
    }
// gettesrs y setters
    public boolean isCheckIn() {
        return checkIn;
    }// gettesrs y setters

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }
    
    
    
}
