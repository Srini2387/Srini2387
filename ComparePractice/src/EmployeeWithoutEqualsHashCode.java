public class EmployeeWithoutEqualsHashCode implements Comparable<EmployeeWithoutEqualsHashCode> {
	
	private int id;
	
	private String name;
	
	private String dept;
	

	public EmployeeWithoutEqualsHashCode(int id, String name, String dept) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
	}
	
	@Override
	public int compareTo(EmployeeWithoutEqualsHashCode other) {

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
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + "]";
	}

}
