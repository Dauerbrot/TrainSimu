package main.network.station;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "route" )
public class RouteDomain implements Route {
    @Id
    @GeneratedValue
    Long id;

    String routeName;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StationDomain> stations = new ArrayList<>();

    public RouteDomain(){}


    @Override
    public Station getNextStation(Station actualStation) {
        return null;
    }

    @Override
    public Set<Station> getRoute() {
        return null;
    }

    @Override
    public Map<String, Set<Station>> getSpecificRoute(String nameOfLine) {
        return null;
    }
    //Getter Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<StationDomain> getStations() {
        return stations;
    }

    public void setStations(List<StationDomain> stations) {
        this.stations = stations;
    }
}
