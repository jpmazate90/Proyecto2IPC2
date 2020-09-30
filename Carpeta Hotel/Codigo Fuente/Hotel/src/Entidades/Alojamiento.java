
package Entidades;

public class Alojamiento {
    // atributos de la clase
    private int id;
    private int idReservacion;
    private boolean checkOut;
// constructor
    public Alojamiento(int id, int idReservacion, boolean checkOut) {
        this.id = id;
        this.idReservacion = idReservacion;
        this.checkOut = checkOut;
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
    public int getIdReservacion() {
        return idReservacion;
    }
// gettesrs y setters
    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }
// gettesrs y setters
    public boolean isCheckOut() {
        return checkOut;
    }
// gettesrs y setters
    public void setCheckOut(boolean checkOut) {
        this.checkOut = checkOut;
    }
    
    
    
}
