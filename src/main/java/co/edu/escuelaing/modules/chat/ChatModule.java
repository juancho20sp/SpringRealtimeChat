package co.edu.escuelaing.modules.chat;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatModule {
    private static final Logger log = LoggerFactory.getLogger(ChatModule.class);

    private final SocketIONamespace namespace;

    public ChatModule(SocketIOServer server) {
        // URL a la cual el front va a dirigir las peticiones y la escucha / emisión de eventos
        this.namespace = server.addNamespace("/chat");

        // Eventos que va a manejar este namespace ('/chat')
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());

        // Evento | Clase que tiene la forma del objeto recibido | Qué hacer cuando se emita el evento
        this.namespace.addEventListener("chat", ChatMessage.class, onChatReceived());
    }


    // EVENTOS
    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();

            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId(), handshakeData.getUrl());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }

    private DataListener<ChatMessage> onChatReceived() {
        return (client, data, ackSender) -> {
            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
            // AQUÍ SE DEFINE EL NOMBRE DEL EVENTO EMITIDO
            this.namespace.getBroadcastOperations().sendEvent("chat", data);
        };
    }
}
