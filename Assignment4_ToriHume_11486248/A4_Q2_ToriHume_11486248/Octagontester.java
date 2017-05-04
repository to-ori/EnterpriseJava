package Assignment4_Q2;

public class Octagontester {

	public static void main(String[] args) {
		//create 3 instances of octagon
		Octagon octagon1 = new Octagon();
		Octagon octagon2 = new Octagon();
		Octagon octagon3 = new Octagon();

		//use the updateFromConsole and writeToConcole method to set and display the values
		octagon1.updateFromConsole();
		octagon1.writeToConsole();

		octagon2.updateFromConsole();
		octagon2.writeToConsole();

		octagon3.updateFromConsole();
		octagon3.writeToConsole();

		//if statements are used to display the result of the compareTo method
		if(octagon1.compareTo(octagon2)==1){
			System.out.println("Octagon1 has a greater area than Octagon2. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}else if(octagon1.compareTo(octagon2)==0){
			System.out.println("Octagon1 has the same area as Octagon2. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}else if(octagon1.compareTo(octagon2)==-1){
			System.out.println("Octagon1 has a smaller area than Octagon2. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}


		if(octagon1.compareTo(octagon3)==1){
			System.out.println("Octagon1 has a greater area than Octagon3. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}else if(octagon1.compareTo(octagon3)==0){
			System.out.println("Octagon1 has the same area as Octagon3. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}else if(octagon1.compareTo(octagon3)==-1){
			System.out.println("Octagon1 has a smaller area than Octagon3. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}

		if(octagon3.compareTo(octagon2)==1){
			System.out.println("Octagon3 has a greater area than Octagon2. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}else if(octagon3.compareTo(octagon2)==0){
			System.out.println("Octagon3 has the same area as Octagon2. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}else if(octagon3.compareTo(octagon2)==-1){
			System.out.println("Octagon3 has a smaller area than Octagon2. \nOctagon1 area: "+octagon1.getArea()+"\nOctagon2 area: "+octagon2.getArea());
		}
	}

}
