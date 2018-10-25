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
    private Long id;
    private String routeName;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StationDomain> stations = new ArrayList<>();

    @OneToOne(targetEntity = StationDomain.class)
    private StationDomain startStation;

    @OneToOne(targetEntity = StationDomain.class)
    private StationDomain endStation;

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

    public StationDomain getStartStation() {
        return startStation;
    }

    public void setStartStation(StationDomain startStation) {
        this.startStation = startStation;
    }

    public StationDomain getEndStation() {
        return endStation;
    }

    public void setEndStation(StationDomain endStation) {
        this.endStation = endStation;
    }
}
