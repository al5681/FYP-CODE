package flowchartmodelcode.flowchartnodes;

/**
 * Represents a stop node, the end of the flowchart
 */
public class StopNode extends Node {

    @Override
    public String output() {
        return super.output();
    }

    @Override
    public String getFlowchartText() {
        return this.flowChartText = "Stop";
    }
}
