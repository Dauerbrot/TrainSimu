package main.network;

import main.network.station.Station;
import main.network.train.Train;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrainNetworkImpl implements TrainNetwork {

    private Map<Station, Set<String>> stationsInNetwork;
    private Map<String, Train> trainsInNetwork;

    public TrainNetworkImpl() {
        stationsInNetwork = new HashMap<>();
        trainsInNetwork = new HashMap<>();

    }

    @Override
    public void addStationToNetwork(Station station) {
        if (station != null && station.linesInStation() != null && !station.linesInStation().isEmpty()) {
            stationsInNetwork.put(station, station.linesInStation());
        }
    }

    @Override
    public Set<Station> getStations() {
        return new HashSet<>(stationsInNetwork.keySet());
    }


    @Override
    public Map<Station, Set<String>> getAllStationsInNetwork() {
        return stationsInNetwork;
    }

    @Override
    public void addTrainToNetwork(Train train) {
        if (train != null && train.getLineName() != null && !train.getLineName().isEmpty()) {
            trainsInNetwork.put(train.getLineName(), train);
        }
    }

    @Override
    public Set<Train> getTrains() {
        return new HashSet<>(trainsInNetwork.values());
    }

    @Override
    public Map<String, Train> getAllTrainsInNetwork() {
        return trainsInNetwork;
    }
}
