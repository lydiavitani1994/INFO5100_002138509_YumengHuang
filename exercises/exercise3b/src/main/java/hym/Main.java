package hym;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Shape[] shapes = new Shape[] {
                new Triangle(3, 4, 5),
                new Rectangle(3, 5),
                new Square(3),
                new Circle(3)
        };

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(shapes[0]);
        oos.flush();
        byte[] triangleByteArray = baos.toByteArray();

        System.out.println(triangleByteArray);

        ByteArrayInputStream bais = new ByteArrayInputStream(triangleByteArray);
        Triangle triangle = (Triangle) new ObjectInputStream(bais).readObject();

        System.out.println(triangle.calculatePerimeter());
    }
}
