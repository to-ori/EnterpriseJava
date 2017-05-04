
//this is a sub class of Employee
public class Worker extends Employee {

	//worker has a additional filed to its superclass, coWorker
	private Worker coWorker = null;

	//constructor calls on super classe's(Employee) constructor and also sets coWokers
	public Worker(String name, float hourlyIncome, int age, Worker coWorker){

		super(name, hourlyIncome, age);
		this.coWorker=coWorker;

	}

	//the work method has been modified 
	//this new version eliminates the need for any for loops and reduces the number of if conditions to one
	
	public void work(int hours){
		
		earned += (hourlyIncome*hours);
		//check to see if there a coWorker
		if(coWorker != null){
			//find the modules of hours divided by 5. 
			//you then take this away to insure you have a whole number when divided by 5
			//coworkhour is equal to the number of times 5 divided into hours evenly
		int coworkhour= (hours-hours%5)/5;
		//delegate method is then called only once and passed in coworkers
		delegate(coworkhour);
		}
		
	}
	
	public void work(){
		//hours worked from age imputed to retirement
		int hoursWorked=(RETIREMENT_AGE-age)*1600;
		System.out.println(hoursWorked);
		//income earned over that time
		earned+= (hourlyIncome*hoursWorked);
		if(coWorker != null){
			//find the modules of hoursworked divided by 5. 
			//you then take this away to insure you have a whole number when divided by 5
			//coworkhour is equal to the number of times 5 divided into hoursworked evenly
		int coworkhour= (hoursWorked-hoursWorked%5)/5;
		//delegate method is then called only once and passed in coworkers
		delegate(coworkhour);
		}
		
	}
	
	
	//this method remains the same as previous
	public void delegate(int hours){
		coWorker.work(hours);
	}
}

