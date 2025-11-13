package AccessModifier_staticMethod_staticProperty.AccessModifier;

public class Circel {
    private double radius;
    private String color;
    public Circel() {
        radius = 1.0;
        color = "red";
    }
    public Circel(double r){
        radius = r;
        color = "red";
    }
    public Circel(double r, String c){
        radius = r;
        color = c;
    }
    public double getRadius(){
        return radius;
    }
    public double getArea(){
        return radius * radius * Math.PI;
    }
}

