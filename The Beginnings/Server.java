import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(5029);
            Socket socket = serverSocket.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            String str = (String)input.readUTF();
            System.out.println("Message: " + str);
            serverSocket.close();

        } catch (Exception e) {

            e.printStackTrace();
            System.exit(-1);

        }

    }

}
