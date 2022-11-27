package hym;

public class ProjectDecorator implements Project {
    protected Project decoratedProject;

    public ProjectDecorator(Project decoratedProject) {
        this.decoratedProject = decoratedProject;
    }

    public void finish() {
        decoratedProject.finish();
    }
}
