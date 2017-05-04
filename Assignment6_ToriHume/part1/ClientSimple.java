package Part1and2;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientSimple extends JFrame implements ActionListener {
	JTextField weight;
	JButton lbkg;
	JButton kglb;
	JButton quit;
	private String GUIRequest;

	JLabel statusLabel;

	public ClientSimple() {
		this.setTitle("Weight Converter");
		this.setSize(350, 150); // initial window size
		this.setDefaultCloseOperation( // triggered when user clicks on the close icon:
				JFrame.EXIT_ON_CLOSE); //end program
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		Container content = getContentPane();
		content.setBackground(Color.CYAN);
		content.setLayout(new FlowLayout()); // how components are arranged
		content.add(new JLabel("Enter Weight"));//text displayed
		weight = new JTextField("\t\t");//text box to accept numbers
		lbkg = new JButton("lb->kg");	//buttons
		kglb = new JButton("kg->lb");
		
		content.add(weight);
		content.add(kglb);
		content.add(lbkg);
		
		content.add(statusLabel);

		lbkg.addActionListener(this);
		kglb.addActionListener(this);
		

		setVisible(true); // makes the frame visible
	}

	public static void main(String[] args) throws IOException {
		
			new ClientSimple();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(lbkg)) {
			try {
				this.setGUIRequest("CONVERT_TO_KGS");
				processInformation();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(kglb)) {
			try {
				this.setGUIRequest("CONVERT_TO_POUNDS");
				processInformation();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		
	}

	public void processInformation() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 8888);
		
		InputStream instream = s.getInputStream();
		
		OutputStream outstream = s.getOutputStream();
		
		Scanner in = new Scanner(instream);
		
		PrintWriter out = new PrintWriter(outstream);
		
		String request = "";
			
			request = getGUIRequest() + " " + weight.getText();
			System.out.println("sending: " + request);
			
			out.print(request  + "\n");
			
			out.flush();
			System.out.println("Waiting for response");
			String response = in.nextLine();
			
			System.out.println("Receiving: " + response);
			statusLabel.setText(response);
		
		instream.close();
		
		outstream.close();
		
		in.close();
		
		s.close();
	}

	public String getGUIRequest() {
		return GUIRequest;
	}

	public void setGUIRequest(String gUIRequest) {
		GUIRequest = gUIRequest;
	}
}

