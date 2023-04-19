package flowchartmodelcode;

import flowchartmodelcode.flowchartnodes.VariableNode;

import java.util.ArrayList;

/**
 * Represents a list of global variables for a program
 */
public class GlobalVariables {
    private ArrayList<VariableNode> globalVariables;

    public GlobalVariables() {
        this.globalVariables = new ArrayList<>();
    }

    /**
     * Gets globalVariables
     *
     * @return value of globalVariables
     */
    public ArrayList<VariableNode> getGlobalVariables() {
        return globalVariables;
    }
}
