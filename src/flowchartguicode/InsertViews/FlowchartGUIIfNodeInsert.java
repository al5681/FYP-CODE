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
 * Represents the view/controller for inserting if nodes into the chart
 */
public class FlowchartGUIIfNodeInsert extends FlowchartGUINodeInsert {

    private TextField textField = new TextField();

    public FlowchartGUIIfNodeInsert(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop() {
        // renders an example use-case for the user to see
        HBox exampleIf = new HBox();
        SVGPath path = new SVGPath();
        path.setContent("M 0 200 L 100 50 L 2200 50 L 2300 200 L 2300 450 L 2200 600 L 150 600 L 0 500 L 0 200");
        exampleIf.setShape(path);
        exampleIf.setStyle("-fx-background-color:#98F5FF;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;");
        Label exampleForLoopText = new Label("IF x > 5");
        exampleIf.getChildren().add(exampleForLoopText);
        exampleIf.setMinWidth(200);
        exampleIf.setMinHeight(30);
        exampleIf.setAlignment(Pos.CENTER);
        // renders a description of the node type and an explanation of the example
        Label rowForDescription = new Label();
        rowForDescription.setText("An if node allows you to execute a block of code if some condition(s) is true. \n" +
                "For example,\nthe following will execute a block of code only if" +
                " the variable 'x' is greater than 5: \n\n");


        topOfView.getChildren().add(rowForDescription);
        topOfView.getChildren().add(exampleIf);
        topOfView.setPadding(new Insets(10));
        // add all the above to the view
        topOfView.setStyle(" -fx-background-color:#717dad;-fx-border-color: black;\n" +
                "     -fx-font-family: Verdana;\n" +
                "     -fx-font-size:15;");
    }

    @Override
    public void renderCentre() {
        textField.setPrefHeight(100);
        textField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.setStyle("-fx-background-color:#98F5FF;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        SVGPath path = new SVGPath();
        path.setContent("M 0 200 L 100 50 L 2200 50 L 2300 200 L 2300 450 L 2200 600 L 150 600 L 0 500 L 0 200");
        centreOfView.setShape(path);
        centreOfView.getChildren().add(new Label("IF "));
        centreOfView.getChildren().add(textField);
        centreOfView.setMaxWidth(300);
        centreOfView.setMaxHeight(25);
        centreOfView.setAlignment(Pos.CENTER);
    }

    @Override
    public void renderBottom() {
        if (editing) {
            createButton.setText("EDIT IF");
        } else {
            createButton.setText("ADD IF");
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
