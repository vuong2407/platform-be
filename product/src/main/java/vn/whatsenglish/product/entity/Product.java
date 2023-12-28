package vn.whatsenglish.product.entity;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "price")
    private float price;

    @Column(name = "description", columnDefinition="Text")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "available_item")
    private Integer availableItem;

    @Column(name = "reserved_item")
    private Integer reservedItem;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_to_discount",
            joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "discount_id"))
    private List<Discount> discounts = new ArrayList<>();

    public static Product ofDto(CreateProductRequestDto dto) {
        return Product.builder()
                .displayName(dto.getDisplayName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .thumbnail(dto.getThumbnail())
                .build();
    }

}
