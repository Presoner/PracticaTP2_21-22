package simulator.model;

public class InterCityRoad extends Road {

	public InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}
	
	@Override
	void reduceTotalContamination() {
		int x = 0;
		if (weather == Weather.SUNNY) x = 2;
	    if (weather == Weather.CLOUDY) x = 3;
		if (weather == Weather.RAINY) x = 10; 
		if (weather == Weather.WINDY) x = 15;
		if (weather == Weather.STORM) x = 20;
		
		
		totalCont = ((100 - x) * totalCont) / 100;
		
		
	}

	@Override
	void updateSpeedLimit() {
		if (totalCont > contLimit)
			speedLimit = maxSpeed / 2;
		else
			speedLimit = maxSpeed;
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		
		if (weather == Weather.STORM) 
			v.setCurrentSpeed((speedLimit*8)/10);
		else 
			v.setCurrentSpeed(speedLimit);
		return 0;
	}

}
