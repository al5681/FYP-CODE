package flowchartguicode.InsertViews;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class FlowchartGUINodeInsert{
    protected Stage primaryStage;
    protected BorderPane mainBorderPane = new BorderPane();
    protected boolean editing;
    protected FlowPane topOfView = new FlowPane();
    protected HBox centreOfView = new HBox();
    protected BorderPane bottomOfView = new BorderPane();
    protected Button createButton = new Button();

    /**
     * If the user is editing a pre-existing node the view needs to be slightly different
     * @param editing if True then the create button text is set to "Edit" otherwise "Create"
     */
    public FlowchartGUINodeInsert(boolean editing) {
        this.editing = editing;
    }

    /**
     * Sets up the contents of the view/controller and opens it
     */
    public void display() {
        // create the stage
        primaryStage = new Stage();
        // Special properties for a pop-up window
        primaryStage.resizableProperty().setValue(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        // render the contents
        renderTop();
        renderCentre();
        renderBottom();
        mainBorderPane.setTop(topOfView);
        mainBorderPane.setCenter(centreOfView);
        mainBorderPane.setBottom(bottomOfView);
        // launch the window
        primaryStage.setScene(new Scene(mainBorderPane, 700, 400));
        primaryStage.show();
        createButton.setOnMouseClicked(e -> {
            primaryStage.close();
        });
    }

    /**
     * Renders the top of the window
     */
    public void renderTop() {}

    /**
     * Renders the centre of the window,
     * A text box in the shape of the node as shown in the chart
     * where the user fills in the blanks
     */
    public void renderCentre() {}

    /**
     * Renders the bottom of the window,
     * a button on the right-hand side to create or edit a node
     */
    public void renderBottom() {}

}
