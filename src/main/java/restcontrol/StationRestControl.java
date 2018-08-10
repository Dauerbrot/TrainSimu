package restcontrol;
import main.network.station.StationDomain;
import main.network.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StationRestControl {

    @Autowired
    StationRepository stationRepository;


    @RequestMapping("/station")
    public List<StationDomain> getStations(){
        return stationRepository.findAll();
    }

    @RequestMapping("/addStation")
    public ResponseEntity<Object> addStation(@RequestBody List<StationDomain> stations){
        for(StationDomain station: stations){
           if(stationRepository.findStationByName(station.getName()) == null){
               stationRepository.save(station);
           }

        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/station").build().toUri();

        //Build ResponseItem
        return ResponseEntity.created(location).build();
    }


}
