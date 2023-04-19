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
 * Represents the view/controller for inserting output nodes into the chart
 */
public class FlowchartGUIOutputNodeInsert extends FlowchartGUINodeInsert {

    private TextField textField = new TextField();

    public FlowchartGUIOutputNodeInsert(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop() {
        // renders an example use-case for the user to see
        HBox exampleOutput = new HBox();
        SVGPath path = new SVGPath();
        path.setContent("M 0 600 L 100 0 L 4000 0 L 3900 600 L 0 600");
        exampleOutput.setShape(path);
        exampleOutput.setStyle("-fx-background-color:#d0ffd0;-fx-background-insets: 1;" +
                "-fx-background-radius:  1;-fx-border-color: black;");
        Label exampleForLoopText = new Label("OUTPUT \"Hello World\"");
        exampleOutput.getChildren().add(exampleForLoopText);
        exampleOutput.setMinWidth(200);
        exampleOutput.setMinHeight(30);
        exampleOutput.setAlignment(Pos.CENTER);
        // renders a description of the node type and an explanation of the example
        Label rowForDescription = new Label();
        rowForDescription.setText("An output node allows you to display text on the screen. \nFor example, the following will output" +
                " the text \"Hello World\" onto the screen: \n\n"
        );
        // add all the above to the view
        topOfView.getChildren().add(rowForDescription);
        topOfView.getChildren().add(exampleOutput);
        topOfView.setPadding(new Insets(10));

        topOfView.setStyle(" -fx-background-color:#717dad;-fx-border-color: black;\n" +
                "     -fx-font-family: Verdana;\n" +
                "     -fx-font-size:15;");
    }

    @Override
    public void renderCentre() {
        textField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.setStyle("-fx-background-color:#d0ffd0;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        SVGPath path = new SVGPath();
        path.setContent("M 0 600 L 100 0 L 4000 0 L 3900 600 L 0 600");
        centreOfView.setShape(path);
        centreOfView.getChildren().add(new Label("OUTPUT "));
        centreOfView.getChildren().add(textField);
        centreOfView.setMaxWidth(350);
        centreOfView.setMaxHeight(25);
        centreOfView.setAlignment(Pos.CENTER);
    }

    @Override
    public void renderBottom() {
        if (editing) {
            createButton.setText("EDIT OUTPUT");
        } else {
            createButton.setText("ADD OUTPUT");
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

    public void setTextField(String text) {
        this.textField.setText(text);
    }

    public Button getCreateButton() {
        return createButton;
    }
}
