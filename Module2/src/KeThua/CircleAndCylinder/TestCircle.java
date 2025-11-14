package KeThua.CircleAndCylinder;

public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        System.out.println(c1);

        Circle c2 = new Circle(2.5);
        System.out.println(c2);

        Circle c3 = new Circle(3.0, "blue");
        System.out.println(c3);

        c3.setRadius(5.0);
        c3.setColor("green");
        System.out.println("After update: " + c3);
    }
}
