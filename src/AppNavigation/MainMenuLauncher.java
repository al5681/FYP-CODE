package AppNavigation;

import elearning.LessonCollection;
import flowchartguicode.FlowchartGUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class servers as the launching point for the program, the methods simply control navigation through the app
 */
public class MainMenuLauncher extends Application {

    private MainMenuController mainMenuController = new MainMenuController();
    LessonSelectionController lessonSelectionController = new LessonSelectionController();
    FlowchartGUI flowchartGUI = new FlowchartGUI();
    LessonCollection currentLessonType;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainMenuController.getStage().setTitle("Version 1.2");
        mainMenuController.showStage();
        lessonButtonEvent();
        chatBuilderButtonEvent();
        chartBuilderExitButtonEvent();
        lessonExitButtonEvent();
        lessonOneStart();
        lessonTwoStart();
        lessonThreeStart();
        lessonFourStart();
        lessonFiveStart();
    }

    public void lessonButtonEvent() {
        mainMenuController.getLessonsButton().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(lessonSelectionController.getScene());

        });
    }

    public void chatBuilderButtonEvent() {
        mainMenuController.getChartBuilderButton().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(flowchartGUI.getFlowchartGUIScene());

        });
    }

    public void chartBuilderExitButtonEvent() {
        flowchartGUI.getExitButton().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(mainMenuController.getScene());
        });
    }

    public void lessonExitButtonEvent() {
        lessonSelectionController.getExitButton().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(mainMenuController.getScene());
        });
    }

    public void lessonOneStart() {
        lessonSelectionController.getLessonOne().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(lessonSelectionController.lessonOneEvent());
            getCurrentLessonType();

        });
    }

    public void lessonTwoStart() {
        lessonSelectionController.getLessonTwo().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(lessonSelectionController.lessonTwoEvent());
            getCurrentLessonType();
        });
    }

    public void lessonThreeStart() {
        lessonSelectionController.getLessonThree().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(lessonSelectionController.lessonThreeEvent());
            getCurrentLessonType();
        });
    }

    public void lessonFourStart() {
        lessonSelectionController.getLessonFour().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(lessonSelectionController.lessonFourEvent());
            getCurrentLessonType();
        });
    }

    public void lessonFiveStart() {
        lessonSelectionController.getLessonFive().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(lessonSelectionController.lessonFiveEvent());
            getCurrentLessonType();
        });
    }

    public void backButtonEvent() {
        currentLessonType.getLessonSuperClassArrayList().get(currentLessonType.getCurrentLesson()).getBackButton().setOnMouseClicked(e -> {
            currentLessonType.setCurrentLesson(currentLessonType.getCurrentLesson() - 1);
            mainMenuController.getStage().setScene(currentLessonType.getLessonSuperClassArrayList().get(currentLessonType.getCurrentLesson()).getScene());

        });
    }


    public void nextButtonEvent() {
        currentLessonType.getLessonSuperClassArrayList().get(currentLessonType.getCurrentLesson()).getNextButton().setOnMouseClicked(e -> {
            if (currentLessonType.getLessonSuperClassArrayList().get(currentLessonType.getCurrentLesson()).getNextButton().getText().equals("Complete")) {
                mainMenuController.getStage().setScene(mainMenuController.getScene());
                currentLessonType.setCurrentLesson(0);
            } else {
                currentLessonType.nextButtonEvent();
                mainMenuController.getStage().setScene(currentLessonType.getLessonSuperClassArrayList().get(currentLessonType.getCurrentLesson()).getScene());
                // call button events again
                nextButtonEvent();
                exitButtonEvent();
                backButtonEvent();
            }
        });
    }


    public void exitButtonEvent() {
        currentLessonType.getLessonSuperClassArrayList().get(currentLessonType.getCurrentLesson()).getExitButton().setOnMouseClicked(e -> {
            mainMenuController.getStage().setScene(mainMenuController.getScene());
            currentLessonType.setCurrentLesson(0);
        });
    }

    public void getCurrentLessonType() {
        if (lessonSelectionController.getCurrentLesson().equals("output")) {
            currentLessonType = lessonSelectionController.getOutputLessons();
        } else if (lessonSelectionController.getCurrentLesson().equals("variables")) {
            currentLessonType = lessonSelectionController.getVariableLessons();
        } else if (lessonSelectionController.getCurrentLesson().equals("input")) {
            currentLessonType = lessonSelectionController.getInputLessons();
        } else if (lessonSelectionController.getCurrentLesson().equals("conditional")) {
            currentLessonType = lessonSelectionController.getConditionalLesson();
        } else if (lessonSelectionController.getCurrentLesson().equals("loop")) {
            currentLessonType = lessonSelectionController.getLoopLesson();
        }
        nextButtonEvent();
        exitButtonEvent();
        backButtonEvent();
    }
}
