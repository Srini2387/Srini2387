package in.jp.common;

import java.util.Arrays;
import java.util.List;

public class EmployeeFactory {
	
	public static List<Employee> employees = Arrays.asList(
		    new Employee(1, "Alice", "IT", 80000),
		    new Employee(2, "Bob", "HR", 60000),
		    new Employee(3, "Charlie", "IT", 95000),
		    new Employee(4, "David", "HR", 75000),
		    new Employee(5, "Eva", "Finance", 90000),
		    new Employee(6, "Frank", "Finance", 85000),
		    new Employee(7, "Grace", "IT", 95000)
		);
}