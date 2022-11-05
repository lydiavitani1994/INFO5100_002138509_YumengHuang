package hym;

public class Main {
    public static void main(String[] args) {
        Building building1 = new Building("1");
        Building building2 = new Building("2");
        Building building3 = new Building("3");

        Course course1 = new Course("1");
        Course course2 = new Course("2");
        Course course3 = new Course("3");

        Grade grade1 = new Grade("1");
        Grade grade2 = new Grade("2");
        Grade grade3 = new Grade("3");

        Homework homework1 = new Homework("1");
        Homework homework2 = new Homework("2");
        Homework homework3 = new Homework("3");

        Instructor instructor1 = new Instructor("1");
        Instructor instructor2 = new Instructor("2");
        Instructor instructor3 = new Instructor("3");

        Printer printer1 = new Printer("1");
        Printer printer2 = new Printer("2");
        Printer printer3 = new Printer("3");

        Quiz quiz1 = new Quiz("1");
        Quiz quiz2 = new Quiz("2");
        Quiz quiz3 = new Quiz("3");

        Student student1 = new Student("1");
        Student student2 = new Student("2");
        Student student3 = new Student("3");

        TA ta1 = new TA("1");
        TA ta2 = new TA("2");
        TA ta3 = new TA("3");

        Classroom classroom1 = new Classroom(
                "1",
                building1,
                1,
                1024L,
                new Course[] {course1, course2, course3},
                50,
                true,
                true
        );
        Classroom classroom2 = new Classroom(
                "2",
                building2,
                2,
                1024L,
                new Course[] {course1, course2, course3},
                50,
                true,
                true
        );
        Classroom classroom3 = new Classroom(
                "3",
                building3,
                3,
                1024L,
                new Course[] {course1, course2, course3},
                50,
                true,
                true
        );
    }
}
