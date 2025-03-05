import java.util.List;

public class MotorBike implements Observer {
	private int metersTravelled = 0;
	private Manufacturer manufacturer;
	private Rider rider;
	private Behaviour drivingBehaviour;
	private Behaviour wheel;
	private WeatherType weatherType;
	private IncidentStatus incidentStatus;
	
		public MotorBike(Manufacturer manufacturer, Rider rider, Behaviour drivingBehaviour, Weather raceWeather) {
			this.manufacturer = manufacturer;
			this.rider = rider;
			this.drivingBehaviour = drivingBehaviour;
			this.weatherType = raceWeather.getWeatherType();
			IncidentDetection.getInstance().registerObserver(this);
			IncidentDetection.getInstance().getIncidentStatus();
	
		}
	
		public void update(WeatherType weatherType) {
			this.weatherType = weatherType;
		}
	
		public void update(IncidentStatus incidentStatus)
		{  
			this.incidentStatus = incidentStatus;
	}

	public int driveBike() {
		int distance = drivingBehaviour.distanceTraveledInWeather(weatherType);
		metersTravelled += distance;
		return distance;
	}

	public void changeDrivingBehaviour(Behaviour newDrivingBehaviour) {
		this.drivingBehaviour = newDrivingBehaviour;
	}

	public void changeWheel(Behaviour wheel) {
		this.wheel = wheel;
	}

	public int getMetersTravelled() {
		return metersTravelled;
	}

	public String getRiderName() {
		return rider.getName();
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public Behaviour getDrivingBehaviour() {
		return drivingBehaviour;
	}

	public Behaviour getWheel() {
		return wheel;
	}

	public WeatherType getWeatherType() {
		return weatherType;
	}
	
	public void crash()
	{
		IncidentDetection.getInstance().notifyObserver();
	}

	public String toString() {
		return rider.getName() + " built by " + manufacturer + " has travelled " + metersTravelled + " meters";
	}

}
