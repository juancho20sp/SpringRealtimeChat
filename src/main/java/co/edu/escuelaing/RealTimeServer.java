package co.edu.escuelaing;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealTimeServer {
    // Estas dos variables están seteadas en 'application.properties'
    @Value("${rt-server.host}")
    private String host;

    @Value("${rt-server.port}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration configuration = new Configuration();
        configuration.setHostname(host);
        configuration.setPort(port);

        return new SocketIOServer(configuration);
    }

    public static void main(String[] args) {
        SpringApplication.run(RealTimeServer.class, args);
    }
}
