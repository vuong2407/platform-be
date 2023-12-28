package vn.whatsenglish.domain.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoCategoryOfProductDto {
    private Long id;
    private String name;
    private int code;
    private String description;
}
