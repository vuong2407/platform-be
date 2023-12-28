package vn.whatsenglish.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "product_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategory {

    public final static int SOFTWARE_TYPE = 0;

    public final static int HARDWARE_TYPE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int code;

    @Column
    private String description;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
}
