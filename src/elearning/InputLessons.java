package elearning;

import elearning.UserInput.NumbesrFromUser;
import elearning.UserInput.TextFromUser;

public class InputLessons extends LessonCollection {
    TextFromUser textFromUser = new TextFromUser();
    NumbesrFromUser numbesrFromUser = new NumbesrFromUser();

    public InputLessons() {
        lessonSuperClassArrayList.add(textFromUser);
        lessonSuperClassArrayList.add(numbesrFromUser);
    }


}
