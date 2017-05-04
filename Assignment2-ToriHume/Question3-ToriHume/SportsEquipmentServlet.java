//Tori Hume
//id 11486248	

//a manager password has been hard coded. Please see it below in line 29.

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
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
public class SportsEquipmentServlet extends HttpServlet
{
	//create a ManagerPassword, for this system there is only one manager and so the password has been hard coded in and not stored in a database
	public String Manager_Password = "P123W987"; 

	//body of page
	static String PAGE_HEADER = "<html><head><title>Basket Ball Hoops </title>";
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
				"<h2>This page is for BasketBall Hoops </h2>" 
				+"<h5>If you would like to search for items from our store just enter fill in the fields for the desired properties. <br> the system will return all produects that match any of the search criteria. </h5>"+
				"<h3>Please note that the system is case senstive.</h3>"+
				"<form method=\"get\">" + 
				"<br>Hoop ID   "+
				"<input type=\"number\" name=\"HoopID\" size=\"30\">" +
				"<p></p>"+
				"<br>Product Name   "+
				"<input type=\"text\" name=\"productName\" size=\"50\">" +
				"<p></p>"+
				"<br>Basket Ball Hoop dimensions   "+
				"<input type=\"text\" name=\"hoopDimensions\" size=\"50\">" +
				"<p></p>" +
				"<br>Basket Ball Hoop Colour   "+
				"<input type=\"text\" name=\"hoopColour\" size=\"50\">" +
				"<p></p>" +
				"<br>Basket Ball Hoop Manufacturer    "+
				"<input type=\"text\" name=\"hoopManufacturer\" size=\"50\">" +
				"<p></p>" +
				"<br>Basket Ball Hoop Price     "+
				"<input type=\"number\" name=\"hoopPrice\" size=\"5\">" +
				"<p></p>" +
				"<h3>In order to add new items to the database you must enter an managers password</h3>"+
				"<input type=\"text\" name=\"MPassword\" size=\"8\">" +
				"<p></p>" +
				"<input type=\"submit\" value=\"Submit\">" +
				"<input type=\"reset\" value=\"Reset\">" +
				"</form>");

		//set information taken in by forms to java variables
		String BHoopID = request.getParameter("HoopID");
		String BProductName = request.getParameter("productName");
		String BHoopDimentions = request.getParameter("hoopDimensions");
		String BHoopColour = request.getParameter("hoopColour");
		String BHoopManufacturer = request.getParameter("hoopManufacturer");
		String BHoopPrice = request.getParameter("hoopPrice");
		String MPasswordEntered = request.getParameter("MPassword");

		//check if a manager password has been entered 
		if (MPasswordEntered !=null && MPasswordEntered.length()>0){

			//next check if the password entered is the correct password
			if (MPasswordEntered.equals(Manager_Password)){

				//if the password is correct check that the id and price are not null
				if((BHoopID !=null && BHoopID.length()>0) && (BHoopPrice != null && BHoopPrice.length()>0)){

					//create variables of type long for id and price
					Long Hoopid = Long.valueOf(BHoopID);
					Long HoopPrice = Long.valueOf(BHoopPrice);

					//next check that all other fields have been filled in
					if((BProductName != null && BProductName.length()>0)
							&&(BHoopDimentions != null && BHoopDimentions.length()>0)
							&&(BHoopColour != null && BHoopColour.length()>0)
							&&(BHoopManufacturer != null && BHoopManufacturer.length()>0)
							){

						//Check if there is a product in the Database with that id already

						try 
						{
							userTransaction.begin();
							EntityManager entityManager =
									entityManagerFactory.createEntityManager();

							out.println("Primary key: " + Hoopid + "<p>");
							BasketBallHoop BBHoop = new BasketBallHoop();	

							BBHoop =
									entityManager.find(BBHoop.getClass(), Hoopid);
							//retrieve product record from database
							entityManager.close();
							userTransaction.commit();

							//if there is no item registered with that id, the details are registered as a new item
							if(BBHoop == null){

								out.println("<br>ID not preassigned, Registering new BasketBall Hoop");
								BBHoop = new BasketBallHoop();	

								BBHoop.setBasketBallHoopId(Hoopid);
								BBHoop.setProductName(BProductName);
								BBHoop.setDimensions(BHoopDimentions);
								BBHoop.setColour(BHoopColour);
								BBHoop.setManufacturer(BHoopManufacturer);
								BBHoop.setPrice(HoopPrice);

								//details registered to the database
								try
								{
									userTransaction.begin();
									entityManager =
											entityManagerFactory.createEntityManager();
									out.println("<br>Transaction to store product details in the "+
											"database starts...<p>");
									entityManager.merge(BBHoop);
									entityManager.close();
									userTransaction.commit();

									//Print new details to screen
									out.println("<h1>Product added </h1>"
											+"Hoop ID: " + Hoopid
											+"<br> Product Name: " + BProductName
											+"<br> Dimentions: " +BHoopDimentions
											+"<br> Colour: " + BHoopColour
											+"<br> Manufacturer: " +BHoopManufacturer
											+ "<br> Price: $" +HoopPrice
											);
								}
								catch(Exception e) {
									out.println("Error: " + e + "<p>");
								}
							}

						}catch(Exception e) {
							out.println("Error: " + e + "<p>");
						}
					}
					//if any fields are left blank print this message to the screen
					else {
						out.print("<h1>All fields MUST be filled in before adding a new product to the database</h1>");
					}
				}
				
			}
			//if the password entered does not match the managerPassword hard coded in this servlet, print error to screen
			else {
				out.print("<h1>Incorrect Password entered.</h1>"
						+ "<h2>Please enter the correct password to add a new product to the database. <br>"
						+ "If you just wish to search for an item please leave the password section empty</h2>");
			}

		}
		
		//if the manager password has not been entered assume the user just wishes to search for products
		else {

			//if something has been entered into ID search for a object with a id containing the criteria entered
			if(BHoopID !=null && BHoopID.length()>0){
				//create variables of type long for id
				Long Hoopid = Long.valueOf(BHoopID);

				EntityManager entityManager =
						entityManagerFactory.createEntityManager();

				TypedQuery<BasketBallHoop> query = entityManager.createQuery(
						"SELECT c FROM BasketBallHoop c WHERE c.basketBallHoopId LIKE '%"+ Hoopid+"%'", BasketBallHoop.class);
				List<BasketBallHoop> idresults = query.getResultList();

				entityManager.close();

				//write results into HTML stream
				for(BasketBallHoop c: (idresults))
					out.println("<h1>Product Name: " + c.getProductName()+"</h1>"
							+"<br> Hoop ID: " + c.getBasketBallHoopId()
							+"<br> Dimentions: " +c.getDimensions()
							+"<br> Colour: " + c.getColour()
							+"<br> Manufacturer: " +c.getManufacturer()
							+ "<br> Price: $" +c.getPrice()
							);
			}
			
			//if something has been entered into Name field, search for a object with a name containing the criteria entered			
			if(BProductName !=null && BProductName.length()>0){

				EntityManager entityManager =
						entityManagerFactory.createEntityManager();

				TypedQuery<BasketBallHoop> query = entityManager.createQuery(
						"SELECT c FROM BasketBallHoop c WHERE c.productName LIKE '%"+ BProductName+"%'", BasketBallHoop.class);
				List<BasketBallHoop> pnresults = query.getResultList();

				entityManager.close();

				//write results into HTML stream
				for(BasketBallHoop c: (pnresults))
					out.println("<h1>Product Name: " + c.getProductName()+"</h1>"
							+"<br> Hoop ID: " + c.getBasketBallHoopId()
							+"<br> Dimentions: " +c.getDimensions()
							+"<br> Colour: " + c.getColour()
							+"<br> Manufacturer: " +c.getManufacturer()
							+ "<br> Price: $" +c.getPrice()
							);
			}

			//if something has been entered into dimension field, search for a object with a dimensions containing the criteria entered	
			if(BHoopDimentions !=null && BHoopDimentions.length()>0){

				EntityManager entityManager =
						entityManagerFactory.createEntityManager();

				TypedQuery<BasketBallHoop> query = entityManager.createQuery(
						"SELECT c FROM BasketBallHoop c WHERE c.dimensions LIKE '%"+ BHoopDimentions+"%'", BasketBallHoop.class);
				List<BasketBallHoop> dresults = query.getResultList();

				entityManager.close();

				//write results into HTML stream
				for(BasketBallHoop c: (dresults))
					out.println("<h1>Product Name: " + c.getProductName()+"</h1>"
							+"<br> Hoop ID: " + c.getBasketBallHoopId()
							+"<br> Dimentions: " +c.getDimensions()
							+"<br> Colour: " + c.getColour()
							+"<br> Manufacturer: " +c.getManufacturer()
							+ "<br> Price: $" +c.getPrice()
							);
			}

			//if something has been entered into colour field, search for a object with a colour containing the criteria entered	
			if(BHoopColour !=null && BHoopColour.length()>0){

				EntityManager entityManager =
						entityManagerFactory.createEntityManager();

				TypedQuery<BasketBallHoop> query = entityManager.createQuery(
						"SELECT c FROM BasketBallHoop c WHERE c.colour LIKE '%"+ BHoopColour+"%'", BasketBallHoop.class);
				List<BasketBallHoop> cresults = query.getResultList();

				entityManager.close();

				//write results into HTML stream
				for(BasketBallHoop c: (cresults))
					out.println("<h1>Product Name: " + c.getProductName()+"</h1>"
							+"<br> Hoop ID: " + c.getBasketBallHoopId()
							+"<br> Dimentions: " +c.getDimensions()
							+"<br> Colour: " + c.getColour()
							+"<br> Manufacturer: " +c.getManufacturer()
							+ "<br> Price: $" +c.getPrice()
							);
			}

			//if something has been entered into Manufacturer field, search for a object with a manufacturer containing the criteria entered	
			if(BHoopManufacturer !=null && BHoopManufacturer.length()>0){


				EntityManager entityManager =
						entityManagerFactory.createEntityManager();

				TypedQuery<BasketBallHoop> query = entityManager.createQuery(
						"SELECT c FROM BasketBallHoop c WHERE c.manufacturer LIKE '%"+ BHoopManufacturer+"%'", BasketBallHoop.class);
				List<BasketBallHoop> mresults = query.getResultList();

				entityManager.close();

				//write results into HTML stream
				for(BasketBallHoop c: (mresults))
					out.println("<h1>Product Name: " + c.getProductName()+"</h1>"
							+"<br> Hoop ID: " + c.getBasketBallHoopId()
							+"<br> Dimentions: " +c.getDimensions()
							+"<br> Colour: " + c.getColour()
							+"<br> Manufacturer: " +c.getManufacturer()
							+ "<br> Price: $" +c.getPrice()
							);
			}

			//if something has been entered into price field, search for a object with a price containing the criteria entered	
			if(BHoopPrice !=null && BHoopPrice.length()>0 ){
				//create variables of type long for price
				Long HoopPrice = Long.valueOf(BHoopPrice);


				EntityManager entityManager =
						entityManagerFactory.createEntityManager();

				TypedQuery<BasketBallHoop> query = entityManager.createQuery(
						"SELECT c FROM BasketBallHoop c WHERE c.price LIKE '%"+ HoopPrice+"%'", BasketBallHoop.class);
				List<BasketBallHoop> presults = query.getResultList();

				entityManager.close();

				//write results into HTML stream
				for(BasketBallHoop c: (presults))
					out.println("<h1>Product Name: " + c.getProductName()+"</h1>"
							+"<br> Hoop ID: " + c.getBasketBallHoopId()
							+"<br> Dimentions: " +c.getDimensions()
							+"<br> Colour: " + c.getColour()
							+"<br> Manufacturer: " +c.getManufacturer()
							+ "<br> Price: $" +c.getPrice()
							);

			}

		}


		out.println(PAGE_FOOTER);
		out.close();
					}
}


