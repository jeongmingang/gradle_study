package gradle_study.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_study.dao.impl.EmployeeDaoImpl;
import gradle_study.dto.Department;
import gradle_study.dto.Employee;
import gradle_study.dto.Title;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	@Before
	public void setUp() throws Exception {
	dao = EmployeeDaoImpl.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {	//tearDown=메소드 실행후 호출
		dao = null;
		System.out.println();
	}
	
	@Test
	public void test03selectEmployeeByall() {
		System.out.printf("%s()%n", "selectEmployeeByAll");
		List<Employee> EmployeeList = dao.selectEmployeeByAll();
		Assert.assertNotNull(EmployeeList);
		
//		titleList.stream().forEach(System.out::println);
		for(Employee e : EmployeeList) {
			System.out.println(e);
		}
	}

	@Test
	public void test04selectEmployeeByNo() {
		System.out.printf("%s()%n", "selectEmployeeByNo");
		Employee selEmp = new Employee(2106);
		Employee emp = dao.selectEmployeeByNo(selEmp);
		Assert.assertNotNull(emp);
		System.out.println(emp);
	}

	@Test
	public void test01InsertEmployee() {
		System.out.printf("%s()%n", "testInsertEmployee");
		Employee newEmp = new Employee(1004, "박규영", new Title(5), new Employee(1003), 2000000, new Department(3));	
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(newEmp));
	}

	@Test
	public void test02UpdateEmployee() {
		System.out.printf("%s()%n", "testUpdateEmployee");
		Employee newEmp = new Employee(1004, "천사", new Title(5), new Employee(4377), 2000000, new Department(3));	
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(newEmp));
	}

	@Test
	public void test06DeleteEmployee() {
		System.out.printf("%s()%n", "testDeleteEmployee");
		Employee newEmp = new Employee(1004);
		int res = dao.deleteEmployee(newEmp);
		Assert.assertEquals(1, res);
		dao.selectEmployeeByAll().stream().forEach(System.out::println);
	}
	
	@Test
	public void test05SelectEmployeeByAllJoin() {
		System.out.printf("%s()%n", "testSelectEmployeeByAllJoin");
		List<Employee> list = dao.selectEmployeeByAllJoin();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		}
	

}
