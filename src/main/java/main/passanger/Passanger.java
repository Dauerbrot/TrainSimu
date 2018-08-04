package main.passanger;

public interface Passanger {
    //Normal Mind of a Passanger, who wants to reach his workplace
    public void initPassanger(String homeStation, String workplaceStation);

    public void setId(long id);
    public long getId();

    public String getHomeStation();
    public String getWorkPlaceStation();

}
