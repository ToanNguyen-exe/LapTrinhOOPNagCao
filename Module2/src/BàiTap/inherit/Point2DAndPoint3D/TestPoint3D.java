package inherit.Point2DAndPoint3D;

public class TestPoint3D {
    public static void main(String[] args) {
        Point3D p3d1 = new Point3D();
        System.out.println("p3d1: " + p3d1);

        Point3D p3d2 = new Point3D(2.0f, 3.0f, 5.0f);
        System.out.println("p3d2: " + p3d2);

        p3d2.setX(10.0f);
        p3d2.setY(20.0f);
        p3d2.setZ(30.0f);
        System.out.println("Sau khi thay đổi p3d2: " + p3d2);

        p3d2.setXYZ(1.0f, 2.0f, 3.0f);
        System.out.println("Sau khi setXYZ p3d2: " + p3d2);

        float[] coords = p3d2.getXYZ();
        System.out.println("Tọa độ p3d2: x=" + coords[0] + ", y=" + coords[1] + ", z=" + coords[2]);
    }
}
