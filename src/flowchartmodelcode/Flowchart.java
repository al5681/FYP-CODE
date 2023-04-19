package flowchartmodelcode;

import flowchartmodelcode.flowchartnodes.*;

import java.io.*;
import java.util.LinkedList;

/**
 * Represents a Flowchart, consisting of a start node and stop node and the nodes in-between added by the user
 * stored in a linked list
 */
public class Flowchart implements Serializable {

    private String name; // will correspond to the name of the function that chart represents
    private LinkedList<Node> myList;
    private StartNode startNode;
    private StopNode stopNode;

    /**
     * Creates a new flowchart, represented as a linked list with a start node and a stop node
     */
    public Flowchart() {
        myList = new LinkedList<>();
        myList.addFirst(startNode = new StartNode());
        myList.addLast(stopNode = new StopNode());
    }

    /**
     * Creates a new flowchart, represented as a linked list with a start node and a stop node
     *
     * @param name, the name of the Flowchart which acts as a function signature
     */
    public Flowchart(String name) {
        this.name = name;
        myList = new LinkedList<>();
        myList.addFirst(startNode = new StartNode());
        myList.addLast(stopNode = new StopNode());
    }

    /**
     * Adds an output node to the flowchart
     *
     * @param text  The text that will be outputted to the console
     * @param index The position of the node in the list
     */
    public void addOutputNode(String text, int index) {
        OutputNode outputNode = new OutputNode(text);
        myList.add(index, outputNode);
        setParents(outputNode, index);
    }

    /**
     * Adds a variable node to the flowchart
     *
     * @param name  The name of the variable
     * @param value The value of the variable
     * @param index The position of the node in the list
     */
    public void addVariableNode(String name, String value, int index) {
        VariableNode variableNode = new VariableNode(name, value);
        myList.add(index, variableNode);
        setParents(variableNode, index);
    }

    /**
     * Adds an input node to the flowchart
     *
     * @param type   The data type of the input
     * @param name   The name of the variable that stores the input
     * @param prompt The text displayed on the console asking the user for input
     * @param index  The position of the node in the list
     */
    public void addInputNode(String type, String name, String prompt, int index) {
        InputNode inputNode = new InputNode(type, name, prompt);
        myList.add(index, inputNode);
        setParents(inputNode, index);
    }

    /**
     * Adds an if node to the flowchart
     *
     * @param condition The condition for the if statement
     * @param index     The position of the node in the list
     */
    public void addIfNode(String condition, int index) {
        IfNode ifNode = new IfNode(condition);
        EndIfNode endIfNode = new EndIfNode();
        myList.add(index, ifNode);
        myList.add(index + 1, endIfNode);
        setParents(ifNode, index);
        setParents(endIfNode, index + 1);
        endIfNode.setParent(ifNode);
    }

    /**
     * Adds and else node to the flowchart
     *
     * @param index The position of the node in the list
     */
    public void addElseNode(int index) {
        ElseNode elseNode = new ElseNode();
        EndElseNode endElseNode = new EndElseNode();
        myList.add(index, elseNode);
        myList.add(index + 1, endElseNode);
        setParents(elseNode, index);
        setParents(endElseNode, index + 1);
        endElseNode.setParent(elseNode);
    }

    /**
     * Adds an else if node to the flowchart
     *
     * @param condition The condition of the else if statement
     * @param index     The position of the node in the list
     */
    public void addElseIfNode(String condition, int index) {
        ElseIfNode elseIfNode = new ElseIfNode(condition);
        EndElseIfNode endElseIfNode = new EndElseIfNode();
        myList.add(index, elseIfNode);
        myList.add(index + 1, endElseIfNode);
        setParents(elseIfNode, index);
        setParents(endElseIfNode, index + 1);
        endElseIfNode.setParent(elseIfNode);
    }

    /**
     * Adds a while node to the flowchart
     *
     * @param condition The condition of the while loop
     * @param index     The position of the node in the list
     */
    public void addWhileNode(String condition, int index) {
        WhileNode whileNode = new WhileNode(condition);
        EndWhileNode endWhileNode = new EndWhileNode();
        myList.add(index, whileNode);
        myList.add(index + 1, endWhileNode);
        setParents(whileNode, index);
        setParents(endWhileNode, index + 1);
        endWhileNode.setParent(whileNode);
    }

    /**
     * Adds a for node to the flowchart
     *
     * @param indexer    The variable for iterating through the for loop
     * @param from       The starting int value of the for loop
     * @param to         The terminating int value of the for loop
     * @param toOperator The operator for the for loop can be '=' or '<'
     * @param index      The position of the node in the list
     */
    public void addForNode(String indexer, String from, String to, String toOperator, int index) {
        if (toOperator.equals("=")) {
            int incrementTo = Integer.valueOf(to);
            incrementTo++;
            to = String.valueOf(incrementTo);
        }
        ForNode forNode = new ForNode(indexer, from, to, toOperator);
        EndForNode endForNode = new EndForNode();
        myList.add(index, forNode);
        myList.add(index + 1, endForNode);
        setParents(forNode, index);
        setParents(endForNode, index + 1);
        endForNode.setParent(forNode);
    }

    /**
     * Adds a call node to the flowchart
     *
     * @param functionName The name of the function to call
     * @param index        The position of the node in the list
     */
    public void addCallNode(String functionName, int index) {
        CallNode callNode = new CallNode(functionName);
        myList.add(index, callNode);
        setParents(callNode, index);
    }

    /**
     * Adds a return node to the flowchart
     *
     * @param returnValue The value to be returned
     * @param index       The position of the node in the list
     */
    public void addReturnNode(String returnValue, int index) {
        ReturnNode returnNode = new ReturnNode(returnValue);
        myList.add(index, returnNode);
        setParents(returnNode, index);
    }

    /**
     * Sets the list of parents to the right value for a new node added to the list, so that its
     * level of indentation can be set to the right value
     *
     * @param node  The node that needs to have its parents assigned
     * @param index The position of the node in the list
     */
    private void setParents(Node node, int index) {
        if (myList.get(index - 1) instanceof ParentNode && myList.get(index - 1).getNoOfParents() > 0) {
            node.setNoOfParents(myList.get(index - 1).getNoOfParents());
            node.setNoOfParents(node.getNoOfParents() + 1);
        } else if (myList.get(index - 1) instanceof ParentNode) {
            node.setNoOfParents(node.getNoOfParents() + 1);
        } else if (myList.get(index - 1) instanceof EndNode) {
            node.setNoOfParents(myList.get(index - 1).getNoOfParents());
            node.setNoOfParents(node.getNoOfParents() - 1);
        } else if (myList.get(index - 1).getNoOfParents() > 0 && !(myList.get(index - 1) instanceof EndNode)) {
            node.setNoOfParents(myList.get(index - 1).getNoOfParents());
        }
        node.setLevelOfIndentation(node.getNoOfParents() * 4);
    }

    /**
     * Takes the position (index) of some node in the list and removes it from the list
     * NB: this method needs to be updated so that if a parent node is deleted its children
     * are also deleted!
     *
     * @param index The position of some node in the list to be deleted
     */
    public void deleteNode(int index) {
        Node currentNode = getMyList().get(index);
        if (currentNode instanceof StartNode || currentNode instanceof StopNode) {
            // do nothing
        } else {
            getMyList().remove(currentNode);
            // needs to be made so that all child nodes are deleted
        }
    }

    /**
     * Traverses the list and outputs a python program, that can then be passed to
     * a python interpreter to run the code
     *
     * @return A String that is a Python program corresponding to the chart the user has produced
     */
    public String outputPythonCode() {
        String output = "";
        for (int i = 0; i < getMyList().size(); i++) {
            if (getMyList().get(i).output() != "") { // if the output of a node is not empty, add it to the code
                String indentation = (myList.get(i).getLevelOfIndentation() == 0) ? "" : String.format("%" + myList.get(i).getLevelOfIndentation() + "s", "");
                output += indentation + myList.get(i).output();
            }
        }
        return output;
    }

    /**
     * Traverses the list and outputs a python function, that can then be passed to
     * a python interpreter to run the code
     *
     * @return A String that is a Python function corresponding to the chart the user has produced
     */
    public String outputPythonFunction() {
        String output = "";
        output += "def " + name + ":\n"; // going to ignore parameters for now, ADD LATER
        if (getMyList().size() == 2) { // if only the start node and stop node are in the list
            String indentation = "    ";
            output += indentation + "pass";
        } else {
            for (int i = 0; i < getMyList().size(); i++) {
                if (getMyList().get(i).output() != "") { // if the output of a node is not empty, add it to the code
                    String indentation = "    ";
                    indentation += (myList.get(i).getLevelOfIndentation() == 0) ? "" : String.format("%" + myList.get(i).getLevelOfIndentation() + "s", "");
                    output += indentation + myList.get(i).output();
                }
            }
        }
        output += "\n";
        return output;
    }

    /**
     * Getter method for the list
     *
     * @return The list representing the flowchart
     */
    public LinkedList<Node> getMyList() {
        return myList;
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
     * Used to write flowchart objects to a .bin file
     *
     * @param fileName
     * @throws IOException
     */
    public void writeObjectToFile(String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            oos.flush();
        }
    }

    /**
     * Used to read flowchart objects that are stored in a .bin file
     *
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Flowchart readObjectFromFile(File file) throws IOException, ClassNotFoundException {
        Object result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = ois.readObject();
        }
        return (Flowchart) result;
    }

    /**
     * Saves the code to a .py file, right now only saves to a single file name
     * it would be better to refine this to ask the user for a file name
     *
     * @param fileContents
     * @throws FileNotFoundException
     */
    public void savePythonFile(String fileContents) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("filename.py")) {
            out.println(fileContents);
        }
    }
}
