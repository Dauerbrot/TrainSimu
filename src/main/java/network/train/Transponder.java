package network.train;

public class Transponder {
    private Signal signal;
    public Transponder(Signal signal) {
        this.signal = signal;
    }

    public String emit() {
        return signal.getFrequency();
    }

    public void changeSignal(String frequency) {
        signal.setFrequency(frequency);
    }
}
