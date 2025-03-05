import java.util.ArrayList;
import java.util.List;

public class Weather implements Subject {
	private WeatherType weatherType;
	private List<Observer> observers;


	public Weather(WeatherType weatherType) {
		this.weatherType = weatherType;
		this.observers = new ArrayList<>();
		
	}

	
	// For observers to pull the weather when notified or at creation
	public WeatherType getWeatherType() {
		return weatherType;
	}

	public void registerObserver(Observer observer)
	{
		observers.add(observer);
	}

	public void removeObserver(Observer observer)
	{
		observers.remove(observer);
	}

	public void notifyObserver()
	{
		for (Observer observer : observers) {observer.update(weatherType);}
	}



	public void changeWeather() {
		weatherType = weatherType.next();
	}
}
