package hym;

public class Rectangle extends Shape{

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculatePerimeter() {
        return (length + width) * 2;
    }
}
