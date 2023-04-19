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
 * Represents the view/controller for inserting input nodes into the chart
 */
public class FlowchartGUIInputNodeInsert extends FlowchartGUINodeInsert{

    private ComboBox comboBoxForType = new ComboBox();
    private TextField nameTextField = new TextField();
    private TextField valuePromptField = new TextField();

    public FlowchartGUIInputNodeInsert(boolean editing) {
        super(editing);
    }

    @Override
    public void renderTop()
    {
        // renders an example use-case for the user to see
        HBox exampleInput = new HBox();
        SVGPath path = new SVGPath();
        path.setContent("M 0 600 L 100 0 L 4000 0 L 3900 600 L 0 600");
        exampleInput.setShape(path);
        exampleInput.setStyle("-fx-background-color:#c4615a;-fx-background-insets: 1;" +
                "-fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        Label exampleForLoopText = new Label("Text INPUT name ASK Enter your name: ");
        exampleInput.getChildren().add(exampleForLoopText);
        exampleInput.setMinWidth(350);
        exampleInput.setMinHeight(30);
        exampleInput.setAlignment(Pos.CENTER);
        // renders a description of the node type and an explanation of the example
        Label rowForDescription = new Label();
        rowForDescription.setText("An input node allows you to get data from the user and store it in a variable.\nFor example, the following will " +
                "ask the user to enter their\nname and then store it in a variable called 'name': \n\n"
        );
        // add all the above to the view
        topOfView.getChildren().add(rowForDescription);
        topOfView.getChildren().add(exampleInput);
        topOfView.setPadding(new Insets(10));
        topOfView.setStyle(" -fx-background-color:#717dad;-fx-border-color: black;\n" +
                "     -fx-font-family: Verdana;\n" +
                "     -fx-font-size:15;");
    }

    @Override
    public void renderCentre()
    {
        HBox mainRow = new HBox();
        nameTextField.setPrefWidth(120);
        valuePromptField.setPrefWidth(120);
        nameTextField.setMaxHeight(30);
        valuePromptField.setMaxHeight(30);
        nameTextField.setMinHeight(30);
        valuePromptField.setMinHeight(30);
        nameTextField.setStyle("-fx-background-insets: 1;" +
                "-fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        valuePromptField.setStyle("-fx-background-insets: 1;" +
                "-fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        mainRow.setPrefHeight(25);
        comboBoxForType.getItems().add("Text");
        comboBoxForType.getItems().add("Whole number");
        comboBoxForType.getItems().add("Decimal number");
        comboBoxForType.getItems().add("True/False");
        comboBoxForType.setPrefWidth(180);
        comboBoxForType.setValue(comboBoxForType.getItems().get(0));
        comboBoxForType.setStyle("-fx-background-insets: 1;" +
                "-fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        mainRow.getChildren().add(comboBoxForType);
        mainRow.getChildren().add(new Label(" INPUT "));
        mainRow.getChildren().add(nameTextField);
        mainRow.getChildren().add(new Label(" ASK "));
        mainRow.getChildren().add(valuePromptField);
        centreOfView.getChildren().add(mainRow);
        valuePromptField.setPrefHeight(25);
        SVGPath path = new SVGPath();
        path.setContent("M 0 600 L 100 0 L 4000 0 L 3900 600 L 0 600");
        centreOfView.setShape(path);
        centreOfView.setStyle("-fx-background-color:#c4615a;-fx-background-insets: 1;" +
                "-fx-background-radius:  1;-fx-border-color: black;-fx-font-family: Verdana;-fx-font-size:15;");
        centreOfView.setMaxWidth(650);
        centreOfView.setMaxHeight(25);
        centreOfView.setAlignment(Pos.CENTER);
    }

    @Override
    public void renderBottom() {
        if(editing) { createButton.setText("EDIT INPUT"); }
        else { createButton.setText("ADD INPUT"); }
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

    public TextField getValuePromptField() {
        return valuePromptField;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public String getComboBoxText() {
        return String.valueOf(comboBoxForType.getValue());
    }

    public ComboBox getComboBoxForType() {
        return comboBoxForType;
    }

    public void setComboBoxForType(ComboBox comboBoxForType) {
        this.comboBoxForType = comboBoxForType;
    }

    public void setNameTextField(String text) {
        this.nameTextField.setText(text);
    }

    public void setValuePromptField(String valuePromptField) {
        this.valuePromptField.setText(valuePromptField);
    }
}
