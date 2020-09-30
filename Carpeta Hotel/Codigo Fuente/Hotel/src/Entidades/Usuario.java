
package Entidades;

public class Usuario {
    
    // constructor de la clase
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String tipoUsuario;
// constructor de la clase
    public Usuario(String usuario, String contraseña, String nombre, String apellido, String tipoUsuario) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoUsuario = tipoUsuario;
    }
// gettesrs y setters
    public String getUsuario() {
        return usuario;
    }// gettesrs y setters

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }// gettesrs y setters

    public String getContraseña() {
        return contraseña;
    }// gettesrs y setters

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
    }
// gettesrs y setters
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
// gettesrs y setters
    public String getTipoUsuario() {
        return tipoUsuario;
    }
// gettesrs y setters
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
 
    
}