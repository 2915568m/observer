public interface Observer {

    void update(WeatherType weather);
    void update(IncidentStatus status);

}