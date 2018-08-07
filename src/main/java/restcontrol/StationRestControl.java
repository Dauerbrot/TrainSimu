package restcontrol;
import main.network.station.StationImpl;
import main.network.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationRestControl {

    @Autowired
    StationRepository stationRepository;

    @RequestMapping("/station")
    public List<StationImpl> getStations(){

        return stationRepository.findAll();

    }

}
