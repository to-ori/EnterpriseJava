package Assignment4_Q3;

//the code for this class was taken from the lecture notes for CT545 
//the only change made to it was in the abstract method calcArea()
//the return type was changed to a Number object
public abstract class GeometricFigure2 {
	
		public static final double PI = 3.141592653;
		private boolean filled;
		
		public GeometricFigure2() {
		filled = false;
		}
		public boolean isFilled() {
		return filled;
		}
		public void setFilled(boolean filled) {
		this.filled = filled;
		}
		public void display() {
		System.out.println("This is some geometric figure.");
		}
		public abstract Number calcArea();

}
