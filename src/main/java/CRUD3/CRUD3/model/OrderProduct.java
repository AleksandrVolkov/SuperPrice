package CRUD3.CRUD3.model;

import CRUD3.CRUD3.model.tovarmodel.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderProduct {

    @JsonIgnore
    @EmbeddedId
    ProductCountKey id;


    @ManyToOne( cascade = CascadeType.ALL)
    @JsonIgnore
    @MapsId("id")
    @JoinColumn(name = "id")
    MyOrder order;


    @ManyToOne( cascade = CascadeType.ALL)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;

    int count;

    @Override
    public int hashCode(){
        return this.product.hashCode();
    }
}
