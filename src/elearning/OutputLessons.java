package elearning;

import elearning.Output.HelloWorldLessonPrototype;
import elearning.Output.MultipleOutputsLesson;

public class OutputLessons extends LessonCollection {

    HelloWorldLessonPrototype helloWorldLessonPrototype = new HelloWorldLessonPrototype();
    MultipleOutputsLesson multipleOutputsLesson = new MultipleOutputsLesson();

    public OutputLessons() {
        lessonSuperClassArrayList.add(helloWorldLessonPrototype);
        lessonSuperClassArrayList.add(multipleOutputsLesson);
    }

}
