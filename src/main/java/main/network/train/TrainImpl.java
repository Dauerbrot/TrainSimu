package main.network.train;

import main.network.station.Station;

import java.util.*;

public class TrainImpl implements Train {
    private String lineName;
    private static final int POSITION_NEXT_STATION = 1;
    private List<Wagon> wagons;
    private Station destinationStation;
    private Station startStation;
    private Station actualStation;
    private List<Station> route;
    private int drivingTime;


    public TrainImpl(String lineName, List<Wagon> wagons, Station startStation, Station destinationStation) {
        this(lineName, wagons, startStation, destinationStation, null);
    }

    public TrainImpl(String lineName, List<Wagon> wagons, Station startStation, Station destinationStation, List<Station> route) {
        //invalid start or destination is not allowed to be initialized
        if (startStation == null || destinationStation == null) {
            throw new NullPointerException();
        }
        this.lineName = lineName;
        this.startStation = startStation;
        this.actualStation = startStation;
        this.destinationStation = destinationStation;
        drivingTime = 0;

        if (wagons != null) {
            this.wagons = wagons;
        } else {
            this.wagons = new ArrayList<>();
        }


        if (route == null) {
            calculateRoute();
        } else {
            this.route = route;
        }
    }

    private void calculateRoute() {
        route = new LinkedList<>();
        //add the start of the station
        route.add(startStation);
        //First Iteration Start next is destination Station
        if (startStation.getNextStations().contains(destinationStation)) {
            route.add(destinationStation);
        }
    }

    @Override
    public String getLineName() {
        return lineName;
    }

    @Override
    public Station getDestination() {
        return destinationStation;
    }

    @Override
    public Station getStart() {
        return startStation;
    }

    @Override
    public List<Station> getRoute() {
        return route;
    }

    @Override
    public void driveToNextStationOnRoute() {
        int position = route.indexOf(actualStation) + POSITION_NEXT_STATION;
        //we´re at the last station
        if(position >= route.size()){
            return;
        }
        setActualStation(route.get(position));
    }

    @Override
    public void setActualStation(Station station) {
        actualStation = station;
    }

    @Override
    public Station getActualStation() {
        return actualStation;
    }

    @Override
    public void removeWagon(int amount) {
        int removeWagonsFromTrain = amount;

        if (amount > wagons.size()) {
            removeWagonsFromTrain = wagons.size();
        }

        for (int i = 0; i < removeWagonsFromTrain; i++) {
            //remove always the first wagon from Train
            wagons.remove(0);
        }
    }

    @Override
    public void addWagon(Wagon wagon) {
        if (wagon != null) {
            wagons.add(wagon);
        }
    }

    @Override
    public int getWagonStatus() {
        return wagons.size();
    }

    @Override
    public void addDrivingTime(int time) {
        if (time < 0) {
            return;
        }
        drivingTime += time;
    }

    @Override
    public int getDrivingTime() {
        return drivingTime;
    }
}
