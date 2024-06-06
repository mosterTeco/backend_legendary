package mx.uv;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.nimbus.State;

import java.sql.*;

public class DAO {
    private static Conexion c = new Conexion();

    // !METODO QUE RETORNA OBJETO CON LOS DATOS DEL USUARIO
    public static Usuario datosUsuario(String correoUsuario) {
        Connection conn = c.getConnection();
        Usuario usuario = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Usuarios WHERE Correo='" + correoUsuario + "'");
            while (rs.next()) {
                usuario = new Usuario(rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Correo"),
                        rs.getString("Pais"), rs.getString("Contrasena"));
            }
        } catch (Exception ex) {
            System.out.println("Erro al obtener datos del usuario: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return usuario;
    }

    // !METODO PARA ACTUALIZAR LOS DATOS DEL USUARIO
    public static String actualizarUsuario(String correo, String pais, String contrasena) {
        Connection conn = c.getConnection();
        int filas=0;
        String msj="";
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Usuarios set Pais='"+pais+"', Contrasena='"+contrasena+"' WHERE Correo='"+correo+"';");
            filas=ps.executeUpdate();
            if(filas>0){
                msj="GG";
            }else{
                msj="GGnt";
            }
        } catch (Exception ex) {
            System.out.println("Error al actualizar los datos del usuario: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

    // !METODO PARA ELIMINAR EL USUARIO
    public static Boolean eliminarUsuario(String correo) {
        Connection conn = c.getConnection();
        boolean eliminado = false;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Usuarios WHERE Correo='" + correo + "'");
            ps.executeUpdate();
            eliminado = true;
        } catch (Exception ex) {
            System.out.println("Error al eliminar el usuario: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return eliminado;
    }

    // !METODO PARA OBTENER AUTOS DE LA BD
    public static List<Auto> dameAutos() {
        Connection conn = c.getConnection();
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM autos WHERE Categoria='Auto'");
            while (rs.next()) {
                Auto auto = new Auto(rs.getString("Nombre"), rs.getInt("Precio"), rs.getString("Categoria"),
                        rs.getInt("Existencias"), rs.getString("imagen"));
                autos.add(auto);
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener autos: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return autos;
    }

    // !METODO PARA OBTENER MOTOCICLETAS DE LA BD
    public static List<Auto> dameMotocicletas() {
        Connection conn = c.getConnection();
        ArrayList<Auto> motocicletas = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM autos WHERE Categoria='Motocicleta'");
            while (rs.next()) {
                Auto auto = new Auto(rs.getString("Nombre"), rs.getInt("Precio"), rs.getString("Categoria"),
                        rs.getInt("Existencias"), rs.getString("imagen"));
                motocicletas.add(auto);
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener motocicletas: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return motocicletas;
    }

    public static List<Compra> dameCompras(String nombreUsuario){
        Connection conn = c.getConnection();
        ArrayList<Compra> compras = new ArrayList<>();
        try{
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM compras WHERE NombreUsuario = '" + nombreUsuario + "';");
             while (rs.next()){
                Compra compra = new Compra(rs.getString("NombreUsuario"), rs.getString("NombreAuto"), 
                rs.getString("Color"), rs.getString("Precio"));
                compras.add(compra);
             }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return compras;
    }

    // !METODO PARA AGREGAR NUEVOS USUARIOS A LA BASE DE DATOS
    public static String crearUsuario(Usuario u) {
        PreparedStatement stm = null;
        Connection conn = null;
        String msj = "";

        conn = c.getConnection();
        try {
            String sql = "INSERT INTO Usuarios (Nombre, Apellidos, Correo, Pais, Contrasena) values (?,?,?,?,?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, u.getNombre());
            stm.setString(2, u.getApellidos());
            stm.setString(3, u.getCorreo());
            stm.setString(4, u.getPais());
            stm.setString(5, u.getContrasena());
            if (stm.executeUpdate() > 0)
                msj = "usuario agregado";
            else
                msj = "usuario no agregado";

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

    // !METODO PARA VALIDAD QUE EL USUARIO ESTÁ REGISTRADO
    public static boolean usuarioRegistrado(String email, String contrasena) {
        boolean respuesta = false;
        Connection conn = c.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Correo, Contrasena FROM Usuarios WHERE Correo='" + email
                    + "' AND Contrasena='" + contrasena + "';");
            if (rs.next()) {
                respuesta = true;
            }
        } catch (Exception ex) {
            System.out.println("Error al iniciar sesion: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return respuesta;
    }

    public static String imgVisualizacionCoche(String nombreCoche){
        Connection conn = c.getConnection();
        String imgVisualizacion = "";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from autoCompra where NombreAuto = '" + nombreCoche + "';");
            if(rs.next()){
                imgVisualizacion = rs.getString("imgVisualizacion");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return imgVisualizacion;
    }

    public static String imgEstadisticasCoche(String nombreCoche){
        Connection conn = c.getConnection();
        String imgEstadisticas = "";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from autoCompra where NombreAuto = '" + nombreCoche + "';");
            if(rs.next()){
                imgEstadisticas = rs.getString("imgEstadisticas");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return imgEstadisticas;
    }

    public static String descrpcionCoche(String nombreCoche){
        Connection conn = c.getConnection();
        String descripcion = "";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from autoCompra where NombreAuto = '" + nombreCoche + "';");
            if(rs.next()){
                descripcion = rs.getString("Descripcion");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return descripcion;
    }

    public static String realizarCompra(String nombreUsuario, String nombreCoche, String color, String precio){
        Connection conn = c.getConnection();
        PreparedStatement stm = null;
        String msj = "";
        try{
            String sql = "INSERT INTO compras values (?,?,?,?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, nombreUsuario);
            stm.setString(2, nombreCoche);
            stm.setString(3, color);
            stm.setString(4, precio);
            if (stm.executeUpdate() > 0)
                msj = "Compra realizada";
            else
                msj = "Proceso fallido";
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

}
