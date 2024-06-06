package mx.uv;

public class Usuario {
private String nombre;
private String apellidos;
private String correo;
private String pais;
private String contrasena;

public Usuario(String nombre, String apellidos, String correo, String pais, String contrasena){
this.nombre=nombre;
this.apellidos=apellidos;
this.correo=correo;
this.pais=pais;
this.contrasena=contrasena;
}

//SETTERS
public void setNombre(String nombre){
    this.nombre=nombre;
}
public void setApellidos(String apellidos){
    this.apellidos=apellidos;
}
public void setCorreo(String correo){
    this.correo=correo;
}
public void setPais(String pais){
    this.pais=pais;
}
public void setContrasena(String contrasena){
    this.contrasena=contrasena;
}

//GETTERS
public String getNombre(){
    return nombre;
}
public String getApellidos(){
    return apellidos;
}
public String getCorreo(){
    return correo;
}
public String getPais(){
    return pais;
}
public String getContrasena(){
    return contrasena;
}

}
