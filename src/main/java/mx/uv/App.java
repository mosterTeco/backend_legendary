package mx.uv;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App {
    static Gson gson = new Gson();
    static HashMap<String, Usuario> usuarios = new HashMap<>();

    public static void main(String[] args) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        post("/registro", (request, response) -> {
            response.type("application/json");
            String payload = request.body();
            Usuario usuario = gson.fromJson(payload, Usuario.class);
            System.out.println("payload " + payload);
            String respuesta = DAO.crearUsuario(usuario);

            return respuesta;
        });

        post("/realizarCompra", (request, response) -> {
            response.type("application/json");
            String param = request.body();
            JsonParser parser  = new JsonParser();
            JsonElement arbol = parser.parse(param);
            //Poner lo que se pasará
            String nombreUsuario = arbol.getAsJsonObject().get("nombreUsuario").getAsString();
            String nombreCoche = arbol.getAsJsonObject().get("nombreCoche").getAsString();
            String color = arbol.getAsJsonObject().get("color").getAsString();
            String precio = arbol.getAsJsonObject().get("precio").getAsString();
            String respuesta = DAO.realizarCompra(nombreUsuario, nombreCoche, color, precio);

            return respuesta;
        });

        post("/validacion", (request, response) -> {
            response.type("application/json");
            String payload = request.body();
            String mensaje = "";
            Usuario usuario = gson.fromJson(payload, Usuario.class);
            System.out.println("usuario " + usuario.getCorreo());
            boolean respuesta = DAO.usuarioRegistrado(usuario.getCorreo(), usuario.getContrasena());

            // JsonObject respuesta2 = new JsonObject();
            if (respuesta == true) {
                // respuesta2.addProperty("msj", "Información validada");
                System.out.println("Usuario correcto");
                mensaje = "Usuario correcto";
            } else {
                System.out.println("Usuario incorrecto");
                // respuesta2.addProperty("msj", "Información invalida");
                mensaje = "Usuario incorrecto";
            }
            return mensaje;
            // return null;
        });

        get("/Autos", (request, response) -> {
            response.type("application/json");
            // return gson.toJson(usuarios.values());
            return gson.toJson(DAO.dameAutos());
        });

        get("/Motocicletas", (request, response) -> {
            response.type("application/json");
            // return gson.toJson(usuarios.values());
            return gson.toJson(DAO.dameMotocicletas());
        });

        get("/compras", (request, response) -> {
            String nombre = request.queryParams("nombreUsuario");
            response.type("application/json");
            return gson.toJson(DAO.dameCompras(nombre));
        });

        get("/obtenerImagenEstadisticas", (request, response) -> {
            String nombreCoche = request.queryParams("nombreCoche");
            response.type("application/json");
            String img = DAO.imgEstadisticasCoche(nombreCoche);
            return img;
        });
        
        get("/obtenerImagenVisualizacion", (request, response) -> {
            String nombreCoche = request.queryParams("nombreCoche");
            response.type("application/json");
            String img = DAO.imgVisualizacionCoche(nombreCoche);
            return img;
        });

        get("/obtenerDescripcionCoche", (request, response) -> {
            String nombreCoche = request.queryParams("nombreCoche");
            response.type("application/json");
            String img = DAO.descrpcionCoche(nombreCoche);
            return img;
        });

        get("/datosUsuario", (request, response) -> {
            String correo = request.queryParams("correo");
            // System.out.println("Request correo; "+correo);
            response.type("application/json");
            return gson.toJson(DAO.datosUsuario(correo));
        });

        get("/actualizarPerfil", (request, response) -> {
            response.type("application/json");
            String correo = request.queryParams("correo");
            String pais= request.queryParams("pais");
            String contrasena= request.queryParams("contrasena");

            String respuesta=DAO.actualizarUsuario(correo, pais, contrasena);
            JsonObject mensaje = new JsonObject();
            mensaje.addProperty("filas", respuesta);
            return mensaje;
        });

        get("/eliminarPerfil", (request, response) -> {
            response.type("application/json");
            String correo = request.queryParams("correo");
            System.out.println("Correo de eliminar: " + correo);
            boolean respuesta = DAO.eliminarUsuario(correo);
            JsonObject mensaje = new JsonObject();
            mensaje.addProperty("respuesta", respuesta);
            return mensaje;
        });

    }
}
