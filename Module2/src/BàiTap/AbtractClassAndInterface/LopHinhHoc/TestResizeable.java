package BàiTap.AbtractClassAndInterface.LopHinhHoc;

import java.util.Random;

public class TestResizeable {
    public static void main(String[] args) {
        Random rand = new Random();

        Object[] shapes = new Object[3];
        shapes[0] = new circle(5);
        shapes[1] = new Rectangle(4, 6);
        shapes[2] = new Square(5);

        for (Object shape : shapes) {
            double percent = rand.nextInt(100) + 1;

            System.out.println("----------------------------");
            System.out.println("Resize: " + percent + "%");

            if (shape instanceof circle) {
                circle c = (circle) shape;
                System.out.println("Before: " + c.getArea());
                c.resize(percent);
                System.out.println("After:  " + c.getArea());
            }

            if (shape instanceof Rectangle && !(shape instanceof Square)) {
                Rectangle r = (Rectangle) shape;
                System.out.println("Before: " + r.getArea());
                r.resize(percent);
                System.out.println("After:  " + r.getArea());
            }

            if (shape instanceof Square) {
                Square s = (Square) shape;
                System.out.println("Before: " + s.getArea());
                s.resize(percent);
                System.out.println("After:  " + s.getArea());
            }
        }
    }
}
