package main.network.station;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StationImpl {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String line;

    public StationImpl(){}

}
