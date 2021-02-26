package gradle_study.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_study.dao.impl.DepartmentDaoImpl;
import gradle_study.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {	//tearDown=메소드 실행후 호출
		System.out.println();
	}
	
	@Test
	public void test04selectDepartmentByall() {
		System.out.printf("%s()%n", "selectDepartmentByAll");
		List<Department> departmentList = dao.selectDepartmentByAll();
		Assert.assertNotNull(departmentList);
		
//		titleList.stream().forEach(System.out::println);
		for(Department d : departmentList) {
			System.out.println(d);
		}
	}

	@Test
	public void test05selectDepartmentByNo() {
		System.out.printf("%s()%n", "selectDepartmentByNo");
		Department department = new Department(4);
		Department searchDepartment = dao.selectDepartmentByNo(department);
		Assert.assertNotNull(searchDepartment);
		System.out.println(searchDepartment);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n", "testInsertDepartment");
		Department newDepartment = new Department(5, "마케팅", 20);	
		int res = dao.insertDepartment(newDepartment);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s()%n", "testUpdateDepartment");
		Department newDepartment = new Department(5, "회계");	
		int res = dao.updateDepartment(newDepartment);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s()%n", "testDeleteDepartment");
		Department newDepartment = new Department(5);	
		int res = dao.deleteDepartment(newDepartment);
		Assert.assertEquals(1, res);
		dao.selectDepartmentByAll().stream().forEach(System.out::println);
	}

}
