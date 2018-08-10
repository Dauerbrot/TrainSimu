package restcontrol;

import main.network.station.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteRestControl {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    RouteRepository routeRepository;

    @RequestMapping("/route")
    public Route getRoute(){
        StationDomain station = new StationDomain();
        station.setId(100001L);
        station.setName("Loewemstrasse");
        station.setPositionX(4);
        station.setPositionZ(-4);
        stationRepository.save(station);

        return null;
    }

    @RequestMapping("/simRoute")
    public void simulateRoute(){
        RouteDomain routeDomain = new RouteDomain();
        routeDomain.setRouteName("U9");
        List<StationDomain> stations = stationRepository.findAll();
        routeDomain.setStations(stations);
        routeRepository.save(routeDomain);
    }
}
