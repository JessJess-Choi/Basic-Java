
import java.io.*;
import java.net.*;

public class TcpClient {
	public static void main(String[] args) throws Exception{
		String stringToServer;
		String stringFromServer;
		
		Socket clientSocket = new Socket("192.168.0.34",10000);
		System.out.println("Connect with Server\nInput Message:");
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		stringToServer = inFromUser.readLine();
		System.out.println("Send to Server : " + stringToServer);
		
		outToServer.writeBytes(stringToServer + '\n');
		
		stringFromServer = inFromServer.readLine();
		
		System.out.println("Message from Server : " + stringFromServer);
		clientSocket.close();
		System.out.println("Client is closed");
	}
}
