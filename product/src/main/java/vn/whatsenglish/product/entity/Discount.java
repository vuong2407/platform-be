package vn.whatsenglish.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import vn.whatsenglish.domain.dto.product.request.CreateDiscountRequestDto;
=======
import vn.whatsenglish.CreateDiscountRequestDto;
>>>>>>> ffaa65bf6dfa6cbc555bf84c838586cfba1938e1

import java.util.ArrayList;
import java.util.List;

@Entity(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_discount")
    private String nameDiscount;

    @Column(name = "description", columnDefinition="Text")
    private String description;

    @Column(name = "code_discount")
    private String codeDiscount;

    @Column(name = "percentage_discount")
    private Integer percentageDiscount;

    @Column(name = "fixed_discount")
    private Float fixedDiscount;

    @ManyToOne
    @JoinColumn(name = "discount_category_id", nullable = false)
    private DiscountCategory discountCategory;

    @ManyToMany(mappedBy = "discounts")
    private List<Product> products = new ArrayList<>();

    public static Discount ofDto(CreateDiscountRequestDto dto) {
        return Discount.builder()
                .nameDiscount(dto.getNameDiscount())
                .description(dto.getDescription())
                .codeDiscount(dto.getCodeDiscount())
                .percentageDiscount(dto.getPercentageDiscount())
                .fixedDiscount(dto.getFixedDiscount())
                .build();
    }
}
