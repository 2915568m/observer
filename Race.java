import java.util.ArrayList;
import java.util.List;

public class Race {
	private Weather raceWeather;
	private int raceTrackDistance;
	private List<MotorBike> racers = new ArrayList<MotorBike>();
	private final int numberOfStepsToChange = 2;

	public Race(WeatherType weather, int raceTrackDistance) {
		this.raceWeather = new Weather(weather);
		this.raceTrackDistance = raceTrackDistance;
	}

	public void makeFourBikes()
	{
		MotorBike One = new MotorBike(Manufacturer.VOLVO, new Rider("makka", "pakka"), new CautiousDriving(), raceWeather);
		MotorBike Two = new MotorBike(Manufacturer.VOLVO, new Rider("fake", "pakka"), new FastDriving(), raceWeather);
		MotorBike Three = new MotorBike(Manufacturer.VOLVO, new Rider("real", "pakka"), new CautiousDriving(), raceWeather);
		MotorBike Four = new MotorBike(Manufacturer.VOLVO, new Rider("almost", "pakka"), new FastDriving(), raceWeather);
		racers.add(One);
		racers.add(Two);
		racers.add(Three);
		racers.add(Four);
	}

	private String getOutcome() {
		MotorBike winner = null, runnerUp = null;
		int travelDistance, winnerDistance = -1, runnerUpDistance = -1;
		
		for (MotorBike bike : racers) {
			travelDistance = bike.getMetersTravelled();
			if (travelDistance > raceTrackDistance) {
				if (travelDistance > winnerDistance) {
					// Move current winner down to runner-up
					runnerUpDistance = winnerDistance;
					runnerUp = winner;
					// Update winner
					winnerDistance = travelDistance;
					winner = bike;
				} else if (travelDistance > runnerUpDistance) {
					// Update runner-up
					runnerUpDistance = travelDistance;
					runnerUp = bike;
				}
			}
		}
		
		if (winner == null)
			// If no racers, or method called before any racer finishes
			return "NO WINNER";
		else if (winnerDistance == runnerUpDistance)
			return "TIE";
		else
			return "WINNER:" + winner.getRiderName() + ", " + winner.getManufacturer();

	}

	public void progressWeather() {
		raceWeather.changeWeather();
	}

	public void changeBikeBehaviour(int position, Behaviour newDrivingBehaviour) {
		if (position >= 0 && position < racers.size())
			racers.get(position).changeDrivingBehaviour(newDrivingBehaviour);
	}

	public Weather getRaceWeather(){
		return raceWeather;
	}

	public WeatherType getWeatherType(){
		return raceWeather.getWeatherType();
	}

	public void raceStep() {
		for (MotorBike bike : racers) {
			bike.driveBike();
			System.out.println(bike.toString());
		}
	}

	public boolean isFinished() {
		Boolean hasFinished = false;
		int counter = 0;
		while (counter < racers.size() && ! hasFinished) {
			if (racers.get(counter).getMetersTravelled() > raceTrackDistance) {
				hasFinished = true;
			}
			counter += 1;
		}
		return hasFinished;
	}

	public void raceBikes() {
		int stepCount = 0;
		while (!isFinished()) {
			stepCount++;
			raceStep();
			if (stepCount % numberOfStepsToChange == 0) {
				progressWeather();
			}
		}
	}

	public int getRaceTrackDistance(){
		return raceTrackDistance;
	}


	public List<MotorBike> getRacers(){
		return this.racers;
	}

	public static void main(String[] args) {
		Race race = new Race(WeatherType.DRY, 100);
		race.makeFourBikes();
		race.raceBikes();
		System.out.println(race.getOutcome());
	}
}
