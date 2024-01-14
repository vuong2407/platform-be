package vn.whatsenglish.orchestrator.workflow.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import vn.whatsenglish.domain.dto.product.request.DeductProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.DeductProductResponseDto;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.orchestrator.commons.ResponseTemplate;
import vn.whatsenglish.orchestrator.retrofit.ServiceProduct;
import vn.whatsenglish.orchestrator.utils.ValidateResponseRetrofit;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStep;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStepStatus;

import java.io.IOException;

public class ProductStep implements WorkflowStep {
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;
    private final ServiceProduct serviceProduct;
    private final DeductProductRequestDto request;

    public ProductStep(ServiceProduct serviceProduct, DeductProductRequestDto request) {
        this.request = request;
        this.serviceProduct = serviceProduct;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return stepStatus;
    }

    @Override
    public boolean process() {
        try {
            Response<DeductProductResponseDto> response = serviceProduct.deductProduct(request).execute();
            ResponseTemplate responseTemplate = ValidateResponseRetrofit.validate(response);
            if (!(responseTemplate.getCode() == 200)) {
                stepStatus = WorkflowStepStatus.FAILED;
                System.out.println("product step: fail process");
                return false;
            } else {
                assert response.body() != null;
                if (response.body().getStatus().equals(OrderStatus.REJECT)) {
                    stepStatus = WorkflowStepStatus.FAILED;
                    return false;
                }
            }
            stepStatus = WorkflowStepStatus.COMPLETE;
            System.out.println("product step: success process");
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("product step: fail process");
            return false;
        }
    }

    @Override
    public boolean revert() {
        try {
            Response<Object> response = serviceProduct.revertDeductingProduct(request).execute();
            ResponseTemplate responseTemplate = ValidateResponseRetrofit.validate(response);
            System.out.println("product step: success revert");
            return responseTemplate.getCode() == 200;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("product step: fail revert");
            return false;
        }
    }
}
