package train;

import java.util.List;

public class Signal {

    private String frequency;
    private String waveLength;
    private List<String> frequencyRange;

    public Signal(String frequency, String waveLength, List<String> frequencyRange) {

        this.frequency = frequency;
        this.waveLength = waveLength;
        this.frequencyRange = frequencyRange;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getWaveLength() {
        return waveLength;
    }

    public void setWaveLength(String waveLength) {
        this.waveLength = waveLength;
    }

    public List<String> getFrequencyRange() {
        return frequencyRange;
    }

    public void setFrequencyRange(List<String> frequencyRange) {
        this.frequencyRange = frequencyRange;
    }
}
