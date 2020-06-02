package CRUD3.CRUD3.model.PostPOJO;

//import javafx.print.Collation;

import lombok.Data;

import java.util.Collection;

//import org.checkerframework.checker.units.qual.A;

@Data
public class PrinterPost extends AbstractPost {

    private Collection<String> printerType;
    private Collection <String> color;
    private Collection<String> format;
    private double maxSpeed;
    private double minSpeed;
    private Collection<String> twoSidedPrinting;

    private String connector;
    private String CISS;

    public Collection<String> getPrinterType() {
        return printerType;
    }

    public void setPrinterType(Collection<String> printerType) {
        this.printerType = printerType;
    }

    public Collection<String> getColor() {
        return color;
    }

    public void setColor(Collection<String> color) {
        this.color = color;
    }

    public Collection<String> getFormat() {
        return format;
    }

    public void setFormat(Collection<String> format) {
        this.format = format;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Collection<String> getTwoSidedPrinting() {
        return twoSidedPrinting;
    }

    public void setTwoSidedPrinting(Collection<String> twoSidedPrinting) {
        this.twoSidedPrinting = twoSidedPrinting;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getCISS() {
        return CISS;
    }

    public void setCISS(String CISS) {
        this.CISS = CISS;
    }
}
