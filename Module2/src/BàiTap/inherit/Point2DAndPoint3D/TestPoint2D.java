package inherit.Point2DAndPoint3D;

public class TestPoint2D {
    public static void main(String[] args) {
        Point2D p1 = new Point2D();
        System.out.println("p1: " + p1);

        Point2D p2 = new Point2D(3.5f, 7.2f);
        System.out.println("p2: " + p2);

        p2.setX(5.0f);
        p2.setY(10.0f);
        System.out.println("Sau khi thay đổi p2: " + p2);

        float[] coords = p2.getXY();
        System.out.println("Tọa độ p2: x=" + coords[0] + ", y=" + coords[1]);
    }
}
