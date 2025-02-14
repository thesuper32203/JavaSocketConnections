import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectedClient {

    private Socket clientSocket;
    private DataInputStream in;

    public ConnectedClient(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));


    }
}
