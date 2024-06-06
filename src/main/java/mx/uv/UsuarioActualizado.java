package mx.uv;

public class UsuarioActualizado {
    private String correo;
    private String pais;
    private String contrasena;

    public UsuarioActualizado(String correo, String pais, String contrasena){
        this.correo=correo;
        this.pais=pais;
        this.contrasena=contrasena;
    }
    //SETTERS
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    //GETTERS
    public String getCorreo() {
        return correo;
    }
    public String getPais() {
        return pais;
    }public String getContrasena() {
        return contrasena;
    }
}
