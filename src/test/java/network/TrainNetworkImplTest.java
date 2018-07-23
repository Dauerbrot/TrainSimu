package network;

import network.station.Station;
import network.train.Train;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TrainNetworkImplTest {

    private TrainNetwork trainNetwork;
    private Set<String> line01;
    private Set<String> line02;

    @Before
    public void setUp() {
        String[] lines = {"U5", "U3"};
        line01 = new HashSet<>(Arrays.asList(lines));

        String[] linesTheSecond = {"S1", "S3"};
        line02 = new HashSet<>(Arrays.asList(linesTheSecond));
        trainNetwork = new TrainNetworkImpl();
    }

    private Station mockStation(String stationName, Set<String> lines) {
        Station stationMock = EasyMock.createMock(Station.class);
        EasyMock.expect(stationMock.getStationName()).andReturn(stationName).anyTimes();
        EasyMock.expect(stationMock.linesInStation()).andReturn(lines).anyTimes();
        EasyMock.replay(stationMock);
        return stationMock;
    }

    private Train mockTrain(String trainName) {
        Train trainMock = EasyMock.createMock(Train.class);
        EasyMock.expect(trainMock.getLineName()).andReturn(trainName).anyTimes();
        EasyMock.replay(trainMock);

        return trainMock;
    }

    @Test
    public void getStationList() {
        Set<Station> stationsInNetwork = trainNetwork.getStations();
        Assert.assertEquals(0, stationsInNetwork.size());
    }

    @Test
    public void addStation() {
        Set<Station> stationsInNetwork = trainNetwork.getStations();
        Assert.assertEquals(0, stationsInNetwork.size());
        trainNetwork.addStationToNetwork(mockStation("MehringDamm", line01));
        stationsInNetwork = trainNetwork.getStations();
        Assert.assertEquals(1, stationsInNetwork.size());
        trainNetwork.addStationToNetwork(mockStation("Frankfurter Allee", line02));
        stationsInNetwork = trainNetwork.getStations();
        Assert.assertEquals(2, stationsInNetwork.size());
    }

    @Test
    public void addStationWithMissingLinesInformation() {
        Station stationMock = mockStation("Wuhletal", null);
        trainNetwork.addStationToNetwork(stationMock);
        Set<Station> stationsInNetwork = trainNetwork.getStations();
        Assert.assertEquals(0, stationsInNetwork.size());
    }

    @Test
    public void addStationWithEmptyLinesInformations() {
        Station stationMock = mockStation("Wuhletal", new HashSet<>());
        trainNetwork.addStationToNetwork(stationMock);
        Set<Station> stationsInNetwork = trainNetwork.getStations();
        Assert.assertEquals(0, stationsInNetwork.size());

    }

    @Test
    public void addStationWithMissingStation() {
        trainNetwork.addStationToNetwork(null);
        Set<Station> stationsInNetwork = trainNetwork.getStations();
        Assert.assertEquals(0, stationsInNetwork.size());
    }

    @Test
    public void addTrainToNetwork() {
        //given
        Set<Train> trains = trainNetwork.getTrains();
        Assert.assertEquals(0, trains.size());

        Train train = mockTrain("U8");
        //when
        trainNetwork.addTrainToNetwork(train);

        //then
        trains = trainNetwork.getTrains();
        Assert.assertEquals(1, trains.size());
    }


    @Test
    public void addTrainWithMissingLineToNetwork() {
        //given
        Set<Train> trains = trainNetwork.getTrains();
        Assert.assertEquals(0, trains.size());

        Train train = mockTrain("");
        //when
        trainNetwork.addTrainToNetwork(train);

        //then
        trains = trainNetwork.getTrains();
        Assert.assertEquals(0, trains.size());
    }

    @Test
    public void addNullTrainToNetwork() {
        //given
        Set<Train> trains = trainNetwork.getTrains();
        Assert.assertEquals(0, trains.size());

        //when
        trainNetwork.addTrainToNetwork(null);

        //then
        trains = trainNetwork.getTrains();
        Assert.assertEquals(0, trains.size());
    }

    @Test
    public void testGetAllTrainsInNetwork() {
        List<Train> trains = new ArrayList<>();
        trains.add(mockTrain("U8"));
        trains.add(mockTrain("U9"));
        trains.add(mockTrain("U10"));
        trains.add(mockTrain("S8"));
        trains.add(mockTrain("S5"));
        for (Train train : trains) {
            trainNetwork.addTrainToNetwork(train);
        }
        Map<String, Train> trainsInNetwork = trainNetwork.getAllTrainsInNetwork();

        for (Train train : trains) {
            Assert.assertTrue(trainsInNetwork.containsValue(train));
            if(trainsInNetwork.containsValue(train)){
                trainsInNetwork.remove(train.getLineName());
            }
        }
        //Every registred train from the original list was in the list. if a train remains after the
        //check, then went something wrong
        Assert.assertEquals(0,trainsInNetwork.size());
    }

    @Test
    public void testGetAllStations(){
        List<Station> stations = new ArrayList<>();
        stations.add(mockStation("Wuhletal", line01));
        stations.add(mockStation("Mehring Damm", line01));
        stations.add(mockStation("Ostkreuz", line02));
        stations.add(mockStation("Treptower Park", line02));

        for(Station station : stations){
            trainNetwork.addStationToNetwork(station);
        }

        Map<Station, Set<String>> stationNetwork = trainNetwork.getAllStationsInNetwork();

        for(Station station: stations){
            Assert.assertTrue(stationNetwork.containsKey(station));
            stationNetwork.remove(station);
        }

        Assert.assertEquals(0, stationNetwork.size());
    }

}