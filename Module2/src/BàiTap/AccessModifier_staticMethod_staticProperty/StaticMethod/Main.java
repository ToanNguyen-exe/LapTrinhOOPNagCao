package BàiTap.AccessModifier_staticMethod_staticProperty.StaticMethod;

public class Main {
    public static void main(String[] args) {
        Student.change();
        Student s1 = new Student(111, "Hoang");
        Student s2 = new Student(222, "Hung");
        Student s3 = new Student(333, "Hieu");

        s1.display();
        s2.display();
        s3.display();
    }
}
