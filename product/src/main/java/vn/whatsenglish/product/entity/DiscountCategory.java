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

@Entity(name = "discount_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCategory {

    public static final int FIXED_DISCOUNT = 1;
    public static final int PERCENTAGE_DISCOUNT = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition="Text")
    private String description;

    @OneToMany(mappedBy = "discountCategory")
    private List<Discount> discounts;
}
