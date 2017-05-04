package Part3;

public class Assignment6TesterClass {
	
	
	public static void main(String[] args) {
		Measurement m1, m2, m3 ,m4;
		WeatherStation testStation;
		m1= new Measurement(6, 50);
		m2= new Measurement(7, 50);
		m3= new Measurement(8, 50);
		m4= new Measurement(9, 100);
		testStation = new WeatherStation("Galway");
		testStation.addMeasurement(m1);
		testStation.addMeasurement(m2);
		testStation.addMeasurement(m3);
		testStation.addMeasurement(m4);
		
		System.out.println("The average temp between 6am and 9am was: "+testStation.averageTemperature(6, 8).toString());
		

	}

}
