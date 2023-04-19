package flowchartmodelcode.flowchartnodes;

/**
 * Represents a return node, which is equivalent to 'return' in Python
 */
public class ReturnNode extends Node {
    private String returnValue;

    /**
     * Creates a return node
     *
     * @param text The text that will be outputted to the console
     */
    public ReturnNode(String text) {
        super();
        this.returnValue = text;
    }

    @Override
    public String output() {
        return "return " + returnValue + " \n";
    }

    @Override
    public String getFlowchartText() {
        return "RETURN " + returnValue;
    }

    /**
     * Gets text
     *
     * @return value of text
     */
    public String getReturnValue() {
        return returnValue;
    }

    /**
     * Sets text
     *
     * @param returnValue the value for text to be set to
     */
    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
