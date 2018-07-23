package network.train;

import network.train.Wagon;
import network.train.WagonImpl;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import passanger.Passanger;

import java.util.Map;


public class WagonImplTest {

    private static final long ID = 0L;
    private Wagon wagon;

    private Passanger mockPassanger(Long id, String destination) {
        Passanger passanger = EasyMock.createMock(Passanger.class);
        EasyMock.expect(passanger.getId()).andReturn(id).anyTimes();
        EasyMock.expect(passanger.getWorkPlaceStation()).andReturn(destination).anyTimes();
        EasyMock.replay(passanger);
        return passanger;
    }

    @Before
    public void setUp() {
        wagon = new WagonImpl(40);
    }

    @Test
    public void trainWagonIsNotFull() {
        Assert.assertFalse(wagon.isWagonFull());
    }

    @Test
    public void addNullPassanger() {
        Passanger result = wagon.addPassanger(null);
        Assert.assertNull(result);
    }


    @Test
    public void addPassenger() {
        Passanger passanger = mockPassanger(ID, "workplace");
        Passanger result = wagon.addPassanger(passanger);
        Assert.assertFalse(wagon.isWagonFull());
        Assert.assertNull(result);
    }

    @Test
    public void addPassangerByFullTrain() {
        //Given
        Long id = ID;
        while (!wagon.isWagonFull()) {
            Passanger passanger = mockPassanger(id, "workplace");
            wagon.addPassanger(passanger);
            id++;
        }

        //when
        id++;
        Passanger passangerFullWagon = mockPassanger(id, "workplace");
        Passanger result = wagon.addPassanger(passangerFullWagon);
        Long resultId = result.getId();
        //then
        Assert.assertNotNull(result);
        Assert.assertEquals(id, resultId);
    }

    @Test
    public void leavingByinvalidStationName(){
        Map<Long, Passanger> leaving = wagon.removePassanger(null);
        Assert.assertTrue(leaving.isEmpty());
    }

    @Test
    public void LeavingPassangerByEmptyWagon(){
        Map<Long, Passanger> leaving = wagon.removePassanger("Wuhletal");
        Assert.assertTrue(leaving.isEmpty());
    }

    @Test
    public void noPassangerAtStation(){
        //Given
        String realDestination = "Wuhletal";
        String destination = "Friedrichsstraße";
        String otherDestiantions = "Tierpark";
        fillWagonWithPassangers(destination, otherDestiantions);
        Map<Long,Passanger> result = wagon.removePassanger(realDestination);
        Assert.assertTrue(result.isEmpty());


    }


    @Test
    public void leavingPassangerByDestination() {
        //Given
        String destination = "Alexanderplatz";
        String otherDestiantions = "Louis-Lewin-Straße";
        fillWagonWithPassangers(destination, otherDestiantions);
        //when

        Assert.assertTrue(wagon.isWagonFull());
        Map<Long,Passanger> leavingPassangers =  wagon.removePassanger(destination);
        //then
        Assert.assertFalse(wagon.isWagonFull());
        Assert.assertEquals(4,leavingPassangers.size());
        //ever passanger have the same destination and not a single one leaves the wrong position
        for(Map.Entry<Long,Passanger> result : leavingPassangers.entrySet()){
            Assert.assertEquals(destination,result.getValue().getWorkPlaceStation());
        }
    }


    private void fillWagonWithPassangers(String destination, String otherDestiantions) {
        Long id = ID;

        while (!wagon.isWagonFull()) {
            Passanger passangerSimulation;
            if (id % 10 == 0) {
                passangerSimulation = mockPassanger(id, destination);
            } else {
                passangerSimulation = mockPassanger(id, otherDestiantions);
            }
            wagon.addPassanger(passangerSimulation);
            id++;
        }
    }
}