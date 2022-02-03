package simulator.model;

import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject{

	protected Junction srcJunc;
	protected Junction destJunc;
	protected int maxSpeed;
	protected int speedLimit;
	protected int contLimit;
	protected int totalCont;
	protected int length;
	protected Weather weather;
	
	protected List<Vehicle> vehicles;
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
			super(id);
			try {
				if (maxSpeed <= 0 || contLimit < 0 || length <= 0 || srcJunc == null || destJunc == null || weather == null) 
					throw new Exception();
				
				this.srcJunc = srcJunc;
				this.destJunc = destJunc;
				this.maxSpeed= maxSpeed;
				this.contLimit = contLimit;
				this.length = length;
				this.weather = weather;
				this.speedLimit = maxSpeed;
				this.totalCont = 0;
			}
			catch(Exception ex) {
				ex.getMessage();
			}
	}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		 reduceTotalContamination();
		 updateSpeedLimit();
		 for (Vehicle v: vehicles) {
			 v.setCurrentSpeed(calculateVehicleSpeed(v));
			 v.advance(time);
		 }//Reordenar
		
	}

	void enter(Vehicle v) throws Exception{
		if (v.getCurrentSpeed() != 0 && v.getLocation() != 0) 
			throw new Exception();
		vehicles.add(v);
	}
	void exit(Vehicle v) {
		vehicles.remove(v);
	}
	
	void setWeather(Weather w) throws Exception {
		if (w == null) throw new Exception();
		weather = w;
	}
	void addContamination(int c) throws Exception {
		if (c < 0) throw new Exception();
		totalCont += c;
	}
	
	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);


	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
