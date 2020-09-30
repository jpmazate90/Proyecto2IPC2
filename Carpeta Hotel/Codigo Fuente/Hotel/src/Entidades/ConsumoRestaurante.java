
package Entidades;

import java.util.Date;
// atributos de la clase
public class ConsumoRestaurante {
    private int idConsumo;
    private int idAlojamiento;
    private String nombreComida;
    private int precioComida;
    private int cantidad;
    private Date fechaConsumo;
// contructor
    public ConsumoRestaurante(int idConsumo, int idAlojamiento, String nombreComida, int precioComida, int cantidad, Date fechaConsumo) {
        this.idConsumo = idConsumo;
        this.idAlojamiento = idAlojamiento;
        this.nombreComida = nombreComida;
        this.precioComida = precioComida;
        this.cantidad = cantidad;
        this.fechaConsumo = fechaConsumo;
    }
// sobrecarga del constructor
    public ConsumoRestaurante(int idAlojamiento, String nombreComida, int precioComida, int cantidad, Date fechaConsumo) {
        this.idAlojamiento = idAlojamiento;
        this.nombreComida = nombreComida;
        this.precioComida = precioComida;
        this.cantidad = cantidad;
        this.fechaConsumo = fechaConsumo;
    }
    
// gettesrs y setters
    public int getIdConsumo() {
        return idConsumo;
    }
// gettesrs y setters
    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }
// gettesrs y setters
    public int getIdAlojamiento() {
        return idAlojamiento;
    }
// gettesrs y setters
    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }
// gettesrs y setters
    public String getNombreComida() {
        return nombreComida;
    }
// gettesrs y setters
    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }
// gettesrs y setters
    public int getPrecioComida() {
        return precioComida;
    }
// gettesrs y setters
    public void setPrecioComida(int precioComida) {
        this.precioComida = precioComida;
    }
// gettesrs y setters
    public int getCantidad() {
        return cantidad;
    }
// gettesrs y setters
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
// gettesrs y setters
    public Date getFechaConsumo() {
        return fechaConsumo;
    }
// gettesrs y setters
    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }
    
    
}
