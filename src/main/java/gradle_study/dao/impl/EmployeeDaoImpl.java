package gradle_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_study.dao.EmployeeDao;
import gradle_study.dto.Department;
import gradle_study.dto.Employee;
import gradle_study.dto.Title;
import gradle_study.util.JdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private static EmployeeDaoImpl instance = new EmployeeDaoImpl();

	public static EmployeeDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	private EmployeeDaoImpl() {
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select empno, empname, title, manager, salary, dept from employee";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
//				System.out.println(list.size());
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empNo");
		String empName = rs.getString("empName");
		Title title = new Title(rs.getInt("title"));
		Employee manager = new Employee(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("dept"));
	
		try {
			title.setName(rs.getString("title_name"));
		}catch (SQLException e) {}
		
		try {
			manager.setEmpName(rs.getString("manager_name"));
		} catch(SQLException e) {}
		
		try {
			dept.setDeptName(rs.getString("deptName"));
			dept.setFloor(rs.getInt("Floor"));
		} catch(SQLException e) {}
			
		try {
			dept.setDeptName(rs.getString("deptName"));
			dept.setFloor(rs.getInt("floor"));
			title.setName(rs.getString("tname"));
			manager.setEmpName(rs.getString("mgrname"));
		} catch (SQLException e) {
	}		
		
		return new Employee(empNo, empName, title, manager, salary, dept);
}

	@Override
	public Employee selectEmployeeByNo(Employee employee) {
		String sql = "select empno, empname, manager, mgrname, salary, " + 
					 "		 title, tname, dept, deptname, floor " +
					 "	from vm_employee" +
					 " where empno = ?";
//		String sql = "select empno, empname, title as title_no, " + 
//					"		 manager as manager_no, salary, dept as deptNo" + 
//					" 	from employee" +
//					" where empno = ?";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().gettNo());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDept().getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee " +
					 "	set empname = ?, title = ?, manager = ?, salary = ?, dept = ? " +
					 " where empno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, employee.getEmpName());
			pstmt.setInt(2, employee.getTitle().gettNo());	   //Title객체의 tNo를 불러옴
			pstmt.setInt(3, employee.getManager().getEmpNo()); //Employee객체의 EmpNo를 불러옴
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getDept().getDeptNo());   //Department객체의 DeptNo를 불러옴
			pstmt.setInt(6, employee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		String sql = "delete from employee where empno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Employee> selectEmployeeByAllJoin() {
		String sql = "select empno, empname, manager, mgrname, salary, " + 
					 "		 title, tname, dept, deptname, floor " +
					 "	from vm_employee";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
