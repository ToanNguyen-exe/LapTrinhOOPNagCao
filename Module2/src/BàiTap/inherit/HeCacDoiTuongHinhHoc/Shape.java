package inherit.HeCacDoiTuongHinhHoc;

public class Shape {
    private String color = "green";
    private boolean filledd = true;

    public Shape() {

    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filledd = filled;
    }

    public String getColor() {
        return color;

    }

    public void setColor(String color) {
        this.color = color;

    }

    public boolean isFilled() {
        return filledd;
    }

    public void setFilledd(boolean filledd) {
        this.filledd = filledd;
    }

    public String toString() {
        return "A Shape with color of "
                + getColor()
                + " and "
                + ((isFilled()) ? "filled" : "not filled");
    }
}
