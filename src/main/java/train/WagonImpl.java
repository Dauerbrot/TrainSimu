package train;

import passanger.Passanger;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WagonImpl implements Wagon {
    private Map<Long, Passanger> passangersInWagon;
    //Train have always a limited space
    private int size;

    public WagonImpl(int size) {
        passangersInWagon = new LinkedHashMap<>();
        this.size = size;
    }

    @Override
    public Passanger addPassanger(Passanger passanger) {
        if (passanger != null && passangersInWagon.size() < size) {
            passangersInWagon.put(passanger.getId(), passanger);
            //Passanger is successfully boarded
            return null;
        }
        //wagon was full and can´t get in the actual Train
        return passanger;
    }

    @Override
    public Map<Long, Passanger> removePassanger(String stationName) {
        if(stationName == null){
            return Collections.emptyMap();
        }
        Map<Long, Passanger> leavingPassangers = new HashMap<>();

        for (Map.Entry<Long, Passanger> passanger : passangersInWagon.entrySet()) {
            if (stationName.equals(passanger.getValue().getWorkPlaceStation())) {
                leavingPassangers.put(passanger.getKey(), passanger.getValue());
            }
        }

        passangersInWagon.keySet().removeAll(leavingPassangers.keySet());

        return leavingPassangers;
    }

    @Override
    public boolean isWagonFull() {
        return passangersInWagon.size() >= size;
    }
}
