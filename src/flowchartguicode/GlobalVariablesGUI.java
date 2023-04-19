package flowchartguicode;

import flowchartguicode.InsertViews.FlowchartGUIVariableNodeInsert;
import flowchartmodelcode.GlobalVariables;
import flowchartmodelcode.flowchartnodes.VariableNode;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Represents a View/Controller for the GlobalVariables class
 */
public class GlobalVariablesGUI {

    private BorderPane variablesPane = new BorderPane();
    private VBox variablesBox = new VBox();
    private GlobalVariables globalVariablesList = new GlobalVariables();
    private Button addNewGlobalVariableButton = new Button("Add new global variable");
    ArrayList<Button> variableButtons = new ArrayList<>();

    /**
     * Sets up the View/Controller
     */
    public void setUp() {
        // set up global variables pane
        variablesPaneEvents();
        variablesPane.setStyle("-fx-border-color: #D3D3D3");
        variablesPane.setTop(new Label("Global variables:"));
        variablesPane.setCenter(variablesBox);
        addNewGlobalVariableButton.getStyleClass().add("add-variable-button");
        variablesPane.setBottom(addNewGlobalVariableButton);
    }

    // sets up the events
    private void variablesPaneEvents() {
        addNewGlobalVariableButton.setOnMouseClicked(e -> {
            displayVariableNodeInsert();
        });
    }

    // renders the variables list as a list of buttons in the GUI
    private void renderGlobalVariables() {
        variablesBox.getChildren().clear(); // clear the variables box
        for (int i = 0; i < globalVariablesList.getGlobalVariables().size(); i++) { // rerender all the variable nodes
            String parsedStr = globalVariablesList.getGlobalVariables().get(i).getFlowchartText().replaceAll("(.{20})", "$1\n");
            Button variableButton = new Button(parsedStr);
            variableButton.getStyleClass().add("variable-button");
            variablesBox.getChildren().add(variableButton);
            variableButtons.add(variableButton);
            enableButtonEvents(variableButton, i);
        }
    }

    // sets up the context menu options for a button in the list
    private void enableButtonEvents(Button variableButton, int i) {
        // create a context menu for each button, which is displayed on a right-click
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(new MenuItem("Edit"));
        contextMenu.getItems().add(new MenuItem("Delete"));
        variableButton.setContextMenu(contextMenu);
        // get the button
        variableButton.setOnMouseClicked(e -> {
            // get the item in the context menu clicked on
            variableButton.getContextMenu().getItems().get(0).setOnAction(g -> {
                // edit is clicked on, depending on the type of node the right edit method is called
                // editNode(variableButton, finalI);
                VariableNode node = globalVariablesList.getGlobalVariables().get(i);
                displayEditVariableNode(node.getName(), node.getValue(), i);
            });
            // delete is clicked on and the node is removed from the chart
            variableButton.getContextMenu().getItems().get(1).setOnAction(f ->
            {
                // deleteNodeCaller(finalI);
                deleteVariable(variableButton, i);
            });
        });
    }

    // deletes a variable from the list
    private void deleteVariable(Button variableButton, int i) {
        variableButtons.remove(variableButton);
        globalVariablesList.getGlobalVariables().remove(i);
        renderGlobalVariables();
    }

    // interface for editing a variable
    private void displayEditVariableNode(String name, String value, int finalI) {
        FlowchartGUIVariableNodeInsert flowchartGUIVariableNodeInsert = new FlowchartGUIVariableNodeInsert(true);
        flowchartGUIVariableNodeInsert.setNameTextField(name);
        flowchartGUIVariableNodeInsert.setValueTextField(value);
        flowchartGUIVariableNodeInsert.display();
        flowchartGUIVariableNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            String textForName = flowchartGUIVariableNodeInsert.getNameTextField().getText();
            String textOfValue = flowchartGUIVariableNodeInsert.getValueTextField().getText();
            VariableNode node = (VariableNode) globalVariablesList.getGlobalVariables().get(finalI);
            if (textForName.matches("(?i)[a-z][a-z0-9_]*") && textForName.length() < 79 && textOfValue.length() < 4000) {
                node.setName(textForName);
                node.setValue(textOfValue);
                flowchartGUIVariableNodeInsert.getPrimaryStage().close();
                renderGlobalVariables();
            } else if (!(textForName.matches("(?i)[a-z][a-z0-9_]*") && textForName.length() > 79)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid variable name");
                alert.setHeaderText("Your variable name is invalid!");
                alert.setContentText("Make sure your variable name corresponds to the following rules:\n" +
                        "- It does not start with a number or special character\n" +
                        "- It is not over 400 characters long\n" +
                        "- It is not blank\n" +
                        "- It contains no spaces");
                alert.showAndWait();
            }
            if (textOfValue.length() > 4000) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid variable value");
                alert.setHeaderText("Your variable value is invalid!");
                alert.setContentText("Make it is not over 4000 characters long");
                alert.showAndWait();
            }
        });
    }

    // interface for adding a variable
    private void displayVariableNodeInsert() {
        FlowchartGUIVariableNodeInsert flowchartGUIVariableNodeInsert = new FlowchartGUIVariableNodeInsert(false);
        flowchartGUIVariableNodeInsert.display();
        flowchartGUIVariableNodeInsert.getCreateButton().setOnMouseClicked(e -> {
            String textForName = flowchartGUIVariableNodeInsert.getNameTextField().getText();
            String textOfValue = flowchartGUIVariableNodeInsert.getValueTextField().getText();
            if (textForName.matches("(?i)[a-z][a-z0-9_]*") && textForName.length() < 79 && textOfValue.length() < 4000) {
                globalVariablesList.getGlobalVariables().add(new VariableNode(textForName, textOfValue));
                flowchartGUIVariableNodeInsert.getPrimaryStage().close();
            } else if (!(textForName.matches("(?i)[a-z][a-z0-9_]*") && textForName.length() > 79)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid variable name");
                alert.setHeaderText("Your variable name is invalid!");
                alert.setContentText("Make sure your variable name corresponds to the following rules:\n" +
                        "- It does not start with a number or special character\n" +
                        "- It is not over 79 characters long\n" +
                        "- It is not blank\n" +
                        "- It contains no spaces");
                alert.showAndWait();
            }
            if (textOfValue.length() > 4000) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid variable value");
                alert.setHeaderText("Your variable value is invalid!");
                alert.setContentText("Make it is not over 4000 characters long");
                alert.showAndWait();
            }
            renderGlobalVariables();
        });
    }

    /**
     * Gets variablesPane
     *
     * @return value of variablesPane
     */
    public BorderPane getVariablesPane() {
        return variablesPane;
    }

    /**
     * Gets globalVariables
     *
     * @return value of globalVariables
     */
    public GlobalVariables getGlobalVariablesList() {
        return globalVariablesList;
    }

}
