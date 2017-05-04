package Assignment4_Q3;

//this class has been taken from the lecture notes of CT545
//it was then modified to make it a generic class that takes subclasses of Number, while it itself is a subclass of GeometricFigure2
//T is a place holder for these objects.
public class Circle<T extends Number> extends GeometricFigure2{

		private T radius;
		private T area;
		
		public Circle(T radius) {
		this.radius = radius;
		}
		
		//to calculate the area of the circle we call on the multiply methods in the number objects
		public T calcArea() {
		Number area=radius.multiply(radius.multiply(new NumberDouble(PI)));
		 return (T) area;
		}
		
		
		public void display() { // overrides method display() in superclass
		System.out.println("This is a circle with radius " + radius.getNum());
		}
		

}
