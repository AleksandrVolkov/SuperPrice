package CRUD3.CRUD3.model.tovarmodel;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PRINTER_DATA")
@Data
public class Printer extends Product {
    private String type;
    private String color;
    private String format;
    private Double maxPrintSpeed;
    private String connector;
    private String CISS;
    private String twoSidedPrinting;

    public Printer() {
    }


    public Printer(Long id_product, String product_type, String shop, String short_image, String name, String short_description, String link_on_full_description, BigDecimal price) {
        super(id_product, product_type, shop, short_image, name, short_description, link_on_full_description, price);
    }


//    public Printer(String product_type, String shop, String short_image, String name, String short_description, String link_on_full_description, BigDecimal price,
//                   String type, String color, String format, Integer max_print_speed, String connector, String CISS, String two_sided_printing) {
//        super(product_type, shop, short_image, name, short_description, link_on_full_description, price);
//        this.type = type;
//        this.color = color;
//        this.format = format;
//        this.max_print_speed = max_print_speed;
//        this.connector = connector;
//        this.CISS = CISS;
//        this.two_sided_printing = two_sided_printing;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Double getMaxPrintSpeed() {
        return maxPrintSpeed;
    }

    public void setMaxPrintSpeed(Double maxPrintSpeed) {
        this.maxPrintSpeed = maxPrintSpeed;
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

    public String getTwoSidedPrinting() {
        return twoSidedPrinting;
    }

    public void setTwoSidedPrinting(String twoSidedPrinting) {
        this.twoSidedPrinting = twoSidedPrinting;
    }
}