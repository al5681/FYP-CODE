package flowchartmodelcode.flowchartnodes;

import java.io.Serializable;

/**
 * Represents a node, an object that represents some component of the flowchart whose contents can be
 * complied into suitable Python code to be executed by an interpreter
 */
public class Node implements Serializable {
    protected String flowChartText;
    protected int noOfParents = 0;
    protected int levelOfIndentation = 0;

    /**
     * Returns the output of a node, this is the corresponding Python code for the object
     * which is generated when the 'outputPythonCode()' method is called in the Flowchart class
     *
     * @return the python code output of a node
     */
    public String output() {
        return "";
    }

    /**
     * Returns the contents of the node that are displayed in the flowchart, for example an output node may return
     * 'OUTPUT "Hello World"
     *
     * @return the text contents of a node that are displayed in the GUI
     */
    public String getFlowchartText() {
        return "";
    }

    /**
     * Gets flowChartText
     *
     * @return value of flowChartText
     */
    public String getFlowChartText() {
        return flowChartText;
    }

    /**
     * Sets flowChartText
     *
     * @param flowChartText the value for flowChartText to be set to
     */
    public void setFlowChartText(String flowChartText) {
        this.flowChartText = flowChartText;
    }

    /**
     * Gets noOfParents
     *
     * @return value of noOfParents
     */
    public int getNoOfParents() {
        return noOfParents;
    }

    /**
     * Sets noOfParents
     *
     * @param noOfParents the value for noOfParents to be set to
     */
    public void setNoOfParents(int noOfParents) {
        this.noOfParents = noOfParents;
    }

    /**
     * Gets levelOfIndentation
     *
     * @return value of levelOfIndentation
     */
    public int getLevelOfIndentation() {
        return levelOfIndentation;
    }

    /**
     * Sets levelOfIndentation
     *
     * @param levelOfIndentation the value for levelOfIndentation to be set to
     */
    public void setLevelOfIndentation(int levelOfIndentation) {
        this.levelOfIndentation = levelOfIndentation;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}