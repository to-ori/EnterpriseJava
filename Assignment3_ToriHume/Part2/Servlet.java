package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servlet")
public class Servlet extends HttpServlet {

	@EJB (lookup="java:global/calculatorBeans/CalculatorBean")

	public CalculatorBeanRemote calculator;

	/* calculator is the representative of the bean, created at runtime using dependency injection */
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


	
		out.println("<html>" + "<head><title>Assignment 3 Calculator</title></head>");

		//create a web form to take in commands from the user.
		out.println("<body  bgcolor=\"#ccccff\">" 

				+ "<div bgcolor=\"#D3D3D3\"><form method=\"get\">"
				+ "X= <input type=\"number\" name=\"x\" >"
				+"<br>"
				+"<br>"
				+"<input type=\"radio\" name=\"calc\" value=\"Add\"/>Add"
				+"<input type=\"radio\" name=\"calc\" value=\"Subtract\"/>Subtract"
				+"<br>"
				+"<br>"
				+"<input type=\"radio\" name=\"calc\" value=\"Divide\"/>Divide"
				+"<input type=\"radio\" name=\"calc\" value=\"Multiply\"/>Multiply"
				+"<br>"
				+"<br>"
				+"<input type=\"radio\" name=\"calc\" value=\"SquRoot\"/>Square Root of X"
				+"<input type=\"radio\" name=\"calc\" value=\"CubRoot\"/>Cubed Root of X"
				+"<br>"
				+"<br>"
				+"Y= <input type= \"number\" name= \"y\">"
				+"<br>"
				+ "<p></p>" + "<input type=\"submit\" value=\"Enter\">"
				+ "</form></div>");

		//set values entered for x and y to java string values
		String x = request.getParameter("x");

		String y = request.getParameter("y");

		//create a java double value answer used to store the result of the calculations
		double answer=0;

		//check that x has been filled in
		if(x != null && x.length()>0){

			//cast x to a double
			double X = Double.parseDouble(x);

			//check if the square root of X option was selected
			//try catch used to throw error if a illegal calculation selected
			
			if(request.getParameter("calc").equals("SquRoot"))
			{ try{
				if(X>0){
				answer = calculator.squareRoot(X);
				}else{
					out.println("Error: Calculation not possible, X can not be a minus number"+"<p>");
					
				}
			}catch(Exception e) {
				out.println("Error: Calculation not possible"+"<p>");
			}
			}
			
			//check if the cubed root of X option was selected
			//try catch used to throw error if a illegal calculation selected
			if(request.getParameter("calc").equals("CubRoot"))
			{
				try{
					answer = calculator.cubedRoot(X);
				}catch(Exception e) {
					out.println("Error: Calculation not possible"+"<p>");
				}
			}


			//if both x and y have values entered enter this if loop
			if(y != null && y.length()>0){

				//cast y to a double
				double Y = Double.parseDouble(y); 

				//Call appropriate calculation method according to the selection made from the Radio Buttons named "calc".
				if(request.getParameter("calc").equals("Add"))
				{
					answer = calculator.add(X,Y);
				}
				if(request.getParameter("calc").equals("Subtract"))
				{
					answer = calculator.subtract(X,Y);
				}
				if(request.getParameter("calc").equals("Divide"))
				{ try{
					answer = calculator.divide(X,Y);
				} catch(Exception e) {
					out.println("Error: Calculation not possible"+"<p>");
				}
				}
				if(request.getParameter("calc").equals("Multiply"))
				{
					answer = calculator.multiply(X,Y);
				}

			}
		}
		//print the answer the the screen
		out.println("<h2>Answer ="+answer +"</h2>");


		out.println("</body></html>");
		out.close();
					}
}