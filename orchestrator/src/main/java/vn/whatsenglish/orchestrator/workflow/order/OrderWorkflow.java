package vn.whatsenglish.orchestrator.workflow.order;

import vn.whatsenglish.orchestrator.workflow.common.Workflow;
import vn.whatsenglish.orchestrator.workflow.common.WorkflowStep;

import java.util.List;

public class OrderWorkflow implements Workflow {

    private final List<WorkflowStep> steps;

    public OrderWorkflow(List<WorkflowStep> steps) {
        this.steps = steps;
    }
    
    @Override
    public List<WorkflowStep> getSteps() {
        return this.steps;
    }
}
