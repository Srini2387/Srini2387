package in.jp.b.hard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * 
 * Convert a list into a map and handle duplicate keys safely.
 */
public class _10_HandleValue {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
			    new Employee(101, "John"),
			    new Employee(102, "David"),
			    new Employee(101, "Smith"),
			    new Employee(103, "Peter")
			);
		
		Map<Integer, String> employeeMap = employees.stream()
			    .collect(Collectors.toMap(
			        Employee::getId,
			        Employee::getName,
			        (oldValue, newValue) -> newValue
			    ));

			System.out.println(employeeMap);
	}

}

class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

