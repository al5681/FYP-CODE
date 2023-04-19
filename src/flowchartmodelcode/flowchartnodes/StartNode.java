package flowchartmodelcode.flowchartnodes;

/**
 * Represents a start node, the beginning of the flowchart
 */
public class StartNode extends Node {

    @Override
    public String output() {
        return super.output();
    }

    @Override
    public String getFlowchartText() {
        return this.flowChartText = "Start";
    }
}
