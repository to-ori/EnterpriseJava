//this is a sub class of Employee
public class Workaholic extends Employee{

	//Workaholic constant 
	public static final int OVERTIME = 500;

	//constructor 
	public Workaholic(String name, float hourlyIncome, int age){
		//calls Employee's constructor (its superclass)
		super(name, hourlyIncome, age);
	}

	//Implements the abstract methods from the Employee class's
	public void work(int hours){
		earned += (hourlyIncome*(hours+OVERTIME));	
	}

	public void work(){

		int yearsWorked=RETIREMENT_AGE-age;
		earned+= (hourlyIncome*(2000+ OVERTIME))*yearsWorked;
	}

}

