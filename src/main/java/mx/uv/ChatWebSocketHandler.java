package mx.uv;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocketHandler {
      private static final Set<Session> sessions = new HashSet<>();
      private String sender, msg;
      String sessionId ;
    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        String query = user.getUpgradeRequest().getRequestURI().getQuery();
        // Aquí puedes manejar la lógica cuando un nuevo usuario se conecta        
        sessions.add(user);        
        System.out.println("QUERY: "+query);
        if(query != null){
          sessionId= query.split("=")[1];
            System.out.println("SESSION ID : "+sessionId);
                spark.Session httpSession =Chat.getHttpSession(sessionId);
                Chat.userUsernameMap.put(user, sessionId);
                 Chat.broadcastMessage(sender = "Server", msg = (sessionId + " entro al chat"));
                 System.out.println("Nueva conexion WebSocket de usuario: " + sessionId);

        }else{
            user.disconnect();
        }
     //   System.out.println("Nuevo usuario conectado: " + user.getRemoteAddress().getAddress());
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        sessions.remove(user);
        System.out.println("Usuario desconectado: " + sessionId);
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {    
        System.out.println("Mensaje recibido de " + sessionId + ": " + message);
        if (!isValidSession(user)) {
            System.out.println("Sesión no válida, desconectando usuario: " +sessionId);
            user.close();
            return;
        }

        // MUESTRA EL MSJ A TODOS LOS USUARIOS DENTRO DE LA APP
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.getRemote().sendStringByFuture(message);
            }
        }
    }


    private boolean isValidSession(Session session) {
        return true; 
    }
}

