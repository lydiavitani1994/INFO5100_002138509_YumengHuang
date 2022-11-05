package hym;

import java.util.Arrays;

public class FullTimeStudent extends Student{
    private double[] examScores;

    public FullTimeStudent(int id, String name, double[] quizScores, double[] examScores) {
        super(id, name, quizScores);
        this.examScores = examScores;
    }

    public String getExamScores() {
        return Arrays.toString(examScores);
    }
}
