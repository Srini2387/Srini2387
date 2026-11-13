package in.jp.a.very.hard;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import in.jp.common.Employee;
import in.jp.common.EmployeeFactory;

/*
 * 
 * Group employees by department and find the highest-paid employee in each department.
 */
public class _1_EachDeptHighestSal {

	public static void main(String[] args) {
		//method-1
		Map<String,List<Employee>> deptWise = EmployeeFactory.employees.stream()
															.collect(Collectors.groupingBy(emp->emp.getDepartment()));
		System.out.println(deptWise);
		Map<String, Employee> result = deptWise.entrySet()
											.stream()
											.collect(
													Collectors.toMap 	(
															entry -> entry.getKey(), 
															entry -> entry.getValue()
																	.stream()
																	.max(Comparator.comparingDouble(emp -> emp.getSalary()))
																	.orElseThrow(NoSuchElementException ::new)
																		)
													);
		
		result.forEach((dept, employee) -> {
			System.out.println("Dept : "+dept +" -> "+" "+employee);
		});
		
		
		//method-2
		Map<String, Optional<Employee>> res =
				EmployeeFactory.employees.stream()
			        .collect(Collectors.groupingBy(
			            e -> e.getDepartment(),
			            Collectors.maxBy(
			                Comparator.comparing(e -> e.getSalary())
			            )
			        ));
		res.forEach((dept, employee) -> {
			System.out.println("Dept : "+dept +" -> "+" "+employee);
		});
	}

}
