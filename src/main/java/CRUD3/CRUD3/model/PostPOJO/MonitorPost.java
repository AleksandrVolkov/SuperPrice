package CRUD3.CRUD3.model.PostPOJO;

import lombok.Data;

import java.util.Collection;

@Data
public class MonitorPost extends AbstractPost {
    private double minScreen;
    private double maxScreen;
    private int minScreenFrequency;
    private int maxScreenFrequency;
    private Collection<String> matrixType;
    private Collection<String> screenResolution;

    public double getMinScreen() {
        return minScreen;
    }

    public void setMinScreen(double minScreen) {
        this.minScreen = minScreen;
    }

    public double getMaxScreen() {
        return maxScreen;
    }

    public void setMaxScreen(double maxScreen) {
        this.maxScreen = maxScreen;
    }

    public int getMinScreenFrequency() {
        return minScreenFrequency;
    }

    public void setMinScreenFrequency(int minScreenFrequency) {
        this.minScreenFrequency = minScreenFrequency;
    }

    public int getMaxScreenFrequency() {
        return maxScreenFrequency;
    }

    public void setMaxScreenFrequency(int maxScreenFrequency) {
        this.maxScreenFrequency = maxScreenFrequency;
    }

    public Collection<String> getMatrixType() {
        return matrixType;
    }

    public void setMatrixType(Collection<String> matrixType) {
        this.matrixType = matrixType;
    }

    public Collection<String> getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(Collection<String> screenResolution) {
        this.screenResolution = screenResolution;
    }
}
