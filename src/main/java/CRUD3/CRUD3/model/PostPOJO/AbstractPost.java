package CRUD3.CRUD3.model.PostPOJO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type( value = PCPost.class, name = "pcPost"),
        @JsonSubTypes.Type( value = MonitorPost.class, name = "monitorPost"),
        @JsonSubTypes.Type( value = PrinterPost.class, name = "printerPost")
})
public class AbstractPost {

    private String type;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;

    AbstractPost(){}

    public AbstractPost(String type, BigDecimal maxPrice, BigDecimal minPrice) {
        this.type = type;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
}
