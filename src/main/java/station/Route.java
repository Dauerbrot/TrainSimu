package station;

import java.util.Set;

public interface Route {
    public Station getNextStation(Station actualStation);
    public Set<Station> getRoute();
}
