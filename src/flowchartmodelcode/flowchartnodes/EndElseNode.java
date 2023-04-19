package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents an end else node, corresponding to the end of the contents of an else node
 */
public class EndElseNode extends EndNode  {

    @Override
    public String output() {
        return super.output();
    }

    @Override
    public String getFlowchartText() {
        return this.flowChartText = "END ELSE";
    }
}
