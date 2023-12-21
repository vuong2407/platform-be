package vn.whatsenglish.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto {

    private Integer id;
    private String name;
    private String code;
    private String description;
}
