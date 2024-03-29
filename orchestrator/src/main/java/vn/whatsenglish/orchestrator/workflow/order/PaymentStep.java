package vn.whatsenglish.orchestrator.workflow.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import vn.whatsenglish.domain.dto.payment.request.PaymentRequestDto;
import vn.whatsenglish.domain.dto.payment.response.PaymentResponseDto;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.orchestrator.commons.ResponseTemplate;
import vn.whatsenglish.orchestrator.retrofit.ServiceUser;
import vn.whatsenglish.orchestrator.utils.ValidateResponseRetrofit;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStep;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStepStatus;

import java.io.IOException;

public class PaymentStep implements WorkflowStep {

    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;
    private final ServiceUser serviceUser;
    private final PaymentRequestDto request;

    public PaymentStep(ServiceUser serviceUser, PaymentRequestDto request) {
        this.serviceUser = serviceUser;
        this.request = request;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return stepStatus;
    }

    @Override
    public boolean process() {
        try {
            Response<PaymentResponseDto> response = serviceUser.deductPayment(request).execute();
            ResponseTemplate responseTemplate = ValidateResponseRetrofit.validate(response);
            if (!(responseTemplate.getCode() == 200)) {
                stepStatus = WorkflowStepStatus.FAILED;
                System.out.println("payment step: fail process");
                return false;
            } else {
                assert response.body() != null;
                if (response.body().getStatus().equals(OrderStatus.REJECT)) {
                    stepStatus = WorkflowStepStatus.FAILED;
                    return false;
                }
            }
            stepStatus = WorkflowStepStatus.COMPLETE;
            System.out.println("payment step: success process");
            return true;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("payment step: fail process");
            return false;
        }
    }

    @Override
    public boolean revert() {
        try {
            Response<Object> response = serviceUser.revertDeductingPayment(request).execute();
            ResponseTemplate responseTemplate = ValidateResponseRetrofit.validate(response);
            System.out.println("payment step: success revert");
            return responseTemplate.getCode() == 200;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("payment step: fail revert");
            return false;
        }
    }
}
