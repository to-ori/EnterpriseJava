package Assignment4_Q2;

import java.util.Scanner;

public class Octagon extends GeometricFigure2 implements ConsoleIO, ComparableGeometricFigure<Octagon>{

	//declare class variables
	private int sideLength;
	private String octIsFilled;
	private double area;
	Scanner input;


	//here we override the updateFromCnsole method found in the interface ConsoleIO
	//we use an instance of scanner to take in an int form the user and set the variable 
	//sideLength equal to this new variable. and call the calcArea method and pass the value returned to the setArea method
	//a try catch is used to deal with error that might occur for non int values being entered
	@Override
	public void updateFromConsole() {
		input = new Scanner(System.in);
		try{

			System.out.println("Please enter the length of the side of the octagon:"
					+ "\nNote: this is a symmetric octagon and so all sides will have the same length.");
			int side = input.nextInt();
			setSideLength(side);
			setArea(calcArea());

		}catch(Exception e){
			System.out.println("\nERROR!! You must enter an integer\n");

		}
	}


	//again we override a method for the interface ConsoleIO
	//here we check if the octagon is filled or empty and set the string variable 
	//octIsFilled accordingly
	//we then call on our toString to display the details of the Octagon to the screen
	@Override
	public void writeToConsole() {
		if(isFilled()){
			octIsFilled="This Octagon is filled";
		}else{
			octIsFilled="This Octagon is not filled";
		}


		System.out.println(toString());
	}


	//here we override the calcArea method from the superclass GeometricFigure2
	//we set the area using the setArea method and the formula to calculate the area of a Octagon
	@Override
	public double calcArea() {

		setArea(2*(1+Math.sqrt(2))*getSideLength());
		return area;
	}



	//here we override the compareTo method from 
	//the interface ComparableGeometricFigure (and in turn the comparable interface)
	//we comparer the area of this octagon to the area of the octagon passed into the method
	@Override
	public int compareTo(Octagon o) {
		if(calcArea() > (o).calcArea()){
			return 1;
		}
		else if(calcArea() < (o).calcArea()){
			return -1;
		}
		else{
			return 0;
		}
	}

	//Overridden toString
	@Override
	public String toString() {
		return "Octagons details: \n"+octIsFilled+"\nSide Length:" + sideLength + ",\nArea:" + calcArea() + "\n";
	}

	//getters and setters for private variables
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

}
