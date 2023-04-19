package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents an end else if node, corresponding to the end of the contents of an else if node
 */
public class EndElseIfNode extends EndNode   {

    @Override
    public String output() {
        return super.output();
    }

    @Override
    public String getFlowchartText() {
        return this.flowChartText = "END ELSE IF";
    }
}