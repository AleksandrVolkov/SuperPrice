package CRUD3.CRUD3.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal price;

    private LocalDate orderDate;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Collection<OrderProduct> productCounts;
//    @OneToMany(mappedBy = "myOrder", fetch = FetchType.EAGER)
//    private Collection<Product> tenants;


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "ORDER_PRODUCTS",
//            joinColumns = @JoinColumn(name = "ORDER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
//    )
//    private List<Product> products;

//    @OneToMany(mappedBy = "order")
//    Set<OrderProdyct> orderProdycts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }



    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MY_USER_ID")
    private MyUser myUser;

    @Override
    public String toString(){
        return
                "products  products" +"\n"+
                "price  "+ getPrice()+"\n"+
                "userId " + myUser.getId();
    }

    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
}
