package alwaysTrue;

import java.io.*;
import java.util.*;
import java.net.*;

public class ChattableServer {

    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);

        ServerSocket serverSocket = new ServerSocket(5029);
        Socket socket = serverSocket.accept();

        DataInputStream serverInput = new DataInputStream(socket.getInputStream());
        DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());

        while (socket.isConnected()) {

            String send = keyboard.nextLine();
            serverOutput.writeUTF(send);
            serverOutput.flush();

            System.out.println("\nWaiting for response...");

            System.out.println("Received : " + (String)serverInput.readUTF());

        }

        socket.close();
        serverSocket.close();
        keyboard.close();

    }

}
