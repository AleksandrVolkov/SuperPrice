package CRUD3.CRUD3.model.tovarmodel;



import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
@Entity
@Table(name = "MONITOR_DATA")
public class Monitor extends Product {
    private Double screen;
    private String screenResolution;
    private Integer screenFrequency;
    private String aspectRatio;
    private String brightness;
    private String responseTime;
    private String connector;
    private String matrixType;


    public Monitor() {
    }

    public Monitor(Long id_product, String product_type, String shop, String short_image, String name, String short_description, String link_on_full_description, BigDecimal price) {
        super(id_product, product_type, shop, short_image, name, short_description, link_on_full_description, price);
    }


//    public Monitor(String product_type, String shop, String short_image, String name, String short_description, String link_on_full_description, BigDecimal price,
//                   Double screen, String screen_resolution, Integer screen_frequency, String aspect_ratio, String brightness, String response_time, String connector, String matrix_type) {
//        super(product_type, shop, short_image, name, short_description, link_on_full_description, price);
//        this.screen = screen;
//        this.screen_resolution = screen_resolution;
//        this.screen_frequency = screen_frequency;
//        this.aspect_ratio = aspect_ratio;
//        this.brightness = brightness;
//        this.response_time = response_time;
//        this.connector = connector;
//        this.matrix_type = matrix_type;
//    }

    public Integer getScreenFrequency() {
        return screenFrequency;
    }

    public void setScreenFrequency(Integer screenFrequency) {
        this.screenFrequency = screenFrequency;
    }

    public Double getScreen() {
        return screen;
    }

    public void setScreen(Double screen) {
        this.screen = screen;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getMatrixType() {
        return matrixType;
    }

    public void setMatrixType(String matrixType) {
        this.matrixType = matrixType;
    }
}
