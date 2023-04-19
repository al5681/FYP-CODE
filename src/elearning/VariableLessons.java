package elearning;

import elearning.Variables.OutputNumbers;
import elearning.Variables.StoringStringsInVariables;

public class VariableLessons extends LessonCollection {
    StoringStringsInVariables storingStringsInVariables = new StoringStringsInVariables();
    OutputNumbers outputNumbers = new OutputNumbers();


    public VariableLessons() {
        lessonSuperClassArrayList.add(storingStringsInVariables);
        lessonSuperClassArrayList.add(outputNumbers);
    }




}
