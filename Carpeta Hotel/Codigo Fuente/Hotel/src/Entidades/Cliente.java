
package Entidades;

public class Cliente {
    // atrubutos de la clase
    private String dpi;
    private String nombre;
    private String apellido;
    private String nit;
// constructor      
    public Cliente(String dpi, String nombre, String apellido, String nit) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nit = nit;
    }
    
    // gettesrs y setters

    public String getDpi() {
        return dpi;
    }
// gettesrs y setters
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }
// gettesrs y setters
    public String getNombre() {
        return nombre;
    }
// gettesrs y setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
// gettesrs y setters
    public String getApellido() {
        return apellido;
    }// gettesrs y setters

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
// gettesrs y setters
    public String getNit() {
        return nit;
    }
// gettesrs y setters
    public void setNit(String nit) {
        this.nit = nit;
    }
    
    
}
