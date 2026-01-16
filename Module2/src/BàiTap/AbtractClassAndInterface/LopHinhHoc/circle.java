package BàiTap.AbtractClassAndInterface.LopHinhHoc;

public class circle implements Resizeable {
    private double radius;

    public circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void resize(double percent) {
        radius += radius * percent / 100;
    }

}
