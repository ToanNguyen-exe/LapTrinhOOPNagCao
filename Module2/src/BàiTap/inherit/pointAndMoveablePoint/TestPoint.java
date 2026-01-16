package inherit.pointAndMoveablePoint;

public class TestPoint {
    public static void main(String[] args) {
        Point p1 = new Point();
        System.out.println("Điểm p1: " + p1);

        Point p2 = new Point(3.5f, 7.2f);
        System.out.println("Điểm p2: " + p2);

        p2.setX(5.0f);
        p2.setY(10.0f);
        System.out.println("Sau khi thay đổi p2: " + p2);

        float[] coords = p2.getXY();
        System.out.println("Tọa độ p2: x=" + coords[0] + ", y=" + coords[1]);
    }
}
