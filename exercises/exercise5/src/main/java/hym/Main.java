package hym;

/*
* Design pattern used in this exercise: Facade, Decorator and Singleton
*
* */
public class Main {
    public static void main(String[] args) {
        ProjectWorker projectWorker = new ProjectWorker();

        System.out.println("Finishing singleton java project...");
        projectWorker.finishJavaProject();

        System.out.println("Finishing python project...");
        projectWorker.finishPythonProject();

        System.out.println("Finishing python project and it's Github repo...");
        projectWorker.finishPythonProjectWithGithubRepo();
    }
}
