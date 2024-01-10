package vn.whatsenglish.orchestrator.workflow.common;

public interface WorkflowStep {
    WorkflowStepStatus getStatus();
    boolean process();
    boolean revert();
}
