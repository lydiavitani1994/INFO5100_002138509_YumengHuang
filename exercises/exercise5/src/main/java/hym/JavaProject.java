package hym;

/**
 * This is singleton.
 */
public class JavaProject implements Project{
    private static JavaProject javaProject = new JavaProject();

    private JavaProject() {}

    public static JavaProject getSingletonJavaProject() {
        return javaProject;
    }

    public void finish() {
        System.out.println("Java project finished!");
    }
}
