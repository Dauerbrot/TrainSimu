package network;

import network.station.Station;
import network.train.Train;

import java.util.Map;
import java.util.Set;

/**
 * First its just a holder of all Informations, which a later needed.
 */
public interface TrainNetwork {
    public void addStationToNetwork(Station station);
    public Set<Station> getStations();
    public Map<Station,Set<String>> getAllStationsInNetwork();

    public void addTrainToNetwork(Train train);
    public Set<Train> getTrains();
    public Map<String, Train> getAllTrainsInNetwork();
}
