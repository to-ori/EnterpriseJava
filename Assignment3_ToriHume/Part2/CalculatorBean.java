package pack;

import javax.ejb.Stateless;


// The EJB class
@Stateless public class CalculatorBean implements CalculatorBeanRemote {
	
	//create methods to perform the calculations
	public double add(double x, double y) {
		return x + y;
	}
	
	public double subtract(double x, double y) {
		return x - y;
	}
	
	public double multiply(double x, double y) {
		return x * y;
	}
	
	public double divide(double x, double y) {
		return x / y;
	}
	
	public double squareRoot(double x) {
		return Math.sqrt(x);
	}
	
	public double cubedRoot(double x) {
		return Math.cbrt(x);
	}
	
}
