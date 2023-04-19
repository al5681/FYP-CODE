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
 * Represents the view/controller for inserting while nodes into the chart
 */
public class FlowchartGUIWhileNodeInsert extends FlowchartGUINodeInsert {

    private TextField textField = new TextField();

    public FlowchartGUIWhileNodeInsert(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop() {
        // renders an example use-case for the user to see
        HBox exampleElseIF = new HBox();
        SVGPath path = new SVGPath();
        path.setContent("M 0 300 L 100 50 L 2200 50 L 2300 300 L 2200 550 L 100 550 L 0 300 ");
        exampleElseIF.setShape(path);
        exampleElseIF.setStyle("-fx-background-color:#ffd4a0;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        Label exampleForLoopText = new Label("WHILE looping == True");
        exampleElseIF.getChildren().add(exampleForLoopText);
        exampleElseIF.setMinWidth(200);
        exampleElseIF.setMinHeight(30);
        exampleElseIF.setAlignment(Pos.CENTER);
        // renders a description of the node type and an explanation of the example
        Label rowForDescription = new Label();
        rowForDescription.setText("A while loop allows you to execute a block of code repetedly so long as some condition(s)\nis true." +
                "\nFor example, the following will execute the same code until the value of 'looping' is no\nlonger equal to 'True':\n\n");

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
        textField.setPrefHeight(100);
        textField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.setStyle("-fx-background-color:#ffd4a0;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        SVGPath path = new SVGPath();
        path.setContent("M 0 300 L 100 50 L 2200 50 L 2300 300 L 2200 550 L 100 550 L 0 300 ");
        centreOfView.setShape(path);
        centreOfView.getChildren().add(new Label("WHILE "));
        centreOfView.getChildren().add(textField);
        centreOfView.setMaxWidth(300);
        centreOfView.setMaxHeight(25);
        centreOfView.setAlignment(Pos.CENTER);
    }

    @Override
    public void renderBottom() {
        if (editing) {
            createButton.setText("EDIT WHILE");
        } else {
            createButton.setText("ADD WHILE");
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

    public TextField getTextField() {
        return textField;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public void setTextField(String textField) {
        this.textField.setText(textField);
    }
}