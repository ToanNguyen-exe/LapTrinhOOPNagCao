package classAndObject.HinhChuNhat;

import java.util.Scanner;

public class ThucHanhHinhChuNhat {
    public static class Rectangle {
        double width, height;

        public Rectangle() {}

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getArea() {
            return this.width * this.height;
        }

        public double getPerimeter() {
            return 2 * (this.width + this.height);
        }

        public String display() {
            return "Rectangle{width=" + width + ", height=" + height + "}";
        }
    }

    public static class HinhChuNhat {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap chieu rong: ");
            double width = sc.nextDouble();
            System.out.print("Nhap chieu dai: ");
            double height = sc.nextDouble();

            Rectangle rect = new Rectangle(width, height);
            System.out.println("Dien tich hinh chu nhat: " + rect.getArea());
            System.out.println("Chu vi hinh chu nhat: " + rect.getPerimeter());
            System.out.println("Thong tin hinh chu nhat: " + rect.display());
            sc.close();
        }
    }
}
