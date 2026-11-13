package in.jp.c.medium;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import in.jp.common.Employee;
import in.jp.common.EmployeeFactory;

/*
 * 
 * Sort employees by salary descending, then name ascending.
 */
public class _13_SortEmpBySalDescThenNameAsc {

	public static void main(String[] args) {
		//method-1
		List<Employee> sortedEmployees = EmployeeFactory.employees.stream()
		        .sorted((e1, e2) -> {
		            int salaryCompare = Double.compare(e2.getSalary(), e1.getSalary());

		            if (salaryCompare == 0) {
		                return e1.getName().compareTo(e2.getName());
		            }

		            return salaryCompare;
		        })
		        .collect(Collectors.toList());
		
		sortedEmployees.stream().forEach(emp -> {
			System.out.println(emp.getName()+", Salary -> "+emp.getSalary());	
		});
		
		System.out.println("###############################");
		
		//method-2
		List<Employee> sortedEmployees2 = EmployeeFactory.employees.stream()
		        .sorted(Comparator.comparing(emp -> ((Employee) emp).getSalary())
		                .reversed()
		                .thenComparing(emp -> ((Employee) emp).getName()))
		        .collect(Collectors.toList());
		sortedEmployees2.stream().forEach(emp -> {
			System.out.println(emp.getName()+", Salary -> "+emp.getSalary());	
		});
	}
}

