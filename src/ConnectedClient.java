import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectedClient {

    private Socket clientSocket;
    private DataInputStream in;

    public ConnectedClient(Socket clientSocket) throws IOException {

        this.clientSocket = clientSocket;
        try {
            this.in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public void readMessages(){

        String line = "";
        while (!line.equalsIgnoreCase("over")){
            try {
                line = in.readUTF();
            } catch (NullPointerException | IOException e) {
                System.out.println(e.toString());
            }
            System.out.println(line);
        }
    }

    public void close()  {
        try {
            clientSocket.close();
            in.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
