package Part3;

public class Measurement {
	//class variables
private int time;
private double temperature;

//constructor to be passed time and temp
public Measurement(int time, double temp){
	setTime(time);
	setTemperature(temp);
}

//setters and getters for time and temperature
public int getTime() {
	return time;
}

public void setTime(int time) {
	this.time = time;
}

public double getTemperature() {
	return temperature;
}

public void setTemperature(double temperature) {
	this.temperature = temperature;
} 
}
