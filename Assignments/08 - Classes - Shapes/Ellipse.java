public class Ellipse extends Shape {
    protected double radius1;
    protected double radius2;

    public Ellipse(double x, double y, double r1, double r2) {
        super.setX(x);
        super.setY(y);
        this.radius1 = r1;
        this.radius2 = r2;
    }

    @Override
    public double getArea() {
        return radius1 * radius2 * 3.1415;
    }

    @Override
    public double getPerimeter() {
        return (2 * 3.1415) * (Math.sqrt(((radius1 * radius1) + (radius2 * radius2))/2));
    }

    @Override
    public void drawShape() {
        System.out.println("we're drawing the shape starting at (" + super.getX() + ", " + super.getY() + ") with the color: " + super.getColor() + " and with fill set to: " + super.getFill());
    }

    
}
