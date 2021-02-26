package gradle_study.dto;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager; //외래키
	private int salary;
	private Department dept; //외래키
	
	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}
	
	public int getEmpNo() {
		return empNo;
	}
	
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public Title getTitle() {
		return title;
	}
	
	public void setTitle(Title title) {
		this.title = title;
	}
	
	public Employee getManager() {
		return manager;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public Department getDept() {
		return dept;
	}
	
	public void setDept(Department dept) {
		this.dept = dept;
	}

//	@Override
//	public String toString() {
//		return String.format("[%s, %s, %s, %s, %s, %s]", empNo, empName, 
//				title.gettNo(), manager.empNo, salary, dept.getDeptNo());
//	}
	
	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s, %s, %s, %s, %s, %s, %s]", empNo, empName, 
				manager.empNo, manager.empName, salary, title.gettNo(), title.gettName(),
				dept.getDeptNo(), dept.getDeptName(), dept.getFloor());
	}
	
	
	
	
}
