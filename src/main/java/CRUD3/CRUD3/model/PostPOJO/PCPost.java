package CRUD3.CRUD3.model.PostPOJO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class PCPost extends AbstractPost {
    Collection<String> ramSize;
    Collection<String> ramType;
    Collection<Integer> cores;
    double minFreq;
    double maxFreq;


    public PCPost(Collection<String> ramSize, Collection<String> ramType, Collection<Integer> cores, int minFreq, int maxFreq, BigDecimal maxPrice, BigDecimal minPrice,String type) {
        super(type,maxPrice,minPrice);
        this.ramSize = ramSize;
        this.ramType = ramType;
        this.cores = cores;
        this.minFreq = minFreq;
        this.maxFreq = maxFreq;
    }

    public PCPost(){}

    public Collection<String> getRamSize() {
        return ramSize;
    }

    public void setRamSize(Collection<String> ramSize) {
        this.ramSize = ramSize;
    }

    public Collection<String> getRamType() {
        return ramType;
    }

    public void setRamType(Collection<String> ramType) {
        this.ramType = ramType;
    }

    public Collection<Integer> getCores() {
        return cores;
    }

    public void setCores(Collection<Integer> cores) {
        this.cores = cores;
    }

    public double getMinFreq() {
        return minFreq;
    }

    public void setMinFreq(double minFreq) {
        this.minFreq = minFreq;
    }

    public double getMaxFreq() {
        return maxFreq;
    }

    public void setMaxFreq(double maxFreq) {
        this.maxFreq = maxFreq;
    }
}
