package mx.uv;

public class Auto {
private String nombre;
private int precio;
private String categoria;
private int existencias;
private String imagen;

public Auto(String nombre, int precio,String categoria, int existencias, String imagen){
    this.nombre=nombre;
    this.precio=precio;
    this.existencias=existencias;
    this.categoria=categoria;
    this.imagen=imagen;
}

public Auto(){
    
}

//SETTERS
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public void setExistencias(int existencias) {
    this.existencias = existencias;
}
public void setPrecio(int precio) {
    this.precio = precio;
}
public void setCategoria(String categoria) {
    this.categoria = categoria;
}
public void setImagen(String imagen) {
    this.imagen = imagen;
}
//GETTERS
public String getNombre() {
    return nombre;
}
public int getPrecio() {
    return precio;
}
public String getCategoria() {
    return categoria;
}
public int getExistencias() {
    return existencias;
}
public String getImagen() {
    return imagen;
}
}
