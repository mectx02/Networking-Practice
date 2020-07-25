package alwaysTrue;

import java.io.*;
import java.util.*;
import java.net.*;

public class ChattableClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 5029);
        Scanner keyboard = new Scanner(System.in);

        DataInputStream clientInput = new DataInputStream(socket.getInputStream());
        DataOutputStream clientOutput = new DataOutputStream(socket.getOutputStream());

        while (socket.isConnected()) {

            System.out.println("Received : " + (String) clientInput.readUTF());

            String send = keyboard.nextLine();
            clientOutput.writeUTF(send);
            clientOutput.flush();

            System.out.println("\nWaiting for response...");

        }

        socket.close();
        keyboard.close();

    }

}
