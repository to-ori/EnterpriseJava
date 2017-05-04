package Part1and2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class ConversionServerMultithreaded {

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(8888);
		class ConversionService implements Runnable {
			private Socket s;
			private Scanner in;
			private PrintWriter out;

			ConversionService(Socket s) {
				this.s = s;
			}

			public void run() {
				try {
					try {
						in = new Scanner(s.getInputStream());
						out = new PrintWriter(s.getOutputStream());
						System.out.println("Accessing doService");
						doService(); // the actual service of the server
					} finally {
						s.close();
					}
				} catch (IOException e) {
					System.err.println(e);
				}
			}

			public void doService() throws IOException {
				while (true) {
					if (!in.hasNext())
						return;
					String request = in.next();
					System.out.println(request);
					if (request.equals("QUIT")) // End connection to this client
						out.println("Good bye");
						
					else
						handleRequest(request);
				}
				
			}

			public void handleRequest(String request) {
				String amountStr = in.next();
				double amount = Double.valueOf(amountStr);
				System.out.println("Received from client: " + amount);
				if (request.equals("CONVERT_TO_POUNDS")) {
					out.println(amount * 2.2d); // server response
					System.out.println("Sending conversion result to client");
				} else if (request.equals("CONVERT_TO_KGS")) {
					out.println(amount / 2.2d); // server response
					System.out.println("Sending conversion result to client");
				} else
					System.err.println("Unknown request!");
				out.flush();
			}
		}
		while (true) {

			Socket s = server.accept();
			System.out.println("Client connected");

			ConversionService service = new ConversionService(s);
			Thread t = new Thread(service);
			t.start();
		}

	}

}