package main.network.train;

import main.network.station.Station;

import java.util.List;

public interface Train {

    public String getLineName();
    public Station getDestination();

    public Station getStart();

    public List<Station> getRoute();

    public void driveToNextStationOnRoute();

    //Actual Position on the Route
    public void setActualStation(Station station);
    public Station getActualStation();

    //allows us to scale in an ongoing operation
    public void removeWagon(int amount);

    public void addWagon(Wagon wagon);

    public int getWagonStatus();

    //for later analysis, how long the train needed to reach his goal
    public void addDrivingTime(int time);

    public int getDrivingTime();

}
