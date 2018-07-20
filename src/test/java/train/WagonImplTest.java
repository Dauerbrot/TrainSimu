package train;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import passanger.Passanger;

import java.util.Map;


public class WagonImplTest {

    public static final long ID = 0L;
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
        long id = ID;
        fillWagonWithPassangers(destination, otherDestiantions, id);
        Map<Long,Passanger> result = wagon.removePassanger(realDestination);
        Assert.assertTrue(result.isEmpty());


    }


    @Test
    public void leavingPassangerByDestination() {
        //Given
        String destination = "Alexanderplatz";
        String otherDestiantions = "Louis-Lewin-Straße";
        long id = ID;
        fillWagonWithPassangers(destination, otherDestiantions, id);
        //when
        Map<Long,Passanger> leavingPassangers =  wagon.removePassanger(destination);

        Assert.assertEquals(4,leavingPassangers.size());
        for(Map.Entry<Long,Passanger> result : leavingPassangers.entrySet()){
            Assert.assertEquals(destination,result.getValue().getWorkPlaceStation());
        }
    }


    private void fillWagonWithPassangers(String destination, String otherDestiantions, long id) {
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