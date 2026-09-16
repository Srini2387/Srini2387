import java.util.Objects;

public class Employee implements Comparable<Employee> {
	
	private int id;
	
	private String name;
	
	private String dept;
	

	public Employee(int id, String name, String dept) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
	}
	
	@Override
	public int compareTo(Employee other) {

	    // Compare ID
	    int result = Integer.compare(this.id, other.id);

	    if (result != 0) {
	        return result;
	    }

	    // Compare Name
	    result = this.name.compareTo(other.name);

	    if (result != 0) {
	        return result;
	    }

	 // Compare Department
	    return this.dept.compareTo(other.dept);
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDept() {
		return dept;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dept, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(dept, other.dept) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + "]";
	}

}
