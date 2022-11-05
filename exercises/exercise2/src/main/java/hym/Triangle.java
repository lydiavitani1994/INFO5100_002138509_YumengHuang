package hym;

public class Triangle extends Shape{
    private double edge1;
    private double edge2;
    private double edge3;

    public Triangle(double edge1, double edge2, double edge3) {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
    }

    @Override
    public double calculatePerimeter() {
        return edge1 + edge2 + edge3;
    }
}
