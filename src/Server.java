import org.w3c.dom.ls.LSOutput;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //Initialize socket and input
    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;

    //Constructor with port
    public Server(int port){

        //Starts server and waits for a connection
        try {
            ss = new ServerSocket(port);
            System.out.println("server started");

            System.out.println("Waiting for a client...");

            s = ss.accept();
            System.out.println("Client accepted");

            //Takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(s.getInputStream()));

            String m = "";

            //Reads message from client until "Over" is sent
            while(!m.equalsIgnoreCase("over")){
                try{
                    m = in.readUTF();
                    System.out.println(m);
                }catch(IOException i){
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            //close connection
            s.close();
            in.close();

        }catch (IOException i){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new Server(8080);
    }
}
