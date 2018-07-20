package station;

import java.util.Set;

public interface Station {
    public String getStationName();
    public Set<Station> getNextStations();
}
