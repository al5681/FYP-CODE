package elearning;

import Console.ConsoleView;
import flowchartguicode.FlowchartBuilder;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * Represents a view/controller for a lesson the contents of which are described in the report
 */
public abstract class LessonSuperClass {

    protected BorderPane parentBorderPane = new BorderPane();
    protected final int WIDTH = 1280;
    protected final int HEIGHT = 900;
    protected FlowchartBuilder mainFlowchartBuilder = new FlowchartBuilder();
    protected BorderPane consoleBorderPane = new BorderPane();
    protected ConsoleView currentConsole = new ConsoleView();
    protected BorderPane runButtonPane = new BorderPane();
    protected Button runButton = new Button("RUN");
    protected TabPane outputTabPane = new TabPane();
    protected Tab tab1ForOutputTabPane = new Tab("Console", consoleBorderPane);
    protected Tab tab2ForOutputTabPane = new Tab("Code", new Label(""));
    protected VBox taskDescriptionContainer = new VBox();
    protected TextFlow description = new TextFlow();
    protected String answer;
    protected BorderPane bottomBorderPane = new BorderPane();
    protected Button nextButton = new Button("Next");
    protected Button backButton = new Button("Back");
    protected Button exitButton = new Button("Exit");
    protected Scene scene;
    protected Image loadImage = new Image("1200px-Stop.svg.png");
    protected ImageView loadVIew = new ImageView(loadImage);
    protected HBox righthandSideHBox = new HBox(10);


    /**
     * Gets scene
     *
     * @return value of scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets nextButton
     *
     * @return value of nextButton
     */
    public Button getNextButton() {
        return nextButton;
    }

    /**
     * Sets nextButton
     *
     * @param nextButton the value for nextButton to be set to
     */
    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
    }

    /**
     * Gets exitButton
     *
     * @return value of exitButton
     */
    public Button getExitButton() {
        return exitButton;
    }

    /**
     * Gets backButton
     *
     * @return value of backButton
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Sets exitButton
     *
     * @param exitButton the value for exitButton to be set to
     */
    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }

    public LessonSuperClass() {
        setUp();
        // build the scene
        scene = new Scene(parentBorderPane, WIDTH, HEIGHT);
        mainFlowchartBuilder.renderFlowchartNodes();
        mainFlowchartBuilder.renderFlowchartLines();
        // events
        mainFlowchartBuilder.enableArrowEvents();
        mainFlowchartBuilder.enableButtonEvents();
        // make the chart draggable
        mainFlowchartBuilder.setDraggable();
        // call the run() method when the button is clicked
        runButton.setOnMouseClicked(event -> {
            run();
        });
        setUpTabPane();

    }

    // sets up the different tabs to view the console or code
    private void setUpTabPane() {
        outputTabPane.getTabs().add(tab1ForOutputTabPane);
        outputTabPane.getTabs().add(tab2ForOutputTabPane);
        outputTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        TextArea codeLabel = new TextArea(mainFlowchartBuilder.getFlowchart().outputPythonCode());
        parentBorderPane.setOnMouseEntered(e -> {
            codeLabel.setEditable(false);
            codeLabel.setText(mainFlowchartBuilder.getFlowchart().outputPythonFunction());
            codeLabel.setText(mainFlowchartBuilder.getFlowchart().outputPythonCode());
            codeLabel.wrapTextProperty().set(true);
            codeLabel.getStyleClass().add("codeText");
            outputTabPane.getTabs().get(1).setContent(codeLabel);
        });
    }

    // Creates a new instance of ConsoleView and calls a Python Interpreter to execute
    // the program gendered by the flowchart within the GUI
    private void run() {
        currentConsole = new ConsoleView();
        consoleBorderPane.setCenter(currentConsole);
        System.setOut(currentConsole.getOut());
        System.setIn(currentConsole.getIn());
        System.setErr(currentConsole.getOut());
        if (mainFlowchartBuilder.getFlowchart().outputPythonCode().equals(answer)) {
            nextButton.setDisable(false);
            Image tickImage = new Image("PngItem.png");
            loadVIew.setImage(tickImage);
        }
        Thread thread = new Thread(() -> {
            PySystemState.initialize();
            try (PythonInterpreter interpreter = new PythonInterpreter(null, new PySystemState())) {
                interpreter.exec(mainFlowchartBuilder.getFlowchart().outputPythonCode());
            }
        });

        thread.start();
    }


    /**
     * This method is overridden my all lessons, it is used to fill in the unique content for the lesson
     * into the template provided by this class
     */
    protected abstract void setUp();

    /**
     * Sets answer
     *
     * @param answer the value for answer to be set to
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
