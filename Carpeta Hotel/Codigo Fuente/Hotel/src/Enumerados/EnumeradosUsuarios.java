package Enumerados;

public enum EnumeradosUsuarios {
// solo sonenumerados que serviran como constantes
    GERENTE("GERENTE"), RECEPCIONISTA("RECEPCIONISTA"), EMPLEADO_RESTAURANTE("EMPLEADO RESTAURANTE");

    private EnumeradosUsuarios(String nombre) {
        this.usuario = nombre;
    }
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

}
