package hym;

import java.util.Arrays;
import java.util.Collections;

public class Student {
    private int id;
    private String name;
    private double[] quizScores;

    public Student(int id, String name, double[] quizScores) {
        this.id = id;
        this.name = name;
        this.quizScores = quizScores;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float calculateAvgQuizScore() {
        float sum = 0;

        for (double score : quizScores) {
            sum += score;
        }

        return sum / quizScores.length;
    }

    public String getQuizScoresAscending() {
        Arrays.sort(this.quizScores);
        return Arrays.toString(this.quizScores);
    }
}
