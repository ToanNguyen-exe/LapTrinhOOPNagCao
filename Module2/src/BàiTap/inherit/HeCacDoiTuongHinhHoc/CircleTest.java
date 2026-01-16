package inherit.HeCacDoiTuongHinhHoc;

public class CircleTest {
    public static void main(String[] args) {
        circle circle = new circle();
        System.out.println(circle);
        circle = new circle(3.5);
        System.out.println(circle);
        circle = new circle(3.5, "indigo", false);
        System.out.println(circle);
    }
}
