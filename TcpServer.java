import java.io.*;
import java.net.*;

public class TcpServer {
	public static void main(String[] args) throws Exception{
		String stringFromClient;
		String stringToClient;
		
		ServerSocket welcomeSocket = new ServerSocket(10000);
		while(true) {
			Socket connectSocket = welcomeSocket.accept();
			
			System.out.println("Connect with Client");
			
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
			
			DataOutputStream outToClient = new DataOutputStream(connectSocket.getOutputStream());
			
			stringFromClient = inFromClient.readLine();
			
			System.out.println("String from Client : " + stringFromClient);
			
			stringToClient = "successfully connected\n";
			
			outToClient.writeBytes(stringToClient);
			
			System.out.println("String send to Client : " + stringToClient);
			
			if(!welcomeSocket.isClosed()) {
				connectSocket.close();
				System.out.println("Close of Server");
				break;
			}
		}
	}
}
