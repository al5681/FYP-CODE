package flowchartguicode.InsertViews;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Represents the view/controller for inserting variable nodes into the chart
 */
public class FlowchartGUIVariableNodeInsert extends FlowchartGUINodeInsert {

    private TextField nameTextField = new TextField();
    private TextField valueTextField = new TextField();
    private Label rowForDescription = new Label();

    public FlowchartGUIVariableNodeInsert(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop() {
        // renders an example use-case for the user to see
        HBox exampleVariable = new HBox();
        exampleVariable.setStyle("-fx-background-color:#ffffd0;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;");
        Label exampleForLoopText = new Label("LET myText = \"Hello World\"");
        exampleVariable.getChildren().add(exampleForLoopText);
        exampleVariable.setMinWidth(200);
        exampleVariable.setMinHeight(30);
        exampleVariable.setAlignment(Pos.CENTER);
        // renders a description of the node type and an explanation of the example
        rowForDescription.setText("A variable allows you to store a value in memory. \nFor example, the following will store" +
                " the text \"Hello World\" in 'myText': \n\n"
        );
        // add all the above to the view
        topOfView.getChildren().add(rowForDescription);
        topOfView.getChildren().add(exampleVariable);
        topOfView.setPadding(new Insets(10));

        topOfView.setStyle(" -fx-background-color:#717dad;-fx-border-color: black;\n" +
                "     -fx-font-family: Verdana;\n" +
                "     -fx-font-size:15;");
    }

    @Override
    public void renderCentre() {
        centreOfView.getChildren().add(new Label("LET "));
        nameTextField.setPrefHeight(25);
        nameTextField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.getChildren().add(nameTextField);
        valueTextField.setPrefHeight(25);
        valueTextField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.getChildren().add(new Label(" = "));
        centreOfView.getChildren().add(valueTextField);
        centreOfView.setStyle("-fx-background-color:#ffffd0;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.setMaxWidth(500);
        centreOfView.setMaxHeight(25);
        centreOfView.setAlignment(Pos.CENTER);
    }

    @Override
    public void renderBottom() {
        if (editing) {
            createButton.setText("EDIT VARIABLE");
        } else {
            createButton.setText("ADD VARIABLE");
        }
        createButton.setStyle(
                " -fx-color: #ffffff;\n" +
                        " -fx-text-align: center;\n" +
                        "-fx-font-family: Verdana;\n" +
                        "-fx-border-color: black;");
        bottomOfView.setRight(createButton);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getValueTextField() {
        return valueTextField;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public void setNameTextField(String nameTextField) {
        this.nameTextField.setText(nameTextField);
    }

    public void setValueTextField(String valueTextField) {
        this.valueTextField.setText(valueTextField);
    }
}
