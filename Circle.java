import java.io.Serializable;
public class Circle implements Serializable {
    private double radius; // Радиус круга
    public Circle(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
