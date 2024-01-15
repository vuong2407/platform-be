package vn.whatsenglish.orchestrator.service;

import vn.whatsenglish.domain.message.PlacingOrderMessage;
import vn.whatsenglish.domain.message.PlacingOrderMessageResponse;
import vn.whatsenglish.orchestrator.entity.OrderEvent;

public interface IOrderEvent {
    OrderEvent createOrderEvent(PlacingOrderMessageResponse placingOrderMessageResponse);
}
