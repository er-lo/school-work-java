import java.awt.Color;

public class TestShape {
    public static void main(String[] args) {
        System.out.println("Hello welcome to Erick's Test Shape Program");
        System.out.println("We'll start out with building a circle");
        Circle c = new Circle(10, 10, 4);
        c.setColor(Color.GREEN);
        System.out.println("");
        System.out.println("Circle Area: " + c.getArea());
        System.out.println("Circle Perimeter: " + c.getPerimeter());
        c.drawShape();

        System.out.println("");
        System.out.println("Next a square");
        Square s = new Square(20, 0, 20);
        s.setColor(Color.BLUE);
        System.out.println("");
        System.out.println("Square Area: " + s.getArea());
        System.out.println("Square Perimeter: " + s.getPerimeter());
        s.drawShape();

        System.out.println("");
        System.out.println("Next a right triangle");
        RightTriangle t = new RightTriangle(40, 10, 20, 10, 4, 5);
        t.setColor(Color.RED);
        System.out.println("");
        System.out.println("RightTriangle Area: " + t.getArea());
        System.out.println("RightTriangle Perimeter: " + t.getPerimeter());
        t.drawShape();
    }
}
