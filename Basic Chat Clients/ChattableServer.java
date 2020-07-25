package alwaysTrue;

import java.io.*;
import java.util.*;
import java.net.*;

public class ChattableServer {

	private int socketNum;
	private String userName;
	private Scanner keyboard = new Scanner(System.in);
	private ServerSocket serverSocket;
	private Socket socket;

	class readMessages extends Thread {

		readMessages() {
			keyboard.nextInt();
		}

		@Override
		public void run() {
			DataInputStream serverInput = null;
			try {
				serverInput = new DataInputStream(socket.getInputStream());
			} catch (IOException ex) {
				//ex.printStackTrace();
				System.exit(0);
			}

			while (socket.isConnected()) {
				try {
					System.out.println("Received : " + (String) serverInput.readUTF());
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			}
		}
	}

	class sendMessages extends Thread {

		@Override
		public void run() {
			DataOutputStream serverOutput = null;
			try {
				serverOutput = new DataOutputStream(socket.getOutputStream());
			} catch (IOException ex) {
				//ex.printStackTrace();
				System.exit(0);
			}

			while (socket.isConnected()) {
				String send = keyboard.nextLine();
				try {
					serverOutput.writeUTF(userName + ": " + send);
					serverOutput.flush();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
	}

	private void config() throws IOException {

		System.out.println("Enter the port (default is 5029): ");

		try {
			socketNum = Integer.parseInt(keyboard.nextLine());
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Invalid port. Port set to 5029"); // Blank line
			socketNum = 5029;
		}

		System.out.println("Enter your username: ");
		userName = keyboard.toString();

		serverSocket = new ServerSocket(socketNum);
		socket = serverSocket.accept();

		new sendMessages().start();
		new readMessages().start();

		while (socket.isConnected()) {
			// Do nothing
		}
		socket.close();
		serverSocket.close();
		keyboard.close();
	}

	public static void main(String[] args) throws IOException {
		ChattableServer server = new ChattableServer();
		server.config();
	}
}
