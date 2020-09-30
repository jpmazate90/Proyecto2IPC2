
package Entidades;
// atributos de la clase
public class Menu {
    private String nombreComida;
    private String tipoComida;
    private int precio;
// constructor de la clase
    public Menu(String nombreComida, String tipoComida, int precio) {
        this.nombreComida = nombreComida;
        this.tipoComida = tipoComida;
        this.precio = precio;
    }

    
    // gettesrs y setters
    public String getNombreComida() {
        return nombreComida;
    }// gettesrs y setters

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }
// gettesrs y setters
    public String getTipoComida() {
        return tipoComida;
    }
// gettesrs y setters
    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }// gettesrs y setters

    public int getPrecio() {
        return precio;
    }// gettesrs y setters

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
