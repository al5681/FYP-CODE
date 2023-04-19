package elearning;

import java.util.ArrayList;

/**
 * A collection of lessons stored in an array list the user can then navigate though the lessons using next/back buttons
 *
 */
public class LessonCollection {
    protected ArrayList<LessonSuperClass> lessonSuperClassArrayList = new ArrayList<>();
    protected int currentLesson = 0;

    public void nextButtonEvent() {
        currentLesson++;
    }

    /**
     * Sets currentLesson
     *
     * @param currentLesson the value for currentLesson to be set to
     */
    public void setCurrentLesson(int currentLesson) {
        this.currentLesson = currentLesson;
    }

    /**
     * Gets currentLesson
     *
     * @return value of currentLesson
     */
    public int getCurrentLesson() {
        return currentLesson;
    }

    /**
     * Gets lessonSuperClassArrayList
     *
     * @return value of lessonSuperClassArrayList
     */
    public ArrayList<LessonSuperClass> getLessonSuperClassArrayList() {
        return lessonSuperClassArrayList;
    }

}
