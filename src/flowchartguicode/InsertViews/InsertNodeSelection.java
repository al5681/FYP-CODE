package flowchartguicode.InsertViews;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Controller for node selection when inserting nodes
 */
public class InsertNodeSelection {

    @FXML
    Button insertOutputNodeButton;

    @FXML
    Button insertVariableNodeButton;

    @FXML
    Button insertIfNodeButton;

    @FXML
    Button insertElseNodeButton;

    @FXML
    Button insertElseIfNodeButton;

    @FXML
    Button insertWhileNodeButton;

    @FXML
    Button insertForNodeButton;

    @FXML
    Button insertInputNodeButton;

    @FXML
    Button insertCallNodeButton;

    @FXML
    Button insertReturnNodeButton;

    private Stage thisStage;

    public InsertNodeSelection(){
        // Create the new stage
        thisStage = new Stage();

        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("insertNodeView.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.resizableProperty().setValue(false);
            thisStage.initStyle(StageStyle.UTILITY);
            thisStage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the stage that was loaded in the constructor
     */
    public void showStage() {
        thisStage.show();
    }

    public void closeStage() {
        thisStage.close();
    }

    public Button getInsertOutputNodeButton() {
        return insertOutputNodeButton;
    }

    public Button getInsertVariableNodeButton() {
        return insertVariableNodeButton;
    }

    public Button getInsertIfNodeButton() {
        return insertIfNodeButton;
    }

    public Button getInsertElseNodeButton() {
        return insertElseNodeButton;
    }

    public Button getInsertElseIfNodeButton() {
        return insertElseIfNodeButton;
    }

    public Button getInsertWhileNodeButton() {
        return insertWhileNodeButton;
    }

    public Button getInsertForNodeButton() {
        return insertForNodeButton;
    }

    public Button getInsertInputNodeButton() {
        return insertInputNodeButton;
    }

    public Button getInsertCallNodeButton() {
        return insertCallNodeButton;
    }
    
    public Button getInsertReturnNodeButton() {
        return insertReturnNodeButton;
    }
}
