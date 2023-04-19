package flowchartmodelcode.flowchartnodes;

/**
 * Represents an output node, which is equivalent to 'print()' in Python
 */
public class OutputNode extends Node {
    private String text;

    /**
     * Creates an output node
     *
     * @param text The text that will be outputted to the console
     */
    public OutputNode(String text) {
        super();
        this.text = text;
    }

    @Override
    public String output() {
        return "print(" + text + ")" + "\n";
    }

    @Override
    public String getFlowchartText() {
        return "OUTPUT " + text;
    }

    /**
     * Gets text
     *
     * @return value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text
     *
     * @param text the value for text to be set to
     */
    public void setText(String text) {
        this.text = text;
    }
}
