package elearning;

import elearning.Conditionals.IfStatement;

public class ConditionalLesson extends LessonCollection {
    IfStatement ifStatement = new IfStatement();

    public ConditionalLesson() {
        lessonSuperClassArrayList.add(ifStatement);
    }
}
