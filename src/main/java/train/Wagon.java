package train;

import passanger.Passanger;

import java.util.Map;

public interface Wagon {
    public Passanger addPassanger(Passanger passanger);
    //Passangers, who leaves the Train so change Station  or reached their goal
    public Map<Long, Passanger> removePassanger(String stationName);

    public boolean isWagonFull();
}
