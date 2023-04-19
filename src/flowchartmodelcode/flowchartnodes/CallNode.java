package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents an function call node
 */
public class CallNode extends Node  {
    private String functionName;

    /**
     * Creates a call node, that represents a function call
     *
     * @param text The signature of the function
     */
    public CallNode(String text) {
        super();
        this.functionName = text;
    }

    @Override
    public String output() {
        return functionName + "\n";
    }

    @Override
    public String getFlowchartText() {
        return "CALL " + functionName;
    }

    /**
     * Gets text
     *
     * @return value of text
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Sets text
     *
     * @param functionName the value for text to be set to
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}