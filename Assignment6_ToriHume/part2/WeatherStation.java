package Part3;
import java.util.ArrayList;
import java.util.OptionalDouble;


public class WeatherStation {
	
	//class variables
	String city;
	ArrayList<Measurement> measurements;
	
	//Constructor 
	public WeatherStation(String city){
		measurements= new ArrayList<Measurement>();
		this.city=city;
	}
	
	//add a measurement to the array list
	public void addMeasurement(Measurement measurement){
		measurements.add(measurement);
	}

	//method to calculate the average temperature between two times
	public OptionalDouble averageTemperature(int startTime, int endTime){
		
		//done using streams and lambda expressions
		 OptionalDouble averageTemp = measurements.stream()
				 .filter(p -> p.getTime() >= startTime && p.getTime()<= endTime)
				 .mapToDouble(p -> p.getTemperature())
				 .average();
		 //return the average temp
		return averageTemp;
	}
	
	
}
