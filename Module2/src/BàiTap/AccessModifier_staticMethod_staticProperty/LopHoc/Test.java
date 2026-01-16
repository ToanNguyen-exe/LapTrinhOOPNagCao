package BàiTap.AccessModifier_staticMethod_staticProperty.LopHoc;

public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Alice");
        student.setClasses("C03");
        System.out.println("Name: " + student.getName());
        System.out.println("Class: " + student.getClasses());


    }

}
