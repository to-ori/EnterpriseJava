package pack;

import javax.ejb.Remote;

//The business interface of the calculator bean

@Remote
public interface CalculatorBeanRemote {
	public double add(double x, double y);
	public double subtract(double x, double y);
	public double multiply(double x, double y);
	public double divide(double x, double y);
	public double squareRoot(double x);
	public double cubedRoot(double x);
}
