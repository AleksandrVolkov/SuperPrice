package CRUD3.CRUD3.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProductCountKey implements Serializable {

    @Column(name = "product_id")
    Long productId;

    @Column(name = "id")
    Long orderId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}