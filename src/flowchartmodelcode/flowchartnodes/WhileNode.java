package flowchartmodelcode.flowchartnodes;

/**
 * Represents a while (loop) node
 */
public class WhileNode extends ParentNode {
    private String condition;

    /**
     * Creates a while node
     *
     * @param condition The condition of the while loop
     */
    public WhileNode(String condition) {
        super();
        this.condition = condition;
    }

    @Override
    public String output() {
        return "while " + condition + ":" + "\n";
    }

    @Override
    public String getFlowchartText() {
        return "WHILE " + condition;
    }

    /**
     * Gets condition
     *
     * @return value of condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets condition
     *
     * @param condition the value for condition to be set to
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
}
