package in.jp.b.hard;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import in.jp.common.Employee;
import in.jp.common.EmployeeFactory;

/*
 * 
 * Find the second-highest salary from a list of employees.
 */
public class _3_SecondHighestSalary {

	public static void main(String[] args) {
		EmployeeFactory.employees.stream().sorted(Comparator.comparingDouble(emp->emp.getSalary())).forEach(System.out::println);
									
		System.out.println("#############################");
		double secondHigestSalary = EmployeeFactory.employees.stream()
				.mapToDouble(Employee::getSalary)
				.distinct()
				.boxed()
				.sorted(Comparator.reverseOrder())
				.skip(1)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Second salary not found"));
		
		//collect ties 
		List<Employee> secondSalList = EmployeeFactory.employees.stream()
				.filter(emp -> emp.getSalary() == secondHigestSalary).collect(Collectors.toList());
		secondSalList.forEach(System.out::println);
		 
	}

}
