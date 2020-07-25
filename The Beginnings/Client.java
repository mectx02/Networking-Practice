import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5029);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF("Hello, Server!");
            output.flush();
            output.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

}
