
package Entidades;
// atributos de  la clase
public class Habitacion {
    private int id;
    private String tipoHabitacion;
    private int nivel;
    private int precio;
// constructor de la calse
    public Habitacion(int id, String tipoHabitacion, int nivel, int precio) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.nivel = nivel;
        this.precio = precio;
    }// gettesrs y setters

    public int getId() {
        return id;
    }
// gettesrs y setters
    public void setId(int id) {
        this.id = id;
    }
// gettesrs y setters
    public String getTipoHabitacion() {
        return tipoHabitacion;
    }
// gettesrs y setters
    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
// gettesrs y setters
    public int getNivel() {
        return nivel;
    }
// gettesrs y setters
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
// gettesrs y setters
    public int getPrecio() {
        return precio;
    }
// gettesrs y setters
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
