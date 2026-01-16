package BàiTap.AbtractClassAndInterface.LopHinhHoc;

public class Square extends Rectangle {
    private double side;

    public Square(double side) {
        super(side, side);
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public void resize(double percent) {
        side += side * percent / 100;
    }
}
