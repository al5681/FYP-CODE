package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents an else node
 */
public class ElseNode extends ParentNode  {

    /**
     * Constructor which simply calls the Node constructor
     * 'instanceOf' is used to determine when a node is an else node
     */
    public ElseNode() {
        super();
    }

    @Override
    public String output() {
        return "else:" + "\n";
    }

    @Override
    public String getFlowchartText() {
        return "ELSE";
    }
}
