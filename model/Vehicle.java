package simulator.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

public class Vehicle extends SimulatedObject{

	private List<Junction> itinerary;
	private int maxSpeed;
	private int currentSpeed;
	private VehicleStatus status;
	private Road road;
	private int location;
	private int contClass;
	private int totalCO2;
	private int totalDistance;
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
			super(id);
			try{
				if (contClass < 0 || contClass > 10 || maxSpeed < 0 || itinerary.size() < 2) {
					throw new Exception("Vehicle exception");
				}
				itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
				status = VehicleStatus.PENDING;
				currentSpeed = 0;
			}
			catch (Exception ex){
				ex.getMessage();
			}

	}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
		if (status.equals(VehicleStatus.TRAVELING)) {
			
			int oldlocation = location;
			
			if (location + currentSpeed < road.getLength())
				location += currentSpeed;
			else {
				location = road.getLength();
				//junction.add(this);
				//status = WAITING;
				//currentSpeed = 0;
			}
			
			contClass *= (location - oldlocation);
			totalCO2 += contClass;
			
			//road.addcontClass(contClass);
			
		}
		
	}
	
	void moveToNextRoad() {
		/*road.exit()
		
		 */
		
		
	}
	void setSpeed(int s) throws Exception{
		if (s < 0) throw new Exception("Negative Speed");
		if (s >= maxSpeed)
			currentSpeed = maxSpeed;
		else currentSpeed = s;
	}
		
	void setcontClassClass(int c) throws Exception{
		if (c < 0 || c > 10) throw new Exception("Wrong contClass");
		contClass = c;
		
	}

	@Override
	public JSONObject report() {

		JSONObject jo1 = new JSONObject();

		// we put some keys with simple values into 'jo1'
		jo1.put("id", _id);
		jo1.put("speed", currentSpeed);
		jo1.put("distance", totalDistance);
		jo1.put("co2", totalCO2);
		jo1.put("class", contClass);
		jo1.put("status", status.toString());
		
		if (status != VehicleStatus.PENDING && status != VehicleStatus.ARRIVED) {	
			//jo1.put("road", road.getID());
			jo1.put("location", location);
		}
		
		return jo1;
	}

	public List<Junction> getItinerary() {
		return itinerary;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public Road getRoad() {
		return road;
	}

	public int getLocation() {
		return location;
	}

	public int getContClass() {
		return contClass;
	}

	public int getTotalCO2() {
		return totalCO2;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

}
