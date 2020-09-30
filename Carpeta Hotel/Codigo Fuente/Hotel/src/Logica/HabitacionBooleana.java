
package Logica;
// atributos de la clase
public class HabitacionBooleana {
    private int id;
    private int idReservacion;
    private boolean estaOcupada;
// constructor de la clase
    public HabitacionBooleana(int id, int idReservacion) {
        this.id = id;
        this.idReservacion=idReservacion;
        this.estaOcupada=false;
    }
// sobre carga
    public HabitacionBooleana(int id) {
        this.id = id;
        this.estaOcupada=false;
    }
    
    
// getters y setters
    public int getId() {
        return id;
    }
// getters y setters
    public void setId(int id) {
        this.id = id;
    }
// getters y setters
    public boolean isEstaOcupada() {
        return estaOcupada;
    }// getters y setters

    public void setEstaOcupada(boolean estaOcupada) {
        this.estaOcupada = estaOcupada;
    }
// getters y setters
    public int getIdReservacion() {
        return idReservacion;
    }
// getters y setters
    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }
    
    
    
}
