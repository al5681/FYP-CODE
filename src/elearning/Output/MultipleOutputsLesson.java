package elearning.Output;

import elearning.LessonSuperClass;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MultipleOutputsLesson extends LessonSuperClass {



    @Override
    protected void setUp() {
        this.nextButton.setText("Complete");
        setAnswer("print(\"Hello World\")\n" +
                "print(\"Goodbye World\")\n");

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

        description.getChildren().add( new Text("Task 2: Goodbye World!\n\n" +
                "Each Output node represents a new line, so if we add another output node another line of text is printed out to the console.\n\n" +
                "Try adding two Output nodes to the chart, have the first output \"Hello World\" and the second output \"Goodbye World\"\n\n" +
                "Your chart should look like this:\n"));
        ImageView imageDemo = new ImageView("Lesson2example.png");


        description.getChildren().add(imageDemo);
        description.getChildren().add(new Text("\nClick the ‘RUN’ button to see if you’ve constructed the chart correctly!\n"));
        description.setStyle("-fx-background-color:#e2dbdb; -fx-font-family: Verdana;-fx-font-size:18; -fx-border-color: black;");



        taskDescriptionContainer.getChildren().add(description);

        righthandSideHBox.getChildren().add(loadVIew);
        loadVIew.setFitHeight(30);
        loadVIew.setFitWidth(30);
        righthandSideHBox.getChildren().add(backButton);
        righthandSideHBox.getChildren().add(nextButton);

        bottomBorderPane.setRight(righthandSideHBox);
        Text lessonNumber = new Text("2/2");
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
