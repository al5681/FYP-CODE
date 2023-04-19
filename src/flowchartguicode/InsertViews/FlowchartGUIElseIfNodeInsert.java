package flowchartguicode.InsertViews;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * Represents the view/controller for inserting else if nodes into the chart
 */
public class FlowchartGUIElseIfNodeInsert extends FlowchartGUINodeInsert {


    private TextField textFieldForCondition = new TextField();

    public FlowchartGUIElseIfNodeInsert(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop() {
        // renders an example use-case for the user to see
        HBox exampleElseIF = new HBox();
        SVGPath path = new SVGPath();
        path.setContent("M 0 200 L 100 50 L 2200 50 L 2300 200 L 2300 450 L 2200 600 L 150 600 L 0 500 L 0 200");
        exampleElseIF.setShape(path);
        exampleElseIF.setStyle("-fx-background-color:#98F5FF;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;");
        Label exampleForLoopText = new Label("ELSE IF x < 5");
        exampleElseIF.getChildren().add(exampleForLoopText);
        exampleElseIF.setMinWidth(200);
        exampleElseIF.setMinHeight(30);
        exampleElseIF.setAlignment(Pos.CENTER);
        // renders a description of the node type and an explanation of the example
        Label rowForDescription = new Label();
        rowForDescription.setText("An else if node allows you to execute a block of code if some condition(s) is true\nafter some other condition(s) has already been evaluated." +
                "\nFor example,\nthe following will execute a block of code only if" +
                " the variable 'x' is less than 5: \n\n");
        // add all the above to the view
        topOfView.getChildren().add(rowForDescription);
        topOfView.getChildren().add(exampleElseIF);
        topOfView.setPadding(new Insets(10));
        topOfView.setStyle(" -fx-background-color:#717dad;-fx-border-color: black;\n" +
                "     -fx-font-family: Verdana;\n" +
                "     -fx-font-size:15;");
    }

    @Override
    public void renderCentre() {
        textFieldForCondition.setPrefHeight(100);
        textFieldForCondition.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.setStyle("-fx-background-color:#98F5FF;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        SVGPath path = new SVGPath();
        path.setContent("M 0 200 L 100 50 L 2200 50 L 2300 200 L 2300 450 L 2200 600 L 150 600 L 0 500 L 0 200");
        centreOfView.setShape(path);
        centreOfView.getChildren().add(new Label("ELSE IF "));
        centreOfView.getChildren().add(textFieldForCondition);
        centreOfView.setMaxWidth(300);
        centreOfView.setMaxHeight(25);
        centreOfView.setAlignment(Pos.CENTER);
    }

    @Override
    public void renderBottom() {
        if (editing) {
            createButton.setText("EDIT ELSE IF");
        } else {
            createButton.setText("ADD ELSE IF");
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

    public TextField getTextFieldForCondition() {
        return textFieldForCondition;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public void setTextFieldForCondition(String textFieldForCondition) {
        this.textFieldForCondition.setText(textFieldForCondition);
    }
}
