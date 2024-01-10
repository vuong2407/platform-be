package vn.whatsenglish.orchestrator.workflow.order;

import vn.whatsenglish.orchestrator.workflow.common.WorkflowStep;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStepStatus;

public class PaymentStep implements WorkflowStep {

    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(Ud)

    @Override
    public WorkflowStepStatus getStatus() {
        return stepStatus;
    }

    @Override
    public boolean process() {
        return false;
    }

    @Override
    public boolean revert() {
        return false;
    }
}
