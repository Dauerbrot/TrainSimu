package main.network.station;

import java.util.Map;
import java.util.Set;

/**
 * Route consists of Stations
 * A Route can be calculated in here and delegates the route to the trains
 */
public interface Route {
    public Station getNextStation(Station actualStation);
    public Set<Station> getRoute();
    public Map<String,Set<Station>> getSpecificRoute(String nameOfLine);
}
