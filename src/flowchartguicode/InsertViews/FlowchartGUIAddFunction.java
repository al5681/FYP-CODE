package flowchartguicode.InsertViews;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Represents the view/controller for adding a new function (flowchart) to the program
 */
public class FlowchartGUIAddFunction extends FlowchartGUINodeInsert {

    private TextField textField = new TextField();

    public FlowchartGUIAddFunction(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop() {
        // renders a description of the node type and an explanation of the example
        Label rowForDescription = new Label();
        rowForDescription.setText("Enter the name for your function in the textbox: \n\n"
        );
        // add all the above to the view
        topOfView.getChildren().add(rowForDescription);
        topOfView.setPadding(new Insets(10));

        topOfView.setStyle(" -fx-background-color:#717dad;-fx-border-color: black;\n" +
                "     -fx-font-family: Verdana;\n" +
                "     -fx-font-size:15;");
    }

    @Override
    public void renderCentre() {
        textField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.getChildren().add(textField);
        textField.setPrefHeight(25);
        textField.setPrefWidth(350);
        centreOfView.setMaxWidth(350);
        centreOfView.setMaxHeight(25);
    }

    @Override
    public void renderBottom() {
        createButton.setText("ADD FUNCTION");
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

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(String text) {
        this.textField.setText(text);
    }

    public Button getCreateButton() {
        return createButton;
    }
}
