package network.station;

import java.util.Set;

/**
 * A Station can have multiple lines
 * A Station can only Hold a finite number of Passanger
 * A Station have a Name
 * A Station is connected to one or multiple other Stations
 * A Station have 2 more Lines (at the Beginning)
 */
public interface Station {
    public String getStationName();
    //Set of all Station which are connected in a way to the station
    public Set<Station> getNextStations();
    //Set of all lines, which runs through the station
    public Set<String> linesInStation();

}
