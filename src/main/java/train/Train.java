package train;

import station.Station;

import java.util.List;

public interface Train {
    //Allows us to change the Tran length in case of higher usage
    public void initTrain(List<Wagon> wagon);

    public void setDestination(Station station);
    public Station getDestination();

    //
    public void setActualStation();
    public Station getActualStation();

    //allows us to scale in an ongoing operation
    public void removeWagon(int amount);
    public void addWagon(Wagon wagon);

    //for later analysis, how long the train needed to reach his goal
    public void addDrivingTime(int time);
    public void getDrivingTime();

}
