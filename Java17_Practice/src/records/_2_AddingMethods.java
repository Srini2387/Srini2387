package records;

public class _2_AddingMethods {

}

record Rectangle(double length, double width) {

    public double area() {
        return length * width;
    }
}
