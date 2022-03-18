package co.edu.escuelaing.modules.chat;

public class ChatMessage {
    private String username;
    private String message;

    // TODO -> ¿Y la fecha?

    public ChatMessage() {

    }

    public ChatMessage(String username, String message) {
        super();
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
