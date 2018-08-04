package main.network.signal;

import main.network.signal.Signal;
import main.network.signal.Transponder;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransponderTest {
    private static final String HZ = "3Hz";
    private static final String HZ_23 = "23000Hz";
    private Transponder transponder;


    @Before
    public void setUp(){
        Signal signalMock = mockSignal();
        transponder = new Transponder(signalMock);
    }

    private Signal mockSignal(){
        Signal signalMock = EasyMock.createMock(Signal.class);
        EasyMock.expect(signalMock.getFrequency()).andReturn(HZ_23).once();
        signalMock.setFrequency(HZ);
        EasyMock.expectLastCall();
        EasyMock.expect(signalMock.getFrequency()).andReturn(HZ).once();

        EasyMock.replay(signalMock);

        return signalMock;
    }

    @Test
    public void test01(){
        String result = transponder.emit();
        Assert.assertEquals(HZ_23, result);
    }

    @Test
    public void test02(){
        String result = transponder.emit();
        Assert.assertEquals(HZ_23, result);

        transponder.changeSignal(HZ);
        result = transponder.emit();

        Assert.assertEquals(HZ, result);
    }


}