package main.network.train;

import main.network.train.Train;
import main.network.train.TrainImpl;
import main.network.train.Wagon;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.network.station.Station;

import java.util.*;

public class TrainImplTest {

    private Train train;
    private Station startStation;
    private Station destinationStation;

    private Wagon mockWagon() {
        Wagon mockWagon = EasyMock.createMock(Wagon.class);
        EasyMock.replay(mockWagon);
        return mockWagon;
    }

    private Station mockStation(String stationName, Station nextStation) {
        Station mockStation = EasyMock.createMock(Station.class);
        EasyMock.expect(mockStation.getStationName()).andReturn(stationName).anyTimes();
        EasyMock.expect(mockStation.getNextStations()).andReturn(Collections.singleton(nextStation));
        EasyMock.replay(mockStation);
        return mockStation;
    }

    @Before
    public void setUp() {
        List<Wagon> wagons = new ArrayList<>();

        destinationStation = mockStation("Wuhletal", null);
        startStation = mockStation("Hellersdorf", destinationStation);

        train = new TrainImpl("S5", wagons, startStation, destinationStation);
    }

    @Test
    public void getDestination() {
        Assert.assertEquals(destinationStation, train.getDestination());
    }

    @Test
    public void getStart() {
        Assert.assertEquals(startStation, train.getStart());
    }

    @Test
    public void getRoute() {
        List<Station> result = train.getRoute();
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getLineName(){
        Assert.assertEquals("S5", train.getLineName());
    }

    @Test
    public void startOfTravel() {
        Station result = train.getActualStation();
        Assert.assertEquals(startStation, result);
    }

    @Test
    public void addNullWagon() {
        //given
        Assert.assertEquals(0, train.getWagonStatus());
        //when
        train.addWagon(null);
        //then
        Assert.assertEquals(0, train.getWagonStatus());
    }

    @Test
    public void addWagon() {
        //given
        Assert.assertEquals(0, train.getWagonStatus());
        //when
        Wagon wagon = mockWagon();
        train.addWagon(wagon);
        //then
        Assert.assertEquals(1, train.getWagonStatus());
    }


    @Test
    public void removeWagonFromEmptyTrain() {
        //given
        Assert.assertEquals(0, train.getWagonStatus());
        //when
        train.removeWagon(1);
        //then
        Assert.assertEquals(0, train.getWagonStatus());
    }

    @Test
    public void removeOneWagonFromTrain() {
        //given
        int amountOfWagons = 4;
        fillTrainWithWagons(amountOfWagons);
        Assert.assertEquals(4, train.getWagonStatus());
        //when
        train.removeWagon(1);
        //then
        Assert.assertEquals(3, train.getWagonStatus());
    }

    @Test
    public void removeMoreWagonsAsTrainHave() {
        //given
        int amountOfWagons = 200;
        fillTrainWithWagons(amountOfWagons);
        Assert.assertEquals(200, train.getWagonStatus());
        //then
        train.removeWagon(201);
        //when
        Assert.assertEquals(0, train.getWagonStatus());

    }

    private void fillTrainWithWagons(int amountOfWagons) {
        for (int i = 0; i < amountOfWagons; i++) {
            train.addWagon(mockWagon());
        }
    }

    @Test
    public void addDrivingTime() {
        Assert.assertEquals(0, train.getDrivingTime());
        train.addDrivingTime(20);
        Assert.assertEquals(20, train.getDrivingTime());
    }

    @Test
    public void addNegativeDrivingTime() {
        Assert.assertEquals(0, train.getDrivingTime());
        train.addDrivingTime(-20);
        Assert.assertEquals(0, train.getDrivingTime());
    }

    @Test
    public void setNextStationOnRoute(){
        //given
        List<Station> route = new LinkedList<>();
        Station station03 = mockStation("Hellersdorf", destinationStation);
        Station station02 = mockStation("Hellersdorf", station03);
        Station station01 = mockStation("Hellersdorf", station02);
        Station startNewStation = mockStation("Hellersdorf", station01);
        route.add(startNewStation);
        route.add(station01);
        route.add(station02);
        route.add(station03);
        route.add(destinationStation);

        Train train2 = new TrainImpl("S5", new ArrayList<>(),startNewStation,destinationStation, route);

        //when
        Assert.assertEquals(startNewStation, train2.getActualStation());
        train2.setActualStation(station01);
        //then
        Assert.assertEquals(station01, train2.getActualStation());

    }

    @Test
    public void driveThroughTheRoute(){
        //given
        List<Station> route = new LinkedList<>();
        Station station03 = mockStation("Hellersdorf", destinationStation);
        Station station02 = mockStation("Hellersdorf", station03);
        Station station01 = mockStation("Hellersdorf", station02);
        Station startNewStation = mockStation("Hellersdorf", station01);
        route.add(startNewStation);
        route.add(station01);
        route.add(station02);
        route.add(station03);
        route.add(destinationStation);

        Train train2 = new TrainImpl("S5", new ArrayList<>(),startNewStation,destinationStation, route);
        //when
        Assert.assertEquals(startNewStation, train2.getActualStation());
        train2.driveToNextStationOnRoute();
        //then
        Assert.assertEquals(station01, train2.getActualStation());
    }

    @Test
    public void driveToLastStation(){
        //given
        List<Station> route = new LinkedList<>();
        Station station03 = mockStation("Hellersdorf", destinationStation);
        Station station02 = mockStation("Hellersdorf", station03);
        Station station01 = mockStation("Hellersdorf", station02);
        Station startNewStation = mockStation("Hellersdorf", station01);
        route.add(startNewStation);
        route.add(station01);
        route.add(station02);
        route.add(station03);
        route.add(destinationStation);

        Train train2 = new TrainImpl("S5", new ArrayList<>(),startNewStation,destinationStation, route);
        //when
        Assert.assertEquals(startNewStation, train2.getActualStation());
        train2.driveToNextStationOnRoute();
        //then
        Assert.assertEquals(station01, train2.getActualStation());
        train2.driveToNextStationOnRoute();
        //then
        Assert.assertEquals(station02, train2.getActualStation());
        train2.driveToNextStationOnRoute();
        //then
        Assert.assertEquals(station03, train2.getActualStation());
        train2.driveToNextStationOnRoute();
        //then
        Assert.assertEquals(destinationStation, train2.getActualStation());
        train2.driveToNextStationOnRoute();
        //then
        Assert.assertEquals(destinationStation, train2.getActualStation());
    }

    //Design by contract
    @Test(expected = NullPointerException.class)
    public void initTrainWithNullStart() {
        train= new TrainImpl("S5", new ArrayList<>(), null, destinationStation);
    }

    @Test(expected = NullPointerException.class)
    public void initTrainWithNullDestination() {
        train = new TrainImpl("S5", new ArrayList<>(), startStation, null);
    }

    @Test(expected = NullPointerException.class)
    public void initTrainWithNullStartAndDestination() {
        train = new TrainImpl("S5", new ArrayList<>(), null, null);
    }


}