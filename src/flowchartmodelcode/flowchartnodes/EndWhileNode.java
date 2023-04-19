package flowchartmodelcode.flowchartnodes;

/**
 * Represents an end while node, corresponding to the end of the contents of a while node
 */
public class EndWhileNode extends EndNode{

    @Override
    public String output() {
        return super.output();
    }

    @Override
    public String getFlowchartText() {
        return this.flowChartText = "END WHILE";
    }
}

