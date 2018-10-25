package restcontrol;

import main.network.station.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class RouteRestControl {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    RouteRepository routeRepository;

    @RequestMapping("/route")
    public Route getRoute(){
        return routeRepository.findAll().get(0);
    }



    @RequestMapping("/addRoute")
    public ResponseEntity<Object> addRouteToDataBase(@RequestBody RouteDomain routeDomain){

        routeRepository.save(routeDomain);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/addRoute").build().toUri();

        //Build ResponseItem
        return ResponseEntity.created(location).build();
    }

    @RequestMapping("/simRoute")
    public ResponseEntity<Object> simulateRoute(){
        RouteDomain routeDomain = new RouteDomain();
        routeDomain.setRouteName("S6");
        List<StationDomain> stationsInDateBase = stationRepository.findAll();
        routeDomain.setStations(stationsInDateBase);
        routeDomain.setStartStation(stationsInDateBase.get(0));
        routeDomain.setEndStation(stationsInDateBase.get(1));


        routeRepository.save(routeDomain);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/simRoute").build().toUri();

        //Build ResponseItem
        return ResponseEntity.created(location).build();
    }
}
