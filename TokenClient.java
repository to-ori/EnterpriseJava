import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TokenClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner console = new Scanner(System.in);
		//take in the port number from the user
		System.out.println("Please enter the  port number to connect to:  ");
		int port = console.nextInt();
		
		
		try{
			//make connection to server
		Socket s = new Socket("localhost", port);
		console.nextLine();
		
		//create input and output streams
		InputStream instream = s.getInputStream();
		OutputStream outstream = s.getOutputStream();
		Scanner in = new Scanner(instream);
		PrintWriter out = new PrintWriter(outstream);
		
		//declare, create variable
		String request = "";
		
		while (!request.equals("QUIT")) {
			
			System.out.println(
					"Input 'SUBMIT' followed by a space and the token to submit a token, 'REMOVE' followed by a space and the token to remove a token and 'QUIT' to close the connection to the server\n ");
			request = console.nextLine();
			//print message sent to server and send it
			System.out.println("Sent: " + request);
			out.print(request + "\n");
			out.flush();

			//print message received from server
			System.out.println("Waiting for response.");
			String response = in.nextLine();
			System.out.println("Recieving: " + response);
		}
		}catch(Exception e){
			System.out.println("Unable to connect to port "+port);
		}
		

	}

}
