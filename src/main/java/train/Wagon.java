package train;

import passanger.Passanger;

import java.util.List;

public interface Wagon {
    public void addPassanger(Passanger passanger);
    //Passangers, who leaves the Train so change Station  or reached their goal
    public List<Passanger> removePassanger(int amount);
}
