public class Triangle extends Shape {
    protected double base;
    protected double height;
    protected double side1;
    protected double side2;

    public Triangle(double x, double y, double base, double height, double side1, double side2) {
        super.setX(x);
        super.setY(y);
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public double getArea() {
        return (base * height) / 2;
    }

    @Override
    public double getPerimeter() {
        return base + side1 + side2;
    }

    @Override
    public void drawShape() {
        System.out.println("we're drawing the shape starting at (" + super.getX() + ", " + super.getY() + ") with the color: " + super.getColor() + " and with fill set to: " + super.getFill());
    }
}
