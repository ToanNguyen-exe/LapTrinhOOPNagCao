package KeThua.Triangle;

import java.util.Scanner;

public class TestTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cạnh 1: ");
        double side1 = sc.nextDouble();
        System.out.print("Cạnh 2: ");
        double side2 = sc.nextDouble();
        System.out.print("Cạnh 3: ");
        double side3 = sc.nextDouble();

        sc.nextLine();
        System.out.print("Nhập màu: ");
        String color = sc.nextLine();

        Triangle triangle = new Triangle(side1, side2, side3, color);
        System.out.println("\nThông tin tam giác:");
        System.out.println("Màu: " + triangle.getColor());
        System.out.println("Chu vi: " + triangle.getPerimeter());
        System.out.println("Diện tích: " + triangle.getArea());
        System.out.println(triangle.toString());

        sc.close();
    }
}
