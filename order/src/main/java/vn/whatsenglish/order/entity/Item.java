package vn.whatsenglish.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "items")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price")
    private Float price;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders = new ArrayList<>();
}
