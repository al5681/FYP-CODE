package flowchartmodelcode.flowchartnodes;

/**
 * Represents a if node
 */
public class IfNode extends ParentNode {
    private String condition;

    /**
     * Creates an if node
     *
     * @param condition The condition for the if statement
     */
    public IfNode(String condition) {
        super();
        this.condition = condition;
    }

    @Override
    public String output() {
        return "if " + condition + ":" + "\n";
    }

    @Override
    public String getFlowchartText() {
        return "IF " + condition;
    }

    /**
     * Gets the condition value
     *
     * @return condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the value of condition
     *
     * @param condition the value for condition to take
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
}
