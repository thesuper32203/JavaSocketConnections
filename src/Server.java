import org.w3c.dom.ls.LSOutput;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //Initialize socket and input
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    //Constructor with port
    public Server(int port) throws IOException{
        //Starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("server started");

            System.out.println("Waiting for a client...");
            while (true) iniConnections();
        }
            catch (IOException i){
                System.out.println(i);
            }
        }



    public void iniConnections() throws IOException{

        Socket clientSocket = server.accept();

        if(clientSocket.isConnected())
            new Thread(()->{
                System.out.println("New client");
                ConnectedClient client = null;
                try {
                    client = new ConnectedClient(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                client.readMessages();
                client.close();
                System.out.println("Client disconnected");
            }).start();

    }
    public static void main(String[] args) throws IOException {
        new Server(8080);
    }
    }




