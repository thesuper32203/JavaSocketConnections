import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    //initialize socket and input/output streams
    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    // Constructor to put IP address and port
    public Client(String ipAddress, int port){

        //Establishes conneciton
        try {
            s = new Socket(ipAddress, port);
            System.out.println("Connected");

            //Takes input from terminal
            in = new DataInputStream(System.in);

            //Sends output to the socket
            out = new DataOutputStream(s.getOutputStream());

        } catch(IOException i){
            System.out.println(i.toString());
            return;
        }

        //String to read message from input
        String m = "";

        //keep reading until "Over" is input
        while(!m.equalsIgnoreCase("over")){
            try{
                m = in.readLine();
                out.writeUTF(m);

            }catch(IOException i){
                System.out.println(i);
            }
        }

        //Close the connection
        try{
            in.close();
            out.close();
            s.close();
        }catch(IOException i){
            System.out.println(i);
        }
    }


    public static void main(String[] args) {
        new Client("127.7.0.0",8080);
    }


}
