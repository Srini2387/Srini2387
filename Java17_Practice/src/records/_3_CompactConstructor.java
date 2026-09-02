package records;

public class _3_CompactConstructor {

}

record Employee(String name, double salary) {

    public Employee {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
    }
}
