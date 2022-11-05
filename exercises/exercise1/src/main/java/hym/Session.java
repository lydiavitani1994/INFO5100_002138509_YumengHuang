package hym;

public class Session {
    private Student[] students;

    public Session(Student[] students) {
        this.students = students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public void printAvgQuizScoreOfEachStudent() {
        System.out.println("Average quiz score of students: ");

        for (Student student : students) {
            System.out.println(student.getId() + ": " + student.calculateAvgQuizScore());
        }
    }

    public void printQuizScoresAscending() {
        System.out.println("Quiz scores for each student in ascending order: ");

        for (Student student : students) {
            System.out.println(student.getName() + ": " + student.getQuizScoresAscending());
        }
    }

    public void printPartTimeStudentNames() {
        System.out.println("Part-time students in session: ");

        for (Student student : students) {
            if (student instanceof PartTimeStudent) {
                System.out.print(student.getName() + " ");
            }
        }
    }

    public void printFullTimeStudentExamScores() {
        System.out.println("Full-time students exam scores: ");

        for (Student student : students) {
            if (student instanceof FullTimeStudent) {
                System.out.println(student.getName() + ": " + ((FullTimeStudent) student).getExamScores());
            }
        }
    }
}
