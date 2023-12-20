package vn.whatsenglish.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String displayName;
    private float price;
    private Float displayPrice;
    private String description;
    private String thumbnail;
}
