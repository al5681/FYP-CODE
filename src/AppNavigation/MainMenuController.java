package AppNavigation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the main menu for the application brining all others parts of the app together
 */
public class MainMenuController {

    @FXML
    Button lessonsButton;

    @FXML
    Button chartBuilderButton;

    private Stage stage;

    private Scene scene;

    public MainMenuController() {
        stage = new Stage();

        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));

            // Set this class as the controller
            loader.setController(this);

            scene = new Scene(loader.load());

            // Load the scene
            stage.setScene(scene
            );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        stage.show();
    }

    public void closeStage() {
        stage.close();
    }

    /**
     * Gets lessonsButton
     *
     * @return value of lessonsButton
     */
    public Button getLessonsButton() {
        return lessonsButton;
    }

    /**
     * Gets chartBuilderButton
     *
     * @return value of chartBuilderButton
     */
    public Button getChartBuilderButton() {
        return chartBuilderButton;
    }

    /**
     * Gets stage
     *
     * @return value of stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets stage
     *
     * @param stage the value for stage to be set to
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets scene
     *
     * @return value of scene
     */
    public Scene getScene() {
        return scene;
    }
}
