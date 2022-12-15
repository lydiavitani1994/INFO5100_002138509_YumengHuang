package hym;

public class GithubRepoProjectDecorator extends ProjectDecorator {

    public GithubRepoProjectDecorator(Project decoratedProject) {
        super(decoratedProject);
    }

    @Override
    public void finish() {
        decoratedProject.finish();
        createGithubRepoOfProject(decoratedProject);
    }

    private void createGithubRepoOfProject(Project decoratedProject) {
        System.out.println("Github repo is created for project.");
    }
}
