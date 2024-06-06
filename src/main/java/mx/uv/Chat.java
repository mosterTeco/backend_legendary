package mx.uv;
import static spark.Spark.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import static j2html.TagCreator.*;

public class Chat {

    static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    static Map<String, spark.Session> sessionMap = new ConcurrentHashMap<>();
    static int nextUserNumber = 1; // Contador

    public static void main(String[] args) {
        staticFiles.location("/public"); // index.html is served at localhost:4567 (default port)
        staticFiles.expireTime(600);
        webSocket("/chat", ChatWebSocketHandler.class);

        // Habilitar sesiones
        before((request, response) -> {
            if (request.session().isNew()) {
                request.session().attribute("username", "Usuario" + nextUserNumber++);
            }
        });

        // Guardar la sesión HTTP cuando se establece una conexión WebSocket
        get("/session", (req, res) -> {
            String sessionId = req.session().id();
            sessionMap.put(sessionId, req.session());
            return sessionId;
        });

        init();
    }

    // Envía un mensaje de un usuario a todos los usuarios, junto con una lista de nombres de usuario actuales
    public static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            /*try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("userMessage", createHtmlMessageFromSender(sender, message))
                    .put("userlist", userUsernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        });
    }

    // Construye un elemento HTML con un nombre, mensaje y una marca de tiempo
    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
            b(sender + " dice:"),
            span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
            p(message)
        ).render();
    }

    public static String getUsername(spark.Session session) {
        return session.attribute("username");
    }

    public static spark.Session getHttpSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
