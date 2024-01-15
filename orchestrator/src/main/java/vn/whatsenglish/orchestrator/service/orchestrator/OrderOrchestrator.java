package vn.whatsenglish.orchestrator.service.orchestrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.whatsenglish.domain.dto.payment.request.PaymentRequestDto;
import vn.whatsenglish.domain.dto.product.request.DeductProductRequestDto;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.PlacingOrderMessage;
import vn.whatsenglish.domain.message.PlacingOrderMessageResponse;
import vn.whatsenglish.orchestrator.retrofit.ServiceProduct;
import vn.whatsenglish.orchestrator.retrofit.ServiceUser;
import vn.whatsenglish.orchestrator.workflow.common.Workflow;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStep;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStepStatus;
import vn.whatsenglish.orchestrator.workflow.order.OrderWorkflow;
import vn.whatsenglish.orchestrator.workflow.order.PaymentStep;
import vn.whatsenglish.orchestrator.workflow.order.ProductStep;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderOrchestrator {

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceProduct serviceProduct;

    public PlacingOrderMessageResponse orderProduct(final PlacingOrderMessage request) {
        PlacingOrderMessageResponse response = PlacingOrderMessageResponse.builder()
                .orderId(request.getOrderId())
                .orderStatus(OrderStatus.CONFIRMED)
                .build();
        Workflow orderWorkflow = generateOrderWorkflow(request);
        List<WorkflowStep> completedStep = new ArrayList<>();
        for (WorkflowStep step : orderWorkflow.getSteps()) {
            if (!step.process()) {
                response.setOrderStatus(OrderStatus.ROLLBACK);
                break;
            }
            completedStep.add(step);
        }
        if (response.getOrderStatus().equals(OrderStatus.ROLLBACK)) {
            completedStep.forEach(step -> {
                if (step.getStatus().equals(WorkflowStepStatus.COMPLETE)) step.revert();
            });
        }
        return response;
    }

    private OrderWorkflow generateOrderWorkflow(PlacingOrderMessage request) {
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .userId(request.getUserId())
                .build();
        DeductProductRequestDto deductProductRequestDto = DeductProductRequestDto.builder()
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .items(request.getItems())
                .build();
        return new OrderWorkflow(List.of(new PaymentStep(serviceUser, paymentRequestDto),
                new ProductStep(serviceProduct, deductProductRequestDto)));
    }

}
