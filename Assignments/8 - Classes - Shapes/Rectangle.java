public class Rectangle extends Shape{
    protected double width;
    protected double height;

    public Rectangle(double x, double y, double width, double height) {
        super.setX(x);
        super.setY(y);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void drawShape() {
        System.out.println("we're drawing the shape starting at (" + super.getX() + ", " + super.getY() + ") with the color: " + super.getColor() + " and with fill set to: " + super.getFill());
    }
}
