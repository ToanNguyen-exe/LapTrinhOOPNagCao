package inherit.pointAndMoveablePoint;

public class TestMovablePoint {
    public static void main(String[] args) {
        MovablePoint mp1 = new MovablePoint();
        System.out.println("mp1: " + mp1);

        MovablePoint mp2 = new MovablePoint(1.5f, 2.5f);
        System.out.println("mp2: " + mp2);

        MovablePoint mp3 = new MovablePoint(5.0f, 10.0f, 1.0f, 1.5f);
        System.out.println("mp3 trước khi move: " + mp3);

        mp3.move();
        System.out.println("mp3 sau khi move: " + mp3);

        mp3.move().move(); // di chuyển thêm 2 lần
        System.out.println("mp3 sau khi move 3 lần: " + mp3);
    }
}
