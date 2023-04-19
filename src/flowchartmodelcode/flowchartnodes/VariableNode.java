package flowchartmodelcode.flowchartnodes;

/**
 * Represents a variable node
 */
public class VariableNode extends Node{
    private String name;
    private String value;

    /**
     * Creates a variable node
     * @param name  The name of the variable
     * @param value The value of the variable
     */
    public VariableNode(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    @Override
    public String output() { return name + " = " + value + "\n"; }

    @Override
    public String getFlowchartText() {return "LET " + name + " = " + value;}

    /**
     * Gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     *
     * @param name the value for name to be set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets value
     *
     * @return value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value
     *
     * @param value the value for value to be set to
     */
    public void setValue(String value) {
        this.value = value;
    }
}
