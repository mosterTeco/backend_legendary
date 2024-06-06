package mx.uv;

public class Compra {
    private String nombreUsuario;
    private String nombreAuto;
    private String color;
    private String precio;

    

    public Compra(String nombreUsuario, String nombreAuto, String color, String precio) {
        this.nombreUsuario = nombreUsuario;
        this.nombreAuto = nombreAuto;
        this.color = color;
        this.precio = precio;
    }

    public Compra() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getNombreAuto() {
        return nombreAuto;
    }
    public void setNombreAuto(String nombreAuto) {
        this.nombreAuto = nombreAuto;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    
}
