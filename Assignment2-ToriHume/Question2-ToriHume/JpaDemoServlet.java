import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


@WebServlet("/jpatest1")
public class JpaDemoServlet extends HttpServlet
{
	
	//body of page
	static String PAGE_HEADER = "<html><head><title>JPA demo servlet"
			+"2</title>";
	static String PAGE_FOOTER = "</body></html>";
	@PersistenceUnit (unitName="Assignmnet2")

	private EntityManagerFactory entityManagerFactory;
	@Resource
	private UserTransaction userTransaction;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
					{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(PAGE_HEADER);
		out.println("<body>" +
				"<h2>Database query: </h2>" +
				"<h3>To see your details please enter only your Customer ID</h3>"+
				"<h3>To resister or update your details please fill in all fields</h3>"+
				"<form method=\"get\">" +
				"<h4>First Name</h4>"+
				"<input type=\"text\" name=\"firstname\" size=\"25\">" +
				"<p></p>"+
				"<h4>Last Name</h4>"+
				"<input type=\"text\" name=\"lastname\" size=\"25\">" +
				"<p></p>"+
				"<h4>Email Address</h4>"+
				"<input type=\"text\" name=\"custEmail\" size=\"50\">" +
				"<p></p>" +
				"<h4>Customer ID </h4>"+
				"<input type=\"number\" name=\"identifier\" size=\"5\">" +
				"<p></p>" +
				"<input type=\"submit\" value=\"Submit\">" +
				"<input type=\"reset\" value=\"Reset\">" +
				"</form>");

		//set information taken in by forms to java variables
		String custFirstName = request.getParameter("firstname");
		String custLastName = request.getParameter("lastname");
		String custEmailAdd = request.getParameter("custEmail");
		String idstr = request.getParameter("identifier");
		

		//if all fields have been filled in by the user
		if ((custFirstName != null && custFirstName.length() > 0 ) 
				&& (custLastName != null && custLastName.length() > 0 ) 
				&& (custEmailAdd != null && custEmailAdd.length() > 0 )) {
			
			//set id 
			Long id = Long.valueOf(idstr);
			


			try // now we retrieve the customer details from the database for the customer with that id
			{
				userTransaction.begin();
				EntityManager entityManager =
						entityManagerFactory.createEntityManager();
				out.println("Transaction to retrieve customer "
						+" starts...<p>");
				out.println("Primary key: " + id + "<p>");
				Customer customer = new Customer();
				customer =
						entityManager.find(customer.getClass(), id);
				// retrieve customer record from database
				entityManager.close();
				userTransaction.commit();
				
				//if there is no customer registered with that id, the details are registered as a new customer
				if(customer == null){
					
					out.println("<br>ID not preassigned, Registering new customer");
					customer = new Customer();
					id = Long.valueOf(idstr);
					customer.setCustomerId(id);
					customer.setFirstName(custFirstName);
					customer.setLastName(custLastName);
					customer.setEmail(custEmailAdd);

					//details registered to the database
					try
					{
						userTransaction.begin();
						entityManager =
								entityManagerFactory.createEntityManager();
						out.println("<br>Transaction to store customer details in the "+
								"database starts...<p>");
						entityManager.persist(customer);
						entityManager.close();
						userTransaction.commit();
					}
					catch(Exception e) {
						out.println("Error: " + e + "<p>");
					}
				}
				//If there is a customer registered to the id already:
				else{
					//Print information currently listed for that customer
					out.println("<br>Customer object for id " + id + " found:<br> Name: "
							+ customer.getFirstName() +" "+ customer.getLastName()+"<br> Email Address: "
							+ customer.getEmail() );
					Customer Updatecustomer = new Customer();
					id = Long.valueOf(idstr);
					Updatecustomer.setCustomerId(id);
					Updatecustomer.setFirstName(custFirstName);
					Updatecustomer.setLastName(custLastName);
					Updatecustomer.setEmail(custEmailAdd);

					//use merge command to update with the new detials given
					try
					{
						userTransaction.begin();
						entityManager =
								entityManagerFactory.createEntityManager();
						entityManager.merge(Updatecustomer);
						entityManager.close();
						userTransaction.commit();
					}
					catch(Exception e) {
						out.println("Error: " + e + "<p>");
					}

					//Print new details to screen
					out.println("<br>Customer object for id " + id + " updated to:<br> Name: "
							+ Updatecustomer.getFirstName() +" "+ Updatecustomer.getLastName()+"<br> Email Address: "
							+ Updatecustomer.getEmail());
				}
			}
			catch(Exception e) {
				out.println("Error: " + e);
			}

			
			//If some of the fields are blank 
		}else if ((custFirstName != null && custFirstName.length() == 0 ) 
				|| (custLastName != null && custLastName.length() == 0 ) 
				|| (custEmailAdd != null && custEmailAdd.length() == 0 )) {
			
			Long id = Long.valueOf(idstr);
			//check if the ID is empty
			if(id==null ){
			out.println("Please enter a valid ID, or fill in all details to register");
			}
			//If ID is not empty
			else{
				
				
				try // now we retrieve the customer details from the database, associated with that ID
				{
					userTransaction.begin();
					EntityManager entityManager =
							entityManagerFactory.createEntityManager();
					out.println("Transaction to retrieve customer "
							+" starts...<p>");
					out.println("Primary key: " + id + "<p>");
					Customer customer = new Customer();
					customer =
							entityManager.find(customer.getClass(), id);
					// retrieve customer record from database
					entityManager.close();
					userTransaction.commit();
					
				

				//If no customer details are returned tell customer the id is not valid
				if (customer == null){
					out.println("<br> <h2>"+id+" is not a valid id. <br> Please enter a valid ID or regsiter as a new user</h2>");
				}
				//If customer details are returned, Display them.
				else{
					out.println("<br>Customer object for id " + id + " found:<br> Name: "
							+ customer.getFirstName() +" "+ customer.getLastName()+"<br> Email Address: "
							+ customer.getEmail() );
					
				}
				}catch(Exception e) {
					out.println("Error: " + e);
				}

			}
		}
		
		
		
		response.getWriter().println("<p>Servlet doGet()"
				+ " finished");
		out.println(PAGE_FOOTER);
		out.close();
					}
}


