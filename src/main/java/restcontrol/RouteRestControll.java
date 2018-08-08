package restcontrol;

import main.network.station.Route;
import main.network.station.StationImpl;
import main.network.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteRestControll {

    @Autowired
    StationRepository stationRepository;

    @RequestMapping("/route")
    public Route getRoute(){
        StationImpl station = new StationImpl();
        station.setId(100001L);
        station.setName("Loewemstrasse");
        station.setPositionX(4);
        station.setPositionZ(-4);
        stationRepository.save(station);

        return null;
    }
}
