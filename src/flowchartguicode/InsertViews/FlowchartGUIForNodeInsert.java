package flowchartguicode.InsertViews;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * Represents the view/controller for inserting for nodes into the chart
 */
public class FlowchartGUIForNodeInsert extends FlowchartGUINodeInsert {

    private TextField indexerTextField = new TextField();
    private TextField fromTextField = new TextField();
    private TextField toTextField = new TextField();
    private ComboBox toComboBox = new ComboBox();

    public FlowchartGUIForNodeInsert(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop() {
        // renders an example use-case for the user to see
        HBox exampleForLoop = new HBox();
        SVGPath path = new SVGPath();
        path.setContent("M 0 300 L 100 50 L 2200 50 L 2300 300 L 2200 550 L 100 550 L 0 300 ");
        exampleForLoop.setShape(path);
        exampleForLoop.setStyle("-fx-background-color:#ffd4a0;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        Label exampleForLoopText = new Label("FOR i = 0 to i < 3 do");
        exampleForLoop.getChildren().add(exampleForLoopText);
        exampleForLoop.setMinWidth(200);
        exampleForLoop.setMinHeight(30);
        exampleForLoop.setAlignment(Pos.CENTER);
        // renders a description of the node type and an explanation of the example
        Label rowForDescription = new Label();
        rowForDescription.setText("A for loop allows you to execute the same block of code a specified amount of times.\nFor example, the following will execute the " +
                "same block of code 3 times: \n\n"
        );
        // add all the above to the view
        topOfView.getChildren().add(rowForDescription);
        topOfView.getChildren().add(exampleForLoop);
        topOfView.setPadding(new Insets(10));

        topOfView.setStyle(" -fx-background-color:#717dad;-fx-border-color: black;\n" +
                "     -fx-font-family: Verdana;\n" +
                "     -fx-font-size:15;");
    }

    @Override
    public void renderCentre() {
        Label placeHolderLable = new Label("?");
        indexerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (indexerTextField.getText().length() == 0) {
                placeHolderLable.setText("?");
            } else {
                placeHolderLable.setText(indexerTextField.getText());
            }
        });
        indexerTextField.setPrefWidth(50);
        fromTextField.setPrefWidth(50);
        toTextField.setPrefWidth(50);

        indexerTextField.setMaxHeight(30);
        fromTextField.setMaxHeight(30);
        toTextField.setMaxHeight(30);

        indexerTextField.setMinHeight(30);
        fromTextField.setMinHeight(30);
        toTextField.setMinHeight(30);

        indexerTextField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        fromTextField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        toTextField.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");

        HBox rowForIndexer = new HBox();
        HBox rowForName = new HBox();
        HBox rowForValue = new HBox();

        rowForIndexer.setPrefHeight(25);
        rowForIndexer.getChildren().add(new Label("FOR "));
        rowForIndexer.getChildren().add(indexerTextField);
        centreOfView.getChildren().add(rowForIndexer);
        fromTextField.setPrefHeight(25);
        rowForName.getChildren().add(new Label(" = "));
        rowForName.getChildren().add(fromTextField);
        centreOfView.getChildren().add(rowForName);
        toTextField.setPrefHeight(25);
        rowForValue.getChildren().add(new Label(" to "));
        rowForValue.getChildren().add(placeHolderLable);

        toComboBox.getItems().add("<");
        toComboBox.getItems().add("=");
        toComboBox.setStyle("-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");

        toComboBox.setValue(toComboBox.getItems().get(0));

        rowForValue.getChildren().add(toComboBox);

        rowForValue.getChildren().add(toTextField);
        centreOfView.getChildren().add(rowForValue);
        Label restOfForText = new Label(" do");
        rowForValue.getChildren().add(restOfForText);
        SVGPath path = new SVGPath();
        path.setContent("M 0 300 L 100 50 L 2200 50 L 2300 300 L 2200 550 L 100 550 L 0 300 ");
        centreOfView.setShape(path);
        centreOfView.setStyle("-fx-background-color:#ffd4a0;-fx-background-insets: 1;" +
                "    -fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.setMaxWidth(450);
        centreOfView.setMaxHeight(25);
        centreOfView.setAlignment(Pos.CENTER);
    }

    @Override
    public void renderBottom() {
        if (editing) {
            createButton.setText("EDIT FOR LOOP");
        } else {
            createButton.setText("ADD FOR LOOP");
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

    public TextField getFromTextField() {
        return fromTextField;
    }

    public TextField getToTextField() {
        return toTextField;
    }

    public TextField getIndexerTextField() {
        return indexerTextField;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public String getToComboBoxText() {
        return String.valueOf(toComboBox.getValue());
    }

    public void setIndexerTextField(String indexerTextField) {
        this.indexerTextField.setText(indexerTextField);
    }

    public void setFromTextField(String fromTextField) {
        this.fromTextField.setText(fromTextField);
    }

    public void setToTextField(String toTextField) {
        this.toTextField.setText(toTextField);
    }

    public ComboBox getToComboBox() {
        return toComboBox;
    }
}
