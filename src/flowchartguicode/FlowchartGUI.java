package flowchartguicode;

import Console.ConsoleView;
import flowchartguicode.InsertViews.FlowchartGUIAddFunction;
import flowchartmodelcode.Flowchart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a view and controller for a chart builder environment and a console to execute the chart
 */
public class FlowchartGUI {

    private BorderPane parentBorderPane = new BorderPane();
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private FlowchartBuilder mainFlowchartBuilder = new FlowchartBuilder("main()");
    private BorderPane consoleBorderPane = new BorderPane();
    private ConsoleView currentConsole = new ConsoleView();
    private BorderPane runButtonPane = new BorderPane();
    private Button runButton = new Button("RUN");
    private TabPane flowchartBuilderTabPane = new TabPane();
    private TabPane outputTabPane = new TabPane();
    private Tab tab1ForOutputTabPane = new Tab("Console", consoleBorderPane);
    private Tab tab2ForOutputTabPane = new Tab("Code", new Label(""));
    private ArrayList<FlowchartBuilder> flowchartBuilderArrayList = new ArrayList<>();
    private GlobalVariablesGUI globalVariablesGUI = new GlobalVariablesGUI();
    private HBox saveBox = new HBox(10);
    private Image saveImage = new Image("save_icon.png");
    private ImageView saveView = new ImageView(saveImage);
    private Image loadImage = new Image("folder.png");
    private ImageView loadVIew = new ImageView(loadImage);
    private Scene flowchartGUIScene;
    private Button exitButton = new Button("Exit");
    private Button savePythonFile = new Button("Save Python file");


    /**
     * Gets flowchartGUIScene
     *
     * @return value of flowchartGUIScene
     */
    public Scene getFlowchartGUIScene() {
        return flowchartGUIScene;
    }

    public FlowchartGUI() {
        setUp();
        // build the scene
        flowchartGUIScene = new Scene(parentBorderPane, WIDTH, HEIGHT);
        mainFlowchartBuilder.renderFlowchartNodes();
        mainFlowchartBuilder.renderFlowchartLines();
        // events
        mainFlowchartBuilder.enableArrowEvents();
        mainFlowchartBuilder.enableButtonEvents();
        // make the chart draggable
        mainFlowchartBuilder.setDraggable();
        flowchartBuilderArrayList.add(mainFlowchartBuilder);
        // call the run() method when the button is clicked
        runButton.setOnMouseClicked(event -> {
            run();
        });
    }

    // sets up the GUI
    private void setUp() {
        // load styles from style sheet
        parentBorderPane.getStylesheets().add("flowchartguicode/CSS/style.css");
        parentBorderPane.getStylesheets().add("flowchartguicode/CSS/insertNodeSelection.css");
        parentBorderPane.getStyleClass().add("background-white");
        runButtonPane.getStyleClass().add("hbox");
        runButton.getStyleClass().add("run-button");
        // set up tab pane to switch between console and code
        setUpTabPane();
        // set up the console pane
        consoleBorderPane.setCenter(currentConsole);
        consoleBorderPane.setBottom(runButtonPane);
        runButtonPane.setRight(runButton);

        Tab mainFlowchartTab = new Tab(mainFlowchartBuilder.getFlowchart().getName(), mainFlowchartBuilder.getFlowchartViewAndController());
        mainFlowchartTab.setClosable(false);

        flowchartBuilderTabPane.getTabs().add(mainFlowchartTab);

        flowchartBuilderTabPane.getTabs().add(newTabButton(flowchartBuilderTabPane));

        // set up global variables pane
        globalVariablesGUI.setUp();
        floppyDiskEvent();
        loadEvent();
        savePythonFile();

        saveView.setFitHeight(20);
        saveView.setFitWidth(20);
        loadVIew.setFitHeight(20);
        loadVIew.setFitWidth(20);

        saveBox.getChildren().add(saveView);
        saveBox.getChildren().add(loadVIew);
        saveBox.getChildren().add(exitButton);
        saveBox.getChildren().add(savePythonFile);
        saveBox.setStyle(" -fx-background-color: #dddddd; -fx-padding: 10px; -fx-border-color: #adacac;");

        // set up the parent pane
        parentBorderPane.setTop(saveBox);
        parentBorderPane.setLeft(globalVariablesGUI.getVariablesPane());
        parentBorderPane.setCenter(flowchartBuilderTabPane);
        parentBorderPane.setRight(outputTabPane);
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
            codeLabel.setText(outputString());
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
        Thread thread = new Thread(() -> {
            PySystemState.initialize();
            try (PythonInterpreter interpreter = new PythonInterpreter(null, new PySystemState())) {
                interpreter.exec(outputString());
            }
        });
        thread.start();
    }

    // returns a string that represents the all components of the program as a single Python file
    private String outputString() {
        String outputString = "";
        for (int i = 0; i < globalVariablesGUI.getGlobalVariablesList().getGlobalVariables().size(); i++) {
            outputString += globalVariablesGUI.getGlobalVariablesList().getGlobalVariables().get(i).output();
        }
        outputString += "\n";
        for (int i = 0; i < flowchartBuilderArrayList.size(); i++) {
            outputString += (flowchartBuilderArrayList.get(i).getFlowchart().outputPythonFunction());
        }
        outputString += "\n" +
                "if __name__ == \"__main__\":\n" +
                "    main()";
        return outputString;
    }

    // allows the Python code to be saved to the root directory as a .py file
    private void savePythonFile() {
        savePythonFile.setOnMouseClicked(f -> {
            String outputString = "";
            for (int i = 0; i < globalVariablesGUI.getGlobalVariablesList().getGlobalVariables().size(); i++) {
                outputString += globalVariablesGUI.getGlobalVariablesList().getGlobalVariables().get(i).output();
            }
            outputString += "\n";
            for (int i = 0; i < flowchartBuilderArrayList.size(); i++) {
                outputString += (flowchartBuilderArrayList.get(i).getFlowchart().outputPythonFunction());
            }
            outputString += "\n" +
                    "if __name__ == \"__main__\":\n" +
                    "    main()";
            try {
                flowchartBuilderArrayList.get(0).getFlowchart().savePythonFile(outputString);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    // called when the 'Create Function' button is clicked on, creating a new tab
    // with its own flowchart that represents a function
    private Tab newTabButton(TabPane tabPane) {
        Tab addTab = new Tab("Create Function"); // You can replace the text with an icon
        addTab.setClosable(false);
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab == addTab) {
                FlowchartGUIAddFunction flowchartGUIAddFunction = new FlowchartGUIAddFunction(false);
                flowchartGUIAddFunction.display();
                flowchartGUIAddFunction.getCreateButton().setOnMouseClicked(f -> {
                    FlowchartBuilder flowchartBuilder = new FlowchartBuilder(flowchartGUIAddFunction.getTextField().getText());
                    flowchartBuilderArrayList.add(flowchartBuilder);
                    Tab newFlowchartTab = new Tab(flowchartGUIAddFunction.getTextField().getText(), flowchartBuilder.getFlowchartViewAndController());
                    tabPane.getTabs().add(tabPane.getTabs().size() - 1, newFlowchartTab); // Adding new tab before the "button" tab
                    tabPane.getSelectionModel().select(tabPane.getTabs().size() - 2); // Selecting the tab before the button, which is the newly created one
                    newFlowchartTab.setOnClosed(e -> {
                        flowchartBuilderArrayList.remove(flowchartBuilder);
                    });
                    flowchartGUIAddFunction.getPrimaryStage().close();
                });
            }
        });
        return addTab;
    }

    public void floppyDiskEvent() {
        String newPath = "saved_flowcharts/";
        saveView.setOnMouseClicked(e -> {
            // create a new directory
            File newDir = new File(newPath);
            if (!newDir.exists()) {
                newDir.mkdirs();
            }
            // write the main flowchart to a folder
            try {
                // selected tab index
                int currentTabIndex = flowchartBuilderTabPane.getSelectionModel().getSelectedIndex();
                flowchartBuilderArrayList.get(currentTabIndex).getFlowchart().writeObjectToFile(newPath + flowchartBuilderArrayList.get(currentTabIndex).getFlowchart().getName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void loadEvent() {
        loadVIew.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                int currentTabIndex = flowchartBuilderTabPane.getSelectionModel().getSelectedIndex();
                try {
                    Flowchart flowchart = flowchartBuilderArrayList.get(currentTabIndex).getFlowchart().readObjectFromFile(file);
                    flowchartBuilderArrayList.get(currentTabIndex).setFlowchart(flowchart);
                    flowchartBuilderArrayList.get(currentTabIndex).updateView();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
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
     * Sets exitButton
     *
     * @param exitButton the value for exitButton to be set to
     */
    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }
}