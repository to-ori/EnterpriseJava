package Assignment4_Q3;

//this is a subclass of Number
public class NumberInt extends Number {
	int number;
	//the constructor take in a primitive int and sets the variable number equal to it
	public NumberInt(int num){
		number=num;
	}

	
//here we override the abstract methods of from the superclass Number
	
	//the multiply method creates a new object of type NumberInt
	//it passes an int
	//this int is calculated by calling the getnum method of the number 
	//object passed into the multiply method. this returns a double,
	//and multiplying it by the int number variable contained in this class
	//the answer is then cast to a int
	//
	@Override
	public Number multiply(Number num) {
		
		NumberInt n= new NumberInt((int)(number*num.getNum()));
		
		return n;
	}


	// getNum method returns the primitive int number variable in this class
	@Override
	public double getNum() {
		// TODO Auto-generated method stub
		return number;
	}

}
