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
						"<form method=\"get\">" +
						"<h4>First Name</h4>"+
						"<input type=\"text\" name=\"firstname\" size=\"25\">" +
						"<p></p>"+
						//add a field in the form to take in the users last name
						"<h4>Last Name</h4>"+
						"<input type=\"text\" name=\"lastname\" size=\"25\">" +
						"<p></p>"+
						//add a field in the form to take in the users email address
						"<h4>Email Address</h4>"+
						"<input type=\"text\" name=\"custEmail\" size=\"50\">" +
						"<p></p>" +
						"<h4>Customer ID </h4>"+
						"<input type=\"number\" name=\"identifier\" size=\"5\">" +
						"<p></p>" +
						"<input type=\"submit\" value=\"Submit\">" +
						"<input type=\"reset\" value=\"Reset\">" +
						"</form>");
					
					//create java variables containing the values passed in to the form
				String custFirstName = request.getParameter("firstname");
				String custLastName = request.getParameter("lastname");
				String custEmailAdd = request.getParameter("custEmail");
				String idstr = request.getParameter("identifier");
				
				//if statment to make sure fields aren't empty
				if ((custFirstName != null && custFirstName.length() > 0 ) 
						&& (custLastName != null && custLastName.length() > 0 ) 
						&& (custEmailAdd != null && custEmailAdd.length() > 0 )) {
							
							//if all fields have been filled in create a new instance of customer and sets its values as the values contained in the java varialbles
					Customer customer = new Customer();
					Long id = Long.valueOf(idstr);
					customer.setCustomerId(id);
					customer.setFirstName(custFirstName);
					customer.setLastName(custLastName);
					customer.setEmail(custEmailAdd);

					
					//try to conect with the database and store the customer entity
					try
					{
						userTransaction.begin();
						EntityManager entityManager =
								entityManagerFactory.createEntityManager();
						out.println("Transaction to store customer details in the"+
								"database starts...<p>");
								entityManager.persist(customer);
								entityManager.close();
								userTransaction.commit();
							
								out.println("Transaction ended.");

								
								//retrive the details back again to confirm they were stored correctly
								try // now we retrieve the customer details from the database...
								{
									userTransaction.begin();
									entityManager =
											entityManagerFactory.createEntityManager();
									out.println("Transaction to retrieve customer "
											+" starts...<p>");
											out.println("Primary key: " + id + "<p>");
											customer = new Customer();
											customer =
													entityManager.find(customer.getClass(), id);
											// retrieve customer record from database
											entityManager.close();
											userTransaction.commit();
											out.println("Transaction ended."); 

											//if no customer found print message to screen
											if(customer == null)
												out.println("Nothing found");
											//otherwise display the details for the customer returned from the database
											else
												out.println("Customer object for id " + id + " found: Name: "
														+ customer.getFirstName() +" "+ customer.getLastName()+" Email Address: "
														+ customer.getEmail());
								}
								catch(Exception e) {
									out.println("Error: " + e);
								}
					}
					catch(Exception e) {
						out.println("Error: " + e + "<p>");
					} 
					
					//if not all fields have been filled in display this message
					} else if ((custFirstName != null && custFirstName.length() == 0 ) 
							|| (custLastName != null && custLastName.length() == 0 ) 
							|| (custEmailAdd != null && custEmailAdd.length() == 0 )) {
						out.println("Please fill in all details!!");
					}
					
					
				response.getWriter().println("<p>Servlet doGet()"
						+ " finished");
						out.println(PAGE_FOOTER);
						out.close();
							}
}


