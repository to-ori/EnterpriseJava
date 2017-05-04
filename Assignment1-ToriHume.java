
/*Student: Tori Hume
 * ID: 11486248
 * Assignment 1
 * CT545
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/test1")
public class WelcomeServlet  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// write the data of the response
		out.println("<html>" + "<head><title>H.B. Welcome page</title></head>");

		out.println("<body  bgcolor=\"#ccccff\">"  // web form generation
				+ "<h2>Welcome to the Hume Bank Home Page!</h2>"
				+ "<h3>New Clients: </h3> If you would like to register with us please fill in all areas below."
				+"<h3> Registered clients:</h3>"
				+"<br>To view your balance just log in with your name and date of birth."
				+"<br>To change your address just enter your name, date of birth and the new address you would like to register."
				+"<br>To make changes to you registered name and date of birth you must call into your local branch.<br> <br>"
				+"<h3>Name</h3>"
				+ "<form method=\"get\">"
				+ "<input type=\"text\" name=\"username\" size=\"25\">"
				+"<h3>Date of Birth</h3>"
				+"<h4>Please enter in dd/mm/yyyy format</h4>"
				+ "<input type=\"text\" name=\"dob\" size=\"25\">"
				+"<h3>Address</h3>"
				+ "<input type=\"text\" name=\"address\" size=\"25\">"
				+ "<p></p>" + "<input type=\"submit\" value=\"Enter\">"
				+ "</form>");

		String username = request.getParameter("username");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");

		//set session variables
		request.getSession().setAttribute("name", username);
		request.getSession().setAttribute("DoB", dob);
		//this variable was created to allow the servlet to distinguish between name and dob being empty on the fit loading of the page and a user not filling them
	



		//If statements insures the username and Date of Birth fields are not empty
		if ((username != null && username.length() > 0) &&(dob != null && dob.length() > 0)) {
			
			//nested if statement checks if the address section has been left empty
			//if entered if the is not empty
			if( address != null && address.length()>0){

				//next if statement check if the user IS already registered
				if((String)request.getSession().getAttribute(username+dob)!=null){

					//updates the address to the address entered.
					out.println("<body >"  // web form generation
							+ "<h1>Your details have been updated.</h1>"
							+"<br>Your old address was listed as " + (String)request.getSession().getAttribute(username+dob));

					request.getSession().setAttribute(username+dob, address);

					out.println("<br>It has been updated to " + (String)request.getSession().getAttribute(username+dob)
							+"<br>To view your balance just log in with your name and date of birth.");

				}
				//the else is entered if the user IS NOT registered
				else{

					//register user
					request.getSession().setAttribute(username+dob, address);

					//tells user they have been registered and show them their details
					out.println("<h1>Your details have been registered.</h1>"
							+"<h3>Name</h3> " + (String)request.getSession().getAttribute("name") 
							+"<h3>Date of Birth</h3>" + (String)request.getSession().getAttribute("DoB")
							+"<h3>Address</h3>"+(String)request.getSession().getAttribute(username+dob)
							+"<br>To view your balance just log in with your name and date of birth."
							+"<br>To change your address just enter your name, date of birth and the new address you would like to register."
							+"<br>To make changes to you registered name and date of birth you must call into your local branch.");
				}
				
				//this else is entered if the address field is empty
			}else {
				
				//this for loop is entered if the user is registered in the system, it displays a random value to 
				//represent the users balance.
				if((String)request.getSession().getAttribute(username+dob)!= null){
					//generates a random number
					double random = Math.random() * 500 + 1;
					//will be used to display it as a decimal
					DecimalFormat df = new DecimalFormat("0.00");

					out.println("<h1 >Welcome Back "+ (String)request.getSession().getAttribute("name")+"</h1>"
							+"<h3>The address we have regestered for you is "+ (String)request.getSession().getAttribute(username+dob)+"</h3>"
							+"<br><br><h2>Your current Balance is: "+df.format(random)+"</h2>"
							+"<br><br>To change your address just enter your name, date of birth and the new address you would like to register."
							+"<br>To make changes to you registered name and date of birth you must call into your local branch.");
				}
				
				//this else statement is entered if the details submitted are not valid..
				//(i.e. the user is not registered or make a mistake in a field)
				else{
					//prints an error message to the screen
					out.println("<h1 >The name or date of birth entered does not match any account.</h1>"
							+"Please try again, If you do not have an account please fill in all the fields to register for an account");
				}
			}
			
		//this elseIf loop prints an error message if the name or date of birth fields have been left blank
		}else if ((username != null && username.length() == 0) || (dob != null && dob.length() == 0)){
			
			out.println("<h1 >Invalid details entered!</h1>"
					+"<h1>Please try again, If you do not have an account please fill in all the fields to register for an account</h1>");
		}

		out.println("</body></html>");
		out.close();
		
	}

	public String getServletInfo() { // Info about the servlet
		
		return "Servlet:WelcomeServlet This is a servlet for Assignment 1 of CT545, Author Tori Hume, Creation date 19/01/2017. ";

	}
}

