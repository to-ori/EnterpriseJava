package Assignment4_Q3;

public class NumberDouble extends Number {
	double number;
	//the constructor take in a primitive double and sets the variable number equal to it
	
	public NumberDouble(double num){
		number=num;
	}
	
	//here we override the abstract methods of from the superclass Number
	
	// getNum method returns the primitive double number variable in this class
	@Override
	public double getNum() {
		
		return number;
	}

	
	//the multiply method creates a new object of type NumberDouble
	//it passes a double
	//this double is calculated by calling the getnum method of the number 
	//object passed into the multiply method and multiplying it
	//by the double number variable contained in this class
	//
	@Override
	public Number multiply(Number num) {
		NumberDouble n= new NumberDouble(number*num.getNum());
		return n;
	}

	

}
