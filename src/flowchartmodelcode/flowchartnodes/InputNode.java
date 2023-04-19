package flowchartmodelcode.flowchartnodes;

/**
 * Represents a input node
 */
public class InputNode extends Node {

    private String type;
    private String name;
    private String prompt;

    /**
     * Creates an input node
     *
     * @param type   The data type of the input
     * @param name   The name of the variable that stores the input
     * @param prompt The text displayed on the console asking the user for input
     */
    public InputNode(String type, String name, String prompt) {
        this.type = type;
        this.name = name;
        this.prompt = prompt;
    }

    @Override
    public String output() {
        if (type == "Whole number") {
            return name + " = int(raw_input(\"" + prompt + "\")) \n";
        } else if (type == "Decimal number") {
            return name + " = float(raw_input(\"" + prompt + "\")) \n";
        }
        return name + " = raw_input(\"" + prompt + "\") \n"; // text and boolean values
    }


    @Override
    public String getFlowchartText() {
        return type + " INPUT " + name + " ASK " + prompt;
    }

    /**
     * Gets type
     *
     * @return value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type
     *
     * @param type the value for type to be set to
     */
    public void setType(String type) {
        this.type = type;
    }

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
     * Gets prompt
     *
     * @return value of prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Sets prompt
     *
     * @param prompt the value for prompt to be set to
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
