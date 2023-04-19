package AppNavigation;

import elearning.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This controller class acts as a menu for lesson selection
 *
 */
public class LessonSelectionController {

    @FXML
    Button lessonOne;

    @FXML
    Button lessonTwo;

    @FXML
    Button lessonThree;

    @FXML
    Button lessonFour;

    @FXML
    Button lessonFive;

    @FXML
    Button exitButton;

    OutputLessons outputLessons = new OutputLessons();

    VariableLessons variableLessons = new VariableLessons();

    InputLessons inputLessons = new InputLessons();

    ConditionalLesson conditionalLesson = new ConditionalLesson();

    LoopLesson loopLesson = new LoopLesson();

    String currentLesson;


    private Stage stage;

    private Scene scene;

    public LessonSelectionController() {
        stage = new Stage();

        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LessonSelectionView.fxml"));

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

    /**
     * Sets scene
     *
     * @param scene the value for scene to be set to
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Gets lessonOne
     *
     * @return value of lessonOne
     */
    public Button getLessonOne() {
        return lessonOne;
    }

    /**
     * Gets lessonTwo
     *
     * @return value of lessonTwo
     */
    public Button getLessonTwo() {
        return lessonTwo;
    }

    /**
     * Gets lessonThree
     *
     * @return value of lessonThree
     */
    public Button getLessonThree() {
        return lessonThree;
    }

    /**
     * Gets lessonFour
     *
     * @return value of lessonFour
     */
    public Button getLessonFour() {
        return lessonFour;
    }

    /**
     * Gets lessonFive
     *
     * @return value of lessonFive
     */
    public Button getLessonFive() {
        return lessonFive;
    }

    /**
     * Gets exitButton
     *
     * @return value of exitButton
     */
    public Button getExitButton() {
        return exitButton;
    }


    public Scene lessonOneEvent() {
        currentLesson = "output";
        return outputLessons.getLessonSuperClassArrayList().get(0).getScene();
    }

    public Scene lessonTwoEvent() {
        currentLesson = "variables";
        return variableLessons.getLessonSuperClassArrayList().get(0).getScene();
    }

    public Scene lessonThreeEvent() {
        currentLesson = "input";
        return inputLessons.getLessonSuperClassArrayList().get(0).getScene();
    }

    public Scene lessonFourEvent() {
        currentLesson = "conditional";
        return conditionalLesson.getLessonSuperClassArrayList().get(0).getScene();
    }

    public Scene lessonFiveEvent() {
        currentLesson = "loop";
        return loopLesson.getLessonSuperClassArrayList().get(0).getScene();
    }

    /**
     * Gets currentLesson
     *
     * @return value of currentLesson
     */
    public String getCurrentLesson() {
        return currentLesson;
    }

    /**
     * Gets outputLessons
     *
     * @return value of outputLessons
     */
    public OutputLessons getOutputLessons() {
        return outputLessons;
    }

    /**
     * Gets variableLessons
     *
     * @return value of variableLessons
     */
    public VariableLessons getVariableLessons() {
        return variableLessons;
    }

    public ConditionalLesson getConditionalLesson() {
        return conditionalLesson;
    }

    public InputLessons getInputLessons() {
        return inputLessons;
    }

    /**
     * Gets loopLesson
     *
     * @return value of loopLesson
     */
    public LoopLesson getLoopLesson() {
        return loopLesson;
    }
}
