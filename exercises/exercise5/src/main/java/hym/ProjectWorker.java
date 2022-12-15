package hym;

/**
 * This is facade.
 */
public class ProjectWorker {
    private Project javaProject;
    private Project pythonProject;
    private Project pythonProjectWithGithubRepo;

    public ProjectWorker() {
        javaProject = JavaProject.getSingletonJavaProject();
        pythonProject = new PythonProject();
        pythonProjectWithGithubRepo = new GithubRepoProjectDecorator(new PythonProject());
    }

    public void finishJavaProject() {
        javaProject.finish();
    }

    public void finishPythonProject() {
        pythonProject.finish();
    }

    public void finishPythonProjectWithGithubRepo() {
        pythonProjectWithGithubRepo.finish();
    }
}
