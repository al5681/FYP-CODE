package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents an end for node, corresponding to the end of the contents of a for node
 */
public class EndForNode extends EndNode{

    @Override
    public String output() {
        return super.output();
    }

    @Override
    public String getFlowchartText() {
        return this.flowChartText = "END FOR";
    }
}
