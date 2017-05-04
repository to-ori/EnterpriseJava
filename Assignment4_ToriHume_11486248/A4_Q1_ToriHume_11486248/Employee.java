//this is the super class.
//all subclasses can call on the methods from this class.
//it contains all the methods and variables that the subclasses have in common
public abstract class Employee {
	
	//variables
	public static final int RETIREMENT_AGE=65; 
	
	protected String name= null;
	protected int age = 0;
	protected float earned= 0.0f;
	protected float hourlyIncome = 0.0f;
	
	//constructor
	public Employee(String name, float hourlyIncome, int age){
		this.name = name;
		this.hourlyIncome= hourlyIncome;
		this.age= age;
	}
	
	public String info(){
		return name + " earned: $" + earned;
	}
	
	//abstract methods
	public abstract void work(int hours);
	public abstract void work();
	

}

