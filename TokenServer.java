import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TokenServer implements Runnable {
	
	//declare variables
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	static Socket clientSocket;
	
	//constructor
	public TokenServer(Socket clientS){
		setSocket(clientS);
	}
	
	//set Socket
	private void setSocket(Socket socket) {
		this.socket=socket;
		
	}

	//main method
	public static void main(String[] args) throws IOException{
		///set port for this server
		ServerSocket server = new ServerSocket(1234);
		
		System.out.println("Server running.. waiting for a client to connect.....");
		//while true insure this loop will always run.
		while (true){
			//create a new socket variable and assign the new client socket to it
			clientSocket = server.accept();
			System.out.println("Client connected");
			//create a new thread
			new Thread(new TokenServer(clientSocket)).start();
		}
	}
	
	

	@Override
	public void run() {
		try{
			try{
				//create the in and out from the client input and output streams
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream());
				//call the doService method
				doService();
			}finally{
				socket.close();
			}
		}catch (IOException e){
			System.err.println(e);
		}
	}

	private void doService() throws IOException{
		
		while(true){
			//if the server hasn't sent any requests exit
			if(!in.hasNext()){
				return;
			}
			
			//otherwise set the String request equal to the request sent 
			String request = in.nextLine();
			System.out.println("Request recived: "+ request);
			
			//call the request method and pass it the string request
			Request(request);
			}
    }

	
    public void Request(String request) {
    	//create TokenList variable use the getInstance method to insure all threads are accessing the same instance of the TokenList class
    	TokenList tokens= TokenList.getInstance();
		
        String responce;
        //if the string starts with SUMBIT
		if (request.startsWith("SUBMIT")) {
			//get the token form the second half of the string
			String newToken = request.split(" ")[1];
			//check if the token is in the treeSet already
            if (tokens.getTokentree().contains(newToken)) {
            	//if it is print message
                responce ="Error: This token already exists. Please try again";
            }
            else {
            	//if its not in the treeset already check how many tokens are already in the tree set
            	//if less than 10 add the new token to the treeSet and return message.
            		if(tokens.getTokentree().size()<10){
            			tokens.getTokentree().add(newToken);
            			responce = ("OK. Token " +newToken+" has been added");
            		}else{
            			//if the treeSet has 10 or more tokens already return error message
            			responce = "Error Token list os full. If you wish to add this token you must remove a different token first";
            		}	
            }
            //send the response to the client
            out.print(responce +"\n"); 
        }
		//if the string starts with REMOVE
        else if (request.startsWith("REMOVE")) {
        	//get the token form the second half of the string
        	String newToken = request.split(" ")[1];
    		//check if the treeset contains the token
            if (tokens.getTokentree().contains(newToken)) {
            	
            	//if it does delete the token and set response equal to appropriate message
                tokens.getTokentree().remove(newToken);
                responce=("OK. \nToken " +newToken +" removed");
            }
            else {
            	//otherwise set response equal to error message
                responce="Error!! This token can not be found and so can not be removed.";
            }
            //send response to the client
            out.print(responce +"\n");

            //if the request is QUIT end connection
        }else if(request.equals("QUIT")){
			  System.err.println("Program ended");
	            out.println("Program ended");
	            try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} 
        else{
        	//if the request is one that hasn't been delt with above print Unkonwn request error
        	System.err.println("Unknown request");
        }
              
		//finaly print how many tokens are currently contained in the treeSet
        System.out.println("Currently there are "+tokens.getTokentree().size()+" stored.");
        out.flush();
    }
		
	}
	
	
	
