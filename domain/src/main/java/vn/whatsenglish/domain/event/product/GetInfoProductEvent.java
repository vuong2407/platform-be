package vn.whatsenglish.domain.event.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.domain.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetInfoProductEvent extends BaseEvent {
    private Integer id;
}
