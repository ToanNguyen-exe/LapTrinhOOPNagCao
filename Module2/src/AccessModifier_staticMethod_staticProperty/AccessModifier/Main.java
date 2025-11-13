package AccessModifier_staticMethod_staticProperty.AccessModifier;

public class Main {
    public static void main(String[] args) {
        Circel circel1 = new Circel();
        System.out.println("Radius: " + circel1.getRadius());
        System.out.println("Area: " + circel1.getArea());

        Circel circel2 = new Circel(2.0);
        System.out.println("Radius: " + circel2.getRadius());
        System.out.println("Area: " + circel2.getArea());

        Circel circel3 = new Circel(3.0, "blue");
        System.out.println("Radius: " + circel3.getRadius());
        System.out.println("Area: " + circel3.getArea());
    }
}
