package hym;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[] {
            new FullTimeStudent(1, "s1", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}, new double[] {87.6, 69.2}),
                new FullTimeStudent(2, "s2", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}, new double[] {97.3, 90.0}),
                new FullTimeStudent(3, "s3", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}, new double[] {87.5, 78.0}),
                new PartTimeStudent(4, "s4", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(5, "s5", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(6, "s6", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(7, "s7", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(8, "s8", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(9, "s9", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(10, "s10", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(11, "s11", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(12, "s12", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(13, "s13", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(14, "s14", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(15, "s15", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(16, "s16", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(17, "s17", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(18, "s18", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(19, "s19", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 0.0, 5.4, 9.5, 9.8}),
                new PartTimeStudent(20, "s20", new double[] {8.0, 9.0, 7.2, 8.1, 5.1, 6.7, 9.1, 4.5, 3.2, 6.7, 10.0, 10.0, 5.4, 9.5, 9.8}),
        };

        Session session = new Session(students);

        session.printAvgQuizScoreOfEachStudent();
        session.printFullTimeStudentExamScores();
        session.printQuizScoresAscending();
        session.printPartTimeStudentNames();
    }
}
