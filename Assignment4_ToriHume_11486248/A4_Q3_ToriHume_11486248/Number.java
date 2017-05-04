package Assignment4_Q3;

//This is an abstract class.
//as instructed in the assignment is contains a main method that creates a new Circle object with a radius of type NumberDouble

public abstract class Number {

	public static void main(String[] args) {
		
		
		Circle<Number> circle = new Circle<Number>(new NumberDouble(6.9));
		System.out.println(circle.calcArea().getNum());
		
		
	}
	//these are two overloaded newNumber methods. depending on the variable passed into them either a 
	//new NumberInt or NumberDouble object is created 
	public static Number newNumber(int i){
		NumberInt ni = new NumberInt(i);
		return ni;
	}
	
	public static Number newNumber(double d){
		NumberDouble nd = new NumberDouble(d);
		return nd;
	}
	

	//the getNum and multiply methods are abstract and will be implemented in the sub classes
	public abstract double getNum();
	
	public abstract Number multiply(Number num);
	

}
