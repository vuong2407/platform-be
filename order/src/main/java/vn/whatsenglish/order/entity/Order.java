package vn.whatsenglish.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "orders")
@Builder
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "shipping_address_id")
    private Integer shippingAddressId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "order_to_item",
            joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @Column(name = "status")
    private int orderStatus;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name = "deleted_date")
    @LastModifiedDate
    private Date deletedDate;

}
