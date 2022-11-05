package hym;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {
                new Triangle(3, 4, 5),
                new Rectangle(3, 5),
                new Square(3),
                new Circle(3)
        };

        Shape.name = "Shape";
        System.out.println(Shape.name);


        for (Shape shape : shapes) {
            System.out.println(shape.calculatePerimeter());
        }
    }
}
