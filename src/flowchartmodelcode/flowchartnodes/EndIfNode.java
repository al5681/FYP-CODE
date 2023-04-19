package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents an end if node, corresponding to the end of the contents of an if node
 */
public class EndIfNode extends EndNode {

    @Override
    public String output() {
        return super.output();
    }

    @Override
    public String getFlowchartText() {
        return this.flowChartText = "END IF";
    }
}
