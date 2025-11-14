package KeThua.CircleAndCylinder;

public class TestCylinder {
    public static void main(String[] args) {
        Cylinder cy1 = new Cylinder();
        System.out.println(cy1);

        Cylinder cy2 = new Cylinder(5.0);
        System.out.println(cy2);

        Cylinder cy3 = new Cylinder(2.5, 10.0);
        System.out.println(cy3);

        Cylinder cy4 = new Cylinder(3.0, 7.0, "yellow");
        System.out.println(cy4);

        cy4.setHeight(12.0);
        System.out.println("After update: " + cy4);
    }
}
