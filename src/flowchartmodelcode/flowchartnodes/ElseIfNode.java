package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents an else if node
 */
public class ElseIfNode extends ParentNode   {
    private String condition;

    /**
     * Creates an else if node representing and else if statement
     *
     * @param condition the condition(s) of the else if statement
     */
    public ElseIfNode(String condition) {
        super();
        this.condition = condition;
    }

    @Override
    public String output() {
        return "elif " + condition + ":" + "\n";
    }

    @Override
    public String getFlowchartText() {
        return "ELSE IF " + condition;
    }

    /**
     * Getter for the condition
     *
     * @return the condition of the else if node
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition of the else if node
     *
     * @param condition the modified/new condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
}