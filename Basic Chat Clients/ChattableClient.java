package alwaysTrue;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;

public class ChattableClient {

    public static void main(String[] args) throws IOException {

        //fancy way to set the ip address. Just shows an input dialog asking for an IP address to connect to.
        String ipAddress = JOptionPane.showInputDialog(null, "Please enter an IP address that you want to connect to.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tLeave blank to default to localhost."); //Formatting is wonderful kids...
        Socket socket;

        if (ipAddress.matches("[0-9]{3}.[0-9]{3}.[0-9]{3}.[0-9]{3}"))
            socket = new Socket(ipAddress, 5029);
        else
            socket = new Socket("localhost", 5029);

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
