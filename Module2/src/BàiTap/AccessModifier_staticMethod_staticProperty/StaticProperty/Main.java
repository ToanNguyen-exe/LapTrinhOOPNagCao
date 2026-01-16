package BàiTap.AccessModifier_staticMethod_staticProperty.StaticProperty;

public class Main {
    public static void main(String[]args){
        car car1=new car("Mazada 3 ","Skyactiv 3");
        System.out.println(car.numberOfCars);
        car car2=new car("Mazada 6 ","Skyactiv 6");
        System.out.println(car.numberOfCars);
    }
}
