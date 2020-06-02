package CRUD3.CRUD3.model.tovarmodel;

import CRUD3.CRUD3.model.OrderProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = PC.class, name = "PC"),
//        @JsonSubTypes.Type(value = Monitor.class, name = "Monitor")
//})
//@JsonDeserialize(using = MyMapper.class)


@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "product_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PC.class, name = "pc"),
        @JsonSubTypes.Type(value = Monitor.class, name = "monitor"),
        @JsonSubTypes.Type(value = Printer.class, name = "printer")
})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @Id
    private Long product_id;

    @JsonProperty
    private String product_type;

    private String shop;

    @Column(length = 300)
    private String short_image;

    private String name;

    @Column(length = 400)
    private String short_description;

    @Column(length = 400)
    private String link_on_full_description;

    private BigDecimal price;

    private Integer pluslike;

    private Integer dislike;

    @Transient
    private Integer count1 = 1;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Collection<OrderProduct> productCounts;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "products")
//    private Collection<Backet> backets;

    //    @JsonIgnore
//    @ManyToOne( cascade = CascadeType.ALL)
//    @JoinColumn(name = "BACKET_ID")
//    public Backet backet;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "products")
//    private Collection<MyOrder> orders;

//    @OneToMany(mappedBy = "product")
//    Set<OrderProdyct> orderProdycts;

    public Product() {
    }

    public Product(Long product_id, String product_type, String shop, String short_image, String name, String short_description, String link_on_full_description, BigDecimal price) {
        this.product_id = product_id;
        this.product_type = product_type;
        this.shop = shop;
        this.short_image = short_image;
        this.name = name;
        this.short_description = short_description;
        this.link_on_full_description = link_on_full_description;
        this.price = price;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getShort_image() {
        return short_image;
    }

    public void setShort_image(String short_image) {
        this.short_image = short_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLink_on_full_description() {
        return link_on_full_description;
    }

    public void setLink_on_full_description(String link_on_full_description) {
        this.link_on_full_description = link_on_full_description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPluslike() {
        return pluslike;
    }

    public void setPluslike(Integer pluslike) {
        this.pluslike = pluslike;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }
}
