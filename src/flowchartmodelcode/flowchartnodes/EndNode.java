package flowchartmodelcode.flowchartnodes;

/**
 * Represents an end node, which helps control the indentation of the nodes in the chart
 * which is required for when the chart is complied to python code
 */
public class EndNode extends Node {
    protected Node parent;

    /**
     * Returns the parent of an end node
     *
     * @return Parent of end node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent of an end node
     *
     * @param parent Node to be made parent of end node
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
