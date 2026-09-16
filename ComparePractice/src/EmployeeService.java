import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeService {

	public static void main(String[] args) {

		//1 Collections.sort
		List<Employee> list = new ArrayList<>();
		list.add(new Employee(2, "Prabha", "ECE"));
		list.add(new Employee(5, "ASathish", "ECE"));
		list.add(new Employee(3, "Praveen", "ECE"));
		list.add(new Employee(1, "Nirun", "ECE"));
		list.forEach(System.out::println);
		System.out.println("Before sorting...");
		Collections.sort(list);
		System.out.println();
		System.out.println("After sorting...");
		list.forEach(System.out::println);
		
		//2 custom sort
		List<Employee> list2 = new ArrayList<>();
		list2.add(new Employee(2, "Prabha", "ECE"));
		list2.add(new Employee(5, "ASathish", "ECE"));
		list2.add(new Employee(3, "Praveen", "ECE"));
		list2.add(new Employee(1, "Nirun", "ECE"));
		System.out.println("Before sorting...");
		list2.forEach(System.out::println);
		Comparator<Employee> byName=  Comparator.comparing(emp->emp.getName()); 
//		Comparator<Employee> byId=  Comparator.comparingInt(emp->emp.getId()); 
		list2.sort(byName);
		System.out.println("After sorting...");
		list2.forEach(System.out::println);
		
		//
		Set<EmployeeWithoutEqualsHashCode> set = new HashSet<>();
		set.add(new EmployeeWithoutEqualsHashCode(2, "Prabha", "ECE"));
		set.add(new EmployeeWithoutEqualsHashCode(3, "Praveen", "ECE"));
		set.add(new EmployeeWithoutEqualsHashCode(3, "Praveen", "ECE"));
		set.add(new EmployeeWithoutEqualsHashCode(3, "Praveen", "ECE"));
		set.add(new EmployeeWithoutEqualsHashCode(1, "Nirun", "ECE"));
		set.add(new EmployeeWithoutEqualsHashCode(1, "Nirun", "ECE"));
		set.add(new EmployeeWithoutEqualsHashCode(1, "Nirun", "ECE"));
		set.add(new EmployeeWithoutEqualsHashCode(5, "Sathish", "ECE"));
		System.out.println(set.size());
		
		
		Set<Employee> set2 = new HashSet<>();
		set2.add(new Employee(2, "Prabha", "ECE"));
		set2.add(new Employee(3, "Praveen", "ECE"));
		set2.add(new Employee(3, "Praveen", "ECE"));
		set2.add(new Employee(3, "Praveen", "ECE"));
		set2.add(new Employee(1, "Nirun", "ECE"));
		set2.add(new Employee(1, "Nirun", "ECE"));
		set2.add(new Employee(1, "Nirun", "ECE"));
		set2.add(new Employee(5, "Sathish", "ECE"));
		System.out.println(set2.size());
		
		
	
	}

}
