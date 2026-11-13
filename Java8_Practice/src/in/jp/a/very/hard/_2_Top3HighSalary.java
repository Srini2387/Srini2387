package in.jp.a.very.hard;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import in.jp.common.Employee;
import in.jp.common.EmployeeFactory;

/*
 * 
 * 	Find the top 3 highest-paid employees in each department.
 */
public class _2_Top3HighSalary {

	public static void main(String[] args) {
		Map<String,List<Employee>> deptWise = EmployeeFactory.employees.stream()
															.collect(Collectors.groupingBy(emp->emp.getDepartment()));
		System.out.println(deptWise);
		Map<String, List<Employee>> result = deptWise.entrySet()
											.stream()
											.collect(
													Collectors.toMap 	(
															entry -> entry.getKey(), 
															entry -> entry.getValue()
																	.stream()
																	.sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
																	.limit(3).collect(Collectors.toList())
																		)
													);
		
		result.forEach((dept, employee) -> {
			System.out.println("Dept : "+dept +" -> "+" "+employee);
		});
	}

}
