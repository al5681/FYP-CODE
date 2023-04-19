package flowchartguicode;

import flowchartguicode.DisplayUtilities.Arrow;
import flowchartguicode.InsertViews.*;
import flowchartmodelcode.Flowchart;
import flowchartmodelcode.flowchartnodes.*;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Represents a view and controller to build a single flowchart
 */
public class FlowchartBuilder {

    private String name;
    private Flowchart flowchart;
    private ArrayList<Button> buttonsInChart = new ArrayList<>();
    private ArrayList<Arrow> linesInChart = new ArrayList<>();
    private Pane flowchartViewAndController = new Pane();


    /**
     * Creates a flowchart builder object
     *
     * @param name the name of the flowchart, which acts as its function signature
     */
    public FlowchartBuilder(String name) {
        this.name = name;
        flowchart = new Flowchart(name);
        // enable scrolling for the chart builder
        this.zoom(this.getFlowchartViewAndController());
        this.renderFlowchartNodes();
        this.renderFlowchartLines();
        // events
        this.enableArrowEvents();
        this.enableButtonEvents();
        // make the chart draggable
        this.setDraggable();
    }

    public FlowchartBuilder() {
        flowchart = new Flowchart();
        // enable scrolling for the chart builder
        this.zoom(this.getFlowchartViewAndController());
        this.renderFlowchartNodes();
        this.renderFlowchartLines();
        // events
        this.enableArrowEvents();
        this.enableButtonEvents();
        // make the chart draggable
        this.setDraggable();
    }


    /**
     * Iterates through all the nodes in a chart and displays them on the view
     */
    public void renderFlowchartNodes() {
        int X = 20;
        int Y = 20;
        ArrayList<Button> updatedButtonsList = new ArrayList<>();
        for (int i = 0; i < flowchart.getMyList().size(); i++) {
            // create a button representing the current node
            Button currButton = new Button();
            currButton.setFont(Font.font("Verdana", 15));
            // add a new line every 50 characters so the shapes don't get too long
            StringBuilder sb = new StringBuilder(flowchart.getMyList().get(i).getFlowchartText());
            int k = 0;
            while ((k = sb.indexOf(" ", k + 50)) != -1) {
                sb.replace(k, k + 1, "\n");
            }
            currButton.setText(sb.toString());
            // set the style of each node
            if (flowchart.getMyList().get(i) instanceof StartNode || flowchart.getMyList().get(i) instanceof StopNode) {
                renderStopAndStartNodes(currButton);
            } else if (flowchart.getMyList().get(i) instanceof OutputNode) {
                renderOutputNode(currButton);
            } else if (flowchart.getMyList().get(i) instanceof InputNode) {
                renderInputNode(currButton);
            } else if (flowchart.getMyList().get(i) instanceof VariableNode) {
                currButton.getStyleClass().add("variable-button");
            } else if (flowchart.getMyList().get(i) instanceof IfNode || flowchart.getMyList().get(i) instanceof EndIfNode
                    || flowchart.getMyList().get(i) instanceof ElseIfNode || flowchart.getMyList().get(i) instanceof EndElseIfNode
                    || flowchart.getMyList().get(i) instanceof ElseNode || flowchart.getMyList().get(i) instanceof EndElseNode) {
                renderConditionNodes(currButton);
            } else if (flowchart.getMyList().get(i) instanceof ForNode || flowchart.getMyList().get(i) instanceof EndForNode
                    || flowchart.getMyList().get(i) instanceof WhileNode || flowchart.getMyList().get(i) instanceof EndWhileNode) {
                renderLoopNode(currButton);
            } else if (flowchart.getMyList().get(i) instanceof CallNode) {
                currButton.getStyleClass().add("call-button");
            } else if (flowchart.getMyList().get(i) instanceof ReturnNode) {
                currButton.getStyleClass().add("return-button");
            }
            // render the indentation position of the nodes
            renderIndentationChart(currButton, X, Y, i);
            String[] split = sb.toString().split(String.valueOf('\n'));
            if (split.length > 1) {
                int scaleFactor = (split.length) + 10;
                Y += (split.length) * scaleFactor + 50;
            } else {
                Y += 50;
            }
            updatedButtonsList.add(currButton);
            flowchartViewAndController.getChildren().add(currButton);
        }
        buttonsInChart = updatedButtonsList; // update the buttons array to represent the new state of the chart
    }

    // the following helper methods render the shape and colour etc. of each node
    private void renderStopAndStartNodes(Button button) {
        button.setShape(new Circle(1));
        button.getStyleClass().add("stop-and-start-button");
    }

    private void renderOutputNode(Button button) {
        SVGPath path = new SVGPath();
        path.setContent("M 0 600 L 100 0 L 4000 0 L 3900 600 L 0 600");
        button.setShape(path);
        button.getStyleClass().add("output-button");
    }

    private void renderInputNode(Button button) {
        SVGPath path = new SVGPath();
        path.setContent("M 0 600 L 100 0 L 4000 0 L 3900 600 L 0 600");
        button.setShape(path);
        button.getStyleClass().add("input-button");
    }

    private void renderConditionNodes(Button button) {
        SVGPath path = new SVGPath();
        path.setContent("M 0 200 L 100 50 L 2200 50 L 2300 200 L 2300 450 L 2200 600 L 150 600 L 0 500 L 0 200   "); // allows for 50 chars per line (original width 1500
        button.setShape(path);
        button.getStyleClass().add("if-button");
    }

    private void renderLoopNode(Button button) {
        SVGPath path = new SVGPath();
        path.setContent("M 0 300 L 100 50 L 2200 50 L 2300 300 L 2200 550 L 100 550 L 0 300 "); // allows for 50 chars per line (original width 1500
        button.setShape(path);
        button.getStyleClass().add("loop-button");
    }

    // set the position of the nodes in the chart according to the indentation approach
    private void renderIndentationChart(Button currButton, int X, int Y, int i) {
        String indentation = (flowchart.getMyList().get(i).getLevelOfIndentation() == 0) ? "" : String.format("%" + flowchart.getMyList().get(i).getLevelOfIndentation() + "s", "");
        int spaces = indentation.replaceAll("[^ ]", "").length();
        if (spaces > 0 && !(flowchart.getMyList().get(i) instanceof EndNode)) {
            currButton.setTranslateX(X + spaces * 5);
        } else if (spaces > 0 && flowchart.getMyList().get(i) instanceof EndNode) {
            currButton.setTranslateX(X + ((EndNode) flowchart.getMyList().get(i)).getParent().getLevelOfIndentation() * 5);
        } else {
            currButton.setTranslateX(X);
        }
        currButton.setTranslateY(Y);
    }

    /**
     * Renders the lines in-between each of the nodes in the chart using the Arrow class
     */
    public void renderFlowchartLines() {
        ArrayList<Arrow> updatedLineList = new ArrayList<>();
        for (int i = 0; i < flowchart.getMyList().size() - 1; i++) {
            if (i == 0 && flowchart.getMyList().size() > 2) {
                Arrow currLine = new Arrow(buttonsInChart.get(i).getBoundsInParent().getMinX() + 28, buttonsInChart.get(i).getBoundsInParent().getMaxY() + 5, buttonsInChart.get(i + 1).getBoundsInParent().getMinX() + 28, buttonsInChart.get(i + 1).getBoundsInParent().getMinY());
                currLine.getStyleClass().add("line-in-chart");
                updatedLineList.add(currLine);
                flowchartViewAndController.getChildren().add(currLine);
            } else {
                Arrow currLine = new Arrow(buttonsInChart.get(i).getBoundsInParent().getMinX() + 28, buttonsInChart.get(i).getBoundsInParent().getMaxY() + 2, buttonsInChart.get(i + 1).getBoundsInParent().getMinX() + 28, buttonsInChart.get(i + 1).getBoundsInParent().getMinY() - 2);
                currLine.getStyleClass().add("line-in-chart");
                updatedLineList.add(currLine);
                flowchartViewAndController.getChildren().add(currLine);
            }
        }
        linesInChart = updatedLineList;
        buttonsInChart.clear();
        // re-render the nodes so the lines are beneath them
        renderFlowchartNodes();
    }

    /**
     * Controls events for user interaction with the nodes in chart, represented as buttons
     */
    public void enableButtonEvents() {
        // create a context menu for each button, which is displayed on a right-click
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(new MenuItem("Edit"));
        contextMenu.getItems().add(new MenuItem("Delete"));
        for (int i = 1; i < buttonsInChart.size() - 1; i++) {
            buttonsInChart.get(i).setContextMenu(contextMenu);
            int finalI = i;
            // get the button
            buttonsInChart.get(i).setOnMouseClicked(e -> {
                // get the item in the context menu clicked on
                buttonsInChart.get(finalI).getContextMenu().getItems().get(0).setOnAction(g -> {
                    // edit is clicked on, depending on the type of node the right edit method is called
                    editNode(flowchart.getMyList().get(finalI), finalI);
                });
                // delete is clicked on and the node is removed from the chart
                buttonsInChart.get(finalI).getContextMenu().getItems().get(1).setOnAction(f ->
                {
                    deleteNodeCaller(finalI);
                });
                // open edit window if node double-clicked
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (e.getClickCount() == 2) {
                        editNode(flowchart.getMyList().get(finalI), finalI);
                    }
                }
            });
        }
    }

    // Helper method to call the right edit method
    private void editNode(Node currNode, int finalI) {
        if (flowchart.getMyList().get(finalI) instanceof OutputNode) {
            OutputNode node = (OutputNode) flowchart.getMyList().get(finalI);
            displayEditOutputNode(node.getText(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof InputNode) {
            InputNode node = (InputNode) flowchart.getMyList().get(finalI);
            displayEditInputNode(node.getType(), node.getName(), node.getPrompt(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof VariableNode) {
            VariableNode node = (VariableNode) flowchart.getMyList().get(finalI);
            displayEditVariableNode(node.getName(), node.getValue(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof IfNode) {
            IfNode node = (IfNode) flowchart.getMyList().get(finalI);
            displayEditIfNode(node.getCondition(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof ElseIfNode) {
            ElseIfNode node = (ElseIfNode) flowchart.getMyList().get(finalI);
            displayEditElseIfNode(node.getCondition(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof WhileNode) {
            WhileNode node = (WhileNode) flowchart.getMyList().get(finalI);
            displayEditWhileNode(node.getCondition(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof ForNode) {
            ForNode node = (ForNode) flowchart.getMyList().get(finalI);
            displayEditForNode(node.getIndexer(), node.getFrom(), node.getTo(), node.getToOperator(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof CallNode) {
            CallNode node = (CallNode) flowchart.getMyList().get(finalI);
            displayEditCallNode(node.getFunctionName(), finalI);
        } else if (flowchart.getMyList().get(finalI) instanceof ReturnNode) {
            ReturnNode node = (ReturnNode) flowchart.getMyList().get(finalI);
            displayEditReturnNode(node.getReturnValue(), finalI);
        }
    }


    // Helper method to call the model to delete a node from the chart
    private void deleteNodeCaller(int index) {
        flowchart.deleteNode(index);
        // the render needs to be updated once a node is deleted
        updateView();
    }

    /**
     * Allows the user to zoom in and out of the flowchart
     *
     * @param pane The pane that contains the flowchart view/controller
     */
    public void zoom(Pane pane) {
        pane.setOnScroll(
                new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        double zoomFactor = 1.05;
                        double deltaY = event.getDeltaY();

                        if (deltaY < 0) {
                            zoomFactor = 0.95;
                        }
                        pane.setScaleX(pane.getScaleX() * zoomFactor);
                        pane.setScaleY(pane.getScaleY() * zoomFactor);
                        event.consume();
                    }
                });
    }

    /**
     * Controls events for user interaction with the connection between the nodes in chart, represented as arrows
     */
    public void enableArrowEvents() {
        for (int i = 0; i < linesInChart.size(); i++) {
            int finalI = i;
            linesInChart.get(i).setOnMouseClicked(e -> {
                // the views for inserting are slightly different depending on the type of node
                if (flowchart.getMyList().get(finalI) instanceof EndIfNode
                        || flowchart.getMyList().get(finalI) instanceof EndElseIfNode) {
                    insertElse(finalI);
                } else {
                    insertWithoutElse(finalI);
                }
            });
        }
    }

    // loads view/controller for the user to insert/edit a node, display a selection screen for which type of node to insert
    private void insertWithoutElse(int finalI) {
        InsertNodeSelectionNoElse insertNodeSelectionNoElse = new InsertNodeSelectionNoElse();
        insertNodeSelectionNoElse.showStage();
        insertNodeSelectionNoElse.getInsertInputNodeButton().setOnMouseClicked(insertPrint -> {
            displayInputNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });

        insertNodeSelectionNoElse.getInsertOutputNodeButton().setOnMouseClicked(insertPrint -> {
            displayOutputNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });
        insertNodeSelectionNoElse.getInsertVariableNodeButton().setOnMouseClicked(insertVariable -> {
            displayVariableNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });
        insertNodeSelectionNoElse.getInsertIfNodeButton().setOnMouseClicked(insertIf -> {
            displayIfNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });
        insertNodeSelectionNoElse.getInsertWhileNodeButton().setOnMouseClicked(insertWhile -> {
            displayWhileNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });
        insertNodeSelectionNoElse.getInsertForNodeButton().setOnMouseClicked(insertFor -> {
            displayForNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });
        insertNodeSelectionNoElse.getInsertCallNodeButton().setOnMouseClicked(insertCall -> {
            displayCallNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });
        insertNodeSelectionNoElse.getInsertReturnNodeButton().setOnMouseClicked(insertReturn -> {
            displayReturnNodeInsert(finalI);
            insertNodeSelectionNoElse.closeStage();
        });
    }

    // loads view/controller for the user to insert/edit a node, with the option of adding an else/else if node, display a selection screen for which type of node to insert
    private void insertElse(int finalI) {
        InsertNodeSelection insertNodeSelection = new InsertNodeSelection();
        insertNodeSelection.showStage();
        insertNodeSelection.getInsertInputNodeButton().setOnMouseClicked(insertPrint -> {
            displayInputNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertOutputNodeButton().setOnMouseClicked(insertPrint -> {
            displayOutputNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertVariableNodeButton().setOnMouseClicked(insertVariable -> {
            displayVariableNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertIfNodeButton().setOnMouseClicked(insertIf -> {
            displayIfNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertElseNodeButton().setOnMouseClicked(insertElse -> {
            flowchart.addElseNode(finalI + 1);
            updateView();
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertElseIfNodeButton().setOnMouseClicked(insertElseIf -> {
            displayElseIfNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertWhileNodeButton().setOnMouseClicked(insertWhile -> {
            displayWhileNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertForNodeButton().setOnMouseClicked(insertFor -> {
            displayForNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertCallNodeButton().setOnMouseClicked(insertCall -> {
            displayCallNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
        insertNodeSelection.getInsertReturnNodeButton().setOnMouseClicked(insertReturn -> {
            displayReturnNodeInsert(finalI);
            insertNodeSelection.closeStage();
        });
    }

    // the following private methods are responsible for loading view/controllers for inserting and editing nodes
    private void displayOutputNodeInsert(int finalI) {
        FlowchartGUIOutputNodeInsert flowchartGUIOutputNodeInsert = new FlowchartGUIOutputNodeInsert(false);
        flowchartGUIOutputNodeInsert.display();
        flowchartGUIOutputNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            outputEvent(flowchartGUIOutputNodeInsert, finalI, false);
        });
        flowchartGUIOutputNodeInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                outputEvent(flowchartGUIOutputNodeInsert, finalI, false);
            }
        });
    }

    private void displayEditOutputNode(String text, int finalI) {
        FlowchartGUIOutputNodeInsert flowchartGUIOutputNodeInsert = new FlowchartGUIOutputNodeInsert(true);
        flowchartGUIOutputNodeInsert.setTextField(text);
        flowchartGUIOutputNodeInsert.display();
        flowchartGUIOutputNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            outputEvent(flowchartGUIOutputNodeInsert, finalI, true);
        });
        flowchartGUIOutputNodeInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                outputEvent(flowchartGUIOutputNodeInsert, finalI, true);
            }
        });
    }

    private void outputEvent(FlowchartGUIOutputNodeInsert flowchartGUIOutputNodeInsert, int finalI, boolean editing) {
        String textForOutputVertex = flowchartGUIOutputNodeInsert.getTextField().getText();
        if (textForOutputVertex.length() < 40000) {
            flowchartGUIOutputNodeInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addOutputNode(textForOutputVertex, finalI + 1);
            } else {
                OutputNode node = (OutputNode) flowchart.getMyList().get(finalI);
                node.setText(textForOutputVertex);
            }
            updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Output");
            alert.setHeaderText("Your output is invalid!");
            alert.setContentText("Make sure your output corresponds to the following rules:\n" +
                    "- It is not over 40,000 characters long");
            alert.showAndWait();
        }
    }

    private void displayInputNodeInsert(int finalI) {
        FlowchartGUIInputNodeInsert flowchartGUIInputNodeInsert = new FlowchartGUIInputNodeInsert(false);
        flowchartGUIInputNodeInsert.display();
        flowchartGUIInputNodeInsert.getCreateButton().setOnMouseClicked(e -> {
            inputEvent(flowchartGUIInputNodeInsert, finalI, false);
        });
        flowchartGUIInputNodeInsert.getValuePromptField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputEvent(flowchartGUIInputNodeInsert, finalI, false);
            }
        });
    }

    private void displayEditInputNode(String type, String name, String prompt, int finalI) {
        FlowchartGUIInputNodeInsert flowchartGUIInputNodeInsert = new FlowchartGUIInputNodeInsert(true);
        flowchartGUIInputNodeInsert.display();
        if (type.equals("Text")) {
            flowchartGUIInputNodeInsert.getComboBoxForType().setValue(flowchartGUIInputNodeInsert.getComboBoxForType().getItems().get(0));
        } else if (type.equals("Whole number")) {
            flowchartGUIInputNodeInsert.getComboBoxForType().setValue(flowchartGUIInputNodeInsert.getComboBoxForType().getItems().get(1));
        } else if (type.equals("Decimal number")) {
            flowchartGUIInputNodeInsert.getComboBoxForType().setValue(flowchartGUIInputNodeInsert.getComboBoxForType().getItems().get(2));
        } else if (type.equals("True/False")) {
            flowchartGUIInputNodeInsert.getComboBoxForType().setValue(flowchartGUIInputNodeInsert.getComboBoxForType().getItems().get(3));
        }
        flowchartGUIInputNodeInsert.setNameTextField(name);
        flowchartGUIInputNodeInsert.setValuePromptField(prompt);
        flowchartGUIInputNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            inputEvent(flowchartGUIInputNodeInsert, finalI, true);
        });
        flowchartGUIInputNodeInsert.getValuePromptField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputEvent(flowchartGUIInputNodeInsert, finalI, true);
            }
        });
    }

    private void inputEvent(FlowchartGUIInputNodeInsert flowchartGUIInputNodeInsert, int finalI, boolean editing) {
        String valueText = flowchartGUIInputNodeInsert.getComboBoxText();
        String nameText = flowchartGUIInputNodeInsert.getNameTextField().getText();
        String promptText = flowchartGUIInputNodeInsert.getValuePromptField().getText();
        if (nameText.length() < 79 && promptText.length() < 3000) {
            flowchartGUIInputNodeInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addInputNode(valueText, nameText, promptText, finalI + 1);
            } else {
                InputNode node = (InputNode) flowchart.getMyList().get(finalI);
                node.setType(valueText);
                node.setName(nameText);
                node.setPrompt(promptText);
            }
            updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid name or prompt");
            alert.setHeaderText("Your name or prompt are invalid!");
            alert.setContentText("Make sure your name is not over 79 characters and your prompt is not over 3000");
            alert.showAndWait();
        }
    }

    private void displayVariableNodeInsert(int finalI) {
        FlowchartGUIVariableNodeInsert flowchartGUIVariableNodeInsert = new FlowchartGUIVariableNodeInsert(false);
        flowchartGUIVariableNodeInsert.display();
        flowchartGUIVariableNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            variableEvent(flowchartGUIVariableNodeInsert, finalI, false);
        });
        flowchartGUIVariableNodeInsert.getValueTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                variableEvent(flowchartGUIVariableNodeInsert, finalI, false);
            }
        });
    }

    private void displayEditVariableNode(String name, String value, int finalI) {
        FlowchartGUIVariableNodeInsert flowchartGUIVariableNodeInsert = new FlowchartGUIVariableNodeInsert(true);
        flowchartGUIVariableNodeInsert.setNameTextField(name);
        flowchartGUIVariableNodeInsert.setValueTextField(value);
        flowchartGUIVariableNodeInsert.display();
        flowchartGUIVariableNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            variableEvent(flowchartGUIVariableNodeInsert, finalI, true);
        });
        flowchartGUIVariableNodeInsert.getValueTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                variableEvent(flowchartGUIVariableNodeInsert, finalI, true);
            }
        });
    }

    private void variableEvent(FlowchartGUIVariableNodeInsert flowchartGUIVariableNodeInsert, int finalI, boolean editing) {
        String textForName = flowchartGUIVariableNodeInsert.getNameTextField().getText();
        String textOfValue = flowchartGUIVariableNodeInsert.getValueTextField().getText();
        if (textForName.matches("(?i)[a-z][a-z0-9_]*") && textForName.length() < 79 && textOfValue.length() < 4000) {
            flowchartGUIVariableNodeInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addVariableNode(textForName, textOfValue, finalI + 1);
            } else {
                VariableNode node = (VariableNode) flowchart.getMyList().get(finalI);
                node.setName(textForName);
                node.setValue(textOfValue);
            }
            updateView();
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
    }

    private void displayIfNodeInsert(int finalI) {
        FlowchartGUIIfNodeInsert flowchartGUIIfVertexInsert = new FlowchartGUIIfNodeInsert(false);
        flowchartGUIIfVertexInsert.display();
        flowchartGUIIfVertexInsert.getCreateButton().setOnMouseClicked(f -> {
            ifNodeEvent(flowchartGUIIfVertexInsert, finalI, false);
        });
        flowchartGUIIfVertexInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ifNodeEvent(flowchartGUIIfVertexInsert, finalI, false);
            }
        });
    }

    private void displayEditIfNode(String condition, int finalI) {
        FlowchartGUIIfNodeInsert flowchartGUIIfNodeInsert = new FlowchartGUIIfNodeInsert(true);
        flowchartGUIIfNodeInsert.setTextField(condition);
        flowchartGUIIfNodeInsert.display();
        flowchartGUIIfNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            ifNodeEvent(flowchartGUIIfNodeInsert, finalI, true);
        });
        flowchartGUIIfNodeInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ifNodeEvent(flowchartGUIIfNodeInsert, finalI, true);
            }
        });
    }

    private void ifNodeEvent(FlowchartGUIIfNodeInsert flowchartGUIIfVertexInsert, int finalI, boolean editing) {
        String conditionForIfNode = flowchartGUIIfVertexInsert.getTextField().getText();
        if (conditionForIfNode.length() < 4000) {
            flowchartGUIIfVertexInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addIfNode(conditionForIfNode, finalI + 1);
            } else {
                IfNode node = (IfNode) flowchart.getMyList().get(finalI);
                node.setCondition(conditionForIfNode);
            }
            updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid condition");
            alert.setHeaderText("Your condition is invalid!");
            alert.setContentText("Make it is not over 4000 characters long");
            alert.showAndWait();
        }
    }

    private void displayElseIfNodeInsert(int finalI) {
        FlowchartGUIElseIfNodeInsert flowchartGUIElseIfVertexInsert = new FlowchartGUIElseIfNodeInsert(false);
        flowchartGUIElseIfVertexInsert.display();
        flowchartGUIElseIfVertexInsert.getCreateButton().setOnMouseClicked(f -> {
            elseIfEvent(flowchartGUIElseIfVertexInsert, finalI, false);
        });
        flowchartGUIElseIfVertexInsert.getTextFieldForCondition().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                elseIfEvent(flowchartGUIElseIfVertexInsert, finalI, false);
            }
        });
    }

    private void displayEditElseIfNode(String condition, int finalI) {
        FlowchartGUIElseIfNodeInsert flowchartGUIElseIfNodeInsert = new FlowchartGUIElseIfNodeInsert(true);
        flowchartGUIElseIfNodeInsert.setTextFieldForCondition(condition);
        flowchartGUIElseIfNodeInsert.display();
        flowchartGUIElseIfNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            elseIfEvent(flowchartGUIElseIfNodeInsert, finalI, true);
        });
        flowchartGUIElseIfNodeInsert.getTextFieldForCondition().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                elseIfEvent(flowchartGUIElseIfNodeInsert, finalI, true);
            }
        });
    }

    private void elseIfEvent(FlowchartGUIElseIfNodeInsert flowchartGUIElseIfVertexInsert, int finalI, boolean editing) {
        String conditionForElseIfNode = flowchartGUIElseIfVertexInsert.getTextFieldForCondition().getText();
        if (conditionForElseIfNode.length() < 4000) {
            flowchartGUIElseIfVertexInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addElseIfNode(conditionForElseIfNode, finalI + 1);
            } else {
                ElseIfNode node = (ElseIfNode) flowchart.getMyList().get(finalI);
                node.setCondition(conditionForElseIfNode);
            }
            updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid condition");
            alert.setHeaderText("Your condition is invalid!");
            alert.setContentText("Make it is not over 4000 characters long");
            alert.showAndWait();
        }
    }

    private void displayWhileNodeInsert(int finalI) {
        FlowchartGUIWhileNodeInsert flowchartGUIWhileVertexInsert = new FlowchartGUIWhileNodeInsert(false);
        flowchartGUIWhileVertexInsert.display();
        flowchartGUIWhileVertexInsert.getCreateButton().setOnMouseClicked(f -> {
            whileEvent(flowchartGUIWhileVertexInsert, finalI, false);
        });
        flowchartGUIWhileVertexInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                whileEvent(flowchartGUIWhileVertexInsert, finalI, false);
            }
        });
    }

    private void displayEditWhileNode(String condition, int finalI) {
        FlowchartGUIWhileNodeInsert flowchartGUIWhileNodeInsert = new FlowchartGUIWhileNodeInsert(true);
        flowchartGUIWhileNodeInsert.setTextField(condition);
        flowchartGUIWhileNodeInsert.display();
        flowchartGUIWhileNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            whileEvent(flowchartGUIWhileNodeInsert, finalI, true);
        });
        flowchartGUIWhileNodeInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                whileEvent(flowchartGUIWhileNodeInsert, finalI, true);
            }
        });
    }

    private void whileEvent(FlowchartGUIWhileNodeInsert flowchartGUIWhileVertexInsert, int finalI, boolean editing) {
        String conditionForWhileNode = flowchartGUIWhileVertexInsert.getTextField().getText();
        if (conditionForWhileNode.length() < 40000) {
            flowchartGUIWhileVertexInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addWhileNode(conditionForWhileNode, finalI + 1);
            } else {
                WhileNode node = (WhileNode) flowchart.getMyList().get(finalI);
                node.setCondition(conditionForWhileNode);
            }
            updateView();
        } else if (conditionForWhileNode.length() > 40000) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid condition");
            alert.setHeaderText("Your condition is invalid!");
            alert.setContentText("Make it is not over 4000 characters long");
            alert.showAndWait();
        }
    }

    private void displayForNodeInsert(int finalI) {
        FlowchartGUIForNodeInsert flowchartGUIForNodeInsert = new FlowchartGUIForNodeInsert(false);
        flowchartGUIForNodeInsert.display();
        flowchartGUIForNodeInsert.getCreateButton().setOnMouseClicked(e -> {
            forNodeEvent(flowchartGUIForNodeInsert, finalI, false);
        });
        flowchartGUIForNodeInsert.getToTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                forNodeEvent(flowchartGUIForNodeInsert, finalI, false);
            }
        });
    }

    private void displayEditForNode(String indexer, String from, String to, String toOperator, int finalI) {
        FlowchartGUIForNodeInsert flowchartGUIForNodeInsert = new FlowchartGUIForNodeInsert(true);
        flowchartGUIForNodeInsert.display();
        flowchartGUIForNodeInsert.setIndexerTextField(indexer);
        flowchartGUIForNodeInsert.setFromTextField(from);
        flowchartGUIForNodeInsert.getToComboBox().setValue("<");
        if (toOperator == "=") {
            flowchartGUIForNodeInsert.getToComboBox().setValue("=");
            int decrementTo = Integer.valueOf(to);
            decrementTo--;
            to = String.valueOf(decrementTo);
            flowchartGUIForNodeInsert.setToTextField(to);
        } else if (toOperator == "<") {
            flowchartGUIForNodeInsert.getToComboBox().setValue("<");
            flowchartGUIForNodeInsert.setToTextField(to);
        }
        flowchartGUIForNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            forNodeEvent(flowchartGUIForNodeInsert, finalI, true);
        });
        flowchartGUIForNodeInsert.getToTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                forNodeEvent(flowchartGUIForNodeInsert, finalI, true);
            }
        });
    }

    private void forNodeEvent(FlowchartGUIForNodeInsert flowchartGUIForNodeInsert, int finalI, boolean editing) {
        String textForIndexer = flowchartGUIForNodeInsert.getIndexerTextField().getText();
        String textForFrom = flowchartGUIForNodeInsert.getFromTextField().getText();
        String textForTo = flowchartGUIForNodeInsert.getToTextField().getText();
        String textForToOperator = flowchartGUIForNodeInsert.getToComboBoxText();
        if (textForIndexer.length() < 79 && textForFrom.length() < 4000 && textForTo.length() < 4000) {
            flowchartGUIForNodeInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addForNode(textForIndexer, textForFrom, textForTo, textForToOperator, finalI + 1);
            } else {
                ForNode node = (ForNode) flowchart.getMyList().get(finalI);
                node.setIndexer(textForIndexer);
                node.setFrom(textForFrom);
                node.setToOperator(textForToOperator);
                if (textForToOperator == "=") {
                    int incrementTo = Integer.valueOf(textForTo);
                    incrementTo++;
                    textForTo = String.valueOf(incrementTo);
                }
                node.setTo(textForTo);
            }
            updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid input");
            alert.setHeaderText("Your input is invalid!");
            alert.setContentText("- Make sure your indexer name is not over 79 characters\n" +
                    "- Make sure your from value is less than 4000 characters\n" +
                    "- Make sure your to value is less than 4000 characters");
            alert.showAndWait();
        }
    }

    private void displayCallNodeInsert(int finalI) {
        FlowchartGUICallFunctionInsert flowchartGUICallFunctionInsert = new FlowchartGUICallFunctionInsert(false);
        flowchartGUICallFunctionInsert.display();
        flowchartGUICallFunctionInsert.getCreateButton().setOnMouseClicked(f -> {
            callEvent(flowchartGUICallFunctionInsert, finalI, false);
        });
        flowchartGUICallFunctionInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                callEvent(flowchartGUICallFunctionInsert, finalI, false);
            }
        });
    }

    private void displayEditCallNode(String text, int finalI) {
        FlowchartGUICallFunctionInsert flowchartGUICallFunctionInsert = new FlowchartGUICallFunctionInsert(true);
        flowchartGUICallFunctionInsert.setTextField(text);
        flowchartGUICallFunctionInsert.display();
        flowchartGUICallFunctionInsert.getCreateButton().setOnMouseClicked(f -> {
            callEvent(flowchartGUICallFunctionInsert, finalI, true);
        });
        flowchartGUICallFunctionInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                callEvent(flowchartGUICallFunctionInsert, finalI, true);
            }
        });
    }

    private void callEvent(FlowchartGUICallFunctionInsert flowchartGUICallFunctionInsert, int finalI, boolean editing) {
        String textForOutputVertex = flowchartGUICallFunctionInsert.getTextField().getText();
        if (textForOutputVertex.matches("^\\D(\\w+)\\s*\\((.*?)\\)")) {
            flowchartGUICallFunctionInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addCallNode(textForOutputVertex, finalI + 1);
            } else {
                CallNode node = (CallNode) flowchart.getMyList().get(finalI);
                node.setFunctionName(textForOutputVertex);
            }
            updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Output");
            alert.setHeaderText("Your output is invalid!");
            alert.setContentText("Make sure your output corresponds to the format of a function call");
            alert.showAndWait();
        }
    }

    private void displayReturnNodeInsert(int finalI) {
        FlowchartGUIReturnNodeInsert flowchartGUIReturnNodeInsert = new FlowchartGUIReturnNodeInsert(false);
        flowchartGUIReturnNodeInsert.display();
        flowchartGUIReturnNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            returnEvent(flowchartGUIReturnNodeInsert, finalI, false);
        });
        flowchartGUIReturnNodeInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                returnEvent(flowchartGUIReturnNodeInsert, finalI, false);
            }
        });
    }

    private void displayEditReturnNode(String returnValue, int finalI) {
        FlowchartGUIReturnNodeInsert flowchartGUIReturnNodeInsert = new FlowchartGUIReturnNodeInsert(true);
        flowchartGUIReturnNodeInsert.setTextField(returnValue);
        flowchartGUIReturnNodeInsert.display();
        flowchartGUIReturnNodeInsert.getCreateButton().setOnMouseClicked(f -> {
            returnEvent(flowchartGUIReturnNodeInsert, finalI, true);
        });
        flowchartGUIReturnNodeInsert.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                returnEvent(flowchartGUIReturnNodeInsert, finalI, true);
            }
        });
    }

    private void returnEvent(FlowchartGUIReturnNodeInsert flowchartGUIReturnNodeInsert, int finalI, boolean editing) {
        String textForReturnValueName = flowchartGUIReturnNodeInsert.getTextField().getText();
        if (textForReturnValueName.length() < 40000) {
            flowchartGUIReturnNodeInsert.getPrimaryStage().close();
            if (!editing) {
                flowchart.addReturnNode(textForReturnValueName, finalI + 1);
            } else {
                ReturnNode node = (ReturnNode) flowchart.getMyList().get(finalI);
                node.setReturnValue(textForReturnValueName);
            }
            updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Output");
            alert.setHeaderText("Your output is invalid!");
            alert.setContentText("Make sure your output corresponds to the following rules:\n" +
                    "- It is not over 40,000 characters long");
            alert.showAndWait();
        }
    }

    // re-renders the view when the user makes a change
    public void updateView() {
        flowchartViewAndController.getChildren().clear();
        buttonsInChart.clear();
        linesInChart.clear();
        renderFlowchartNodes();
        renderFlowchartLines();
        // call the event methods again
        enableArrowEvents();
        setDraggable();
        enableButtonEvents();
    }

    /**
     * Allows the chart to be moved around the view
     */
    public void setDraggable() {
        buttonsInChart.get(0).setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                flowchartViewAndController.setTranslateX(event.getSceneX());
                flowchartViewAndController.setTranslateY(event.getSceneY());
            }
        });
    }

    /**
     * Gets flowchartViewAndController
     *
     * @return value of flowchartViewAndController
     */
    public Pane getFlowchartViewAndController() {
        return flowchartViewAndController;
    }

    /**
     * Gets flowchart
     *
     * @return value of flowchart
     */
    public Flowchart getFlowchart() {
        return flowchart;
    }


    /**
     * Sets flowchart
     *
     * @param flowchart the value for flowchart to be set to
     */
    public void setFlowchart(Flowchart flowchart) {
        this.flowchart = flowchart;
    }
}
