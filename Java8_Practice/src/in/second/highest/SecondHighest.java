package in.second.highest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import in.jp.common.Employee;
import in.jp.common.EmployeeFactory;

public class SecondHighest {

	public static void main(String[] args) {
		EmployeeFactory.employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).forEach(System.out::println);
		System.out.println("###############################################");
		
		Optional<Employee> max = EmployeeFactory.employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
		double firstSal = max.get().getSalary();
		Employee secondHighest = EmployeeFactory.employees.stream().filter(emp->emp.getSalary()<firstSal).sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).findFirst().orElse(null);
		System.out.println(secondHighest);
		System.out.println("###############################################");
		System.out.println("###############################################");
		Double double1 = EmployeeFactory.employees.stream().map(emp->emp.getSalary()).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
		System.out.println(double1);
		System.out.println("###############################################");
		System.out.println("###############################################");
		
		
		double[] arr = {23.2, 24.5, 12.3, 55.7, 22.3 , 12.1};
		Optional<Double> findFirst = Arrays.stream(arr).boxed().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
		System.out.println(findFirst.get());
	}
	
}
