import java.util.ArrayList;
import java.util.List;

public class IncidentDetection implements Subject {
    
    private static IncidentDetection instance;
    private List<Observer> observers = new ArrayList<>();
    private IncidentStatus incidentStatus = IncidentStatus.SAFE;

    private IncidentDetection()
    {
    }

    public static IncidentDetection getInstance()
    {
        if (instance == null)
        {
            instance = new IncidentDetection();
        }
        return instance;
    }

    public IncidentStatus getIncidentStatus()
    {
        return incidentStatus;
    }

    @Override
    public void registerObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) 
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver()
    {
        for (Observer observer : observers) {observer.update(incidentStatus);}
    }  
}