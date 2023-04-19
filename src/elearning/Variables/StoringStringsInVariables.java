package elearning.Variables;

import elearning.LessonSuperClass;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class StoringStringsInVariables extends LessonSuperClass {

    // sets up the GUI
    @Override
    protected void setUp() {

        setAnswer("x = \"Hello World\"\n" +
                "print(x)\n");
        // load styles from style sheet
        parentBorderPane.getStylesheets().add("flowchartguicode/CSS/style.css");
        parentBorderPane.getStylesheets().add("flowchartguicode/CSS/insertNodeSelection.css");
        parentBorderPane.getStyleClass().add("background-white");
        runButtonPane.getStyleClass().add("hbox");
        runButton.getStyleClass().add("run-button");
        // set up tab pane to switch between console and code
        // set up the console pane
        consoleBorderPane.setCenter(currentConsole);
        consoleBorderPane.setBottom(runButtonPane);
        runButtonPane.setRight(runButton);

        Tab mainFlowchartTab = new Tab(mainFlowchartBuilder.getFlowchart().getName(), mainFlowchartBuilder.getFlowchartViewAndController());
        mainFlowchartTab.setClosable(false);

        // set up task description
        taskDescriptionContainer.setMaxWidth(280);
        taskDescriptionContainer.setMinWidth(280);

        description.setPadding(new Insets(10));

        description.getChildren().add(new Text("Task 1: Storing data in variables\n\n" +
                "A variable is an element of a programming that can be used to store a piece of data.\n\n" +
                "Try adding a Variable node to the chart give it a name and set its value to “Hello World”\n\n" +
                "Your chart should look like this:\n"));
        ImageView imageDemo = new ImageView("VLesson1.png");
        description.getChildren().add(imageDemo);
        description.getChildren().add(new Text("\nClick the ‘RUN’ button to see if you’ve constructed the chart correctly!\n"));
        description.setStyle("-fx-background-color:#e2dbdb; -fx-font-family: Verdana;-fx-font-size:18; -fx-border-color: black;");


        taskDescriptionContainer.getChildren().add(description);


        righthandSideHBox.getChildren().add(loadVIew);
        loadVIew.setFitHeight(30);
        loadVIew.setFitWidth(30);
        righthandSideHBox.getChildren().add(nextButton);

        bottomBorderPane.setRight(righthandSideHBox);

        Text lessonNumber = new Text("1/2");
        lessonNumber.setStyle("-fx-fill: white;");
        bottomBorderPane.setCenter(lessonNumber);
        bottomBorderPane.setLeft(exitButton);
        bottomBorderPane.setStyle("   -fx-background-color: #211f24;\n" +
                "    -fx-text-fill: #FFF;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-family: 'Roboto';\n" +
                "    -fx-text-align: center;\n" +
                "    -fx-font-size:25;");
        nextButton.setDisable(true);


        parentBorderPane.setBottom(bottomBorderPane);
        parentBorderPane.setCenter(mainFlowchartBuilder.getFlowchartViewAndController());
        parentBorderPane.setRight(outputTabPane);
        parentBorderPane.setLeft(taskDescriptionContainer);
    }

}
