package gradle_study.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_study.dto.Department;
import gradle_study.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {
	private static TransactionService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// TransactionServiceTest를 수행하기 전에 수행
		service = new TransactionService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// TransactionServiceTest를 수행한 후에 수행
		service = null;
	}

	@After
	public void tearDown() throws Exception {
		//test메서드 끝날때마다 호출
		System.out.println();
	}

	@Test
	public void test01transAddTitleAndDepartment_FailTitle() {
		System.out.printf("%s()%n", "transAddTitleAndDepartment_FailTitle");
		
		Title title = new Title(1, "인턴");
		Department dept = new Department(5, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	
	@Test
	public void test02transAddTitleAndDepartment_FailDept() {
		System.out.printf("%s()%n", "transAddTitleAndDepartment_FailDept");
		
		Title title = new Title(6, "인턴");
		Department dept = new Department(1, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	
	@Test
	public void test03transAddTitleAndDepartment_FailBoth() {
		System.out.printf("%s()%n", "transAddTitleAndDepartment_FailBoth");
		
		Title title = new Title(1, "인턴");
		Department dept = new Department(1, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	
	@Test
	public void test04transAddTitleAndDepartment_Succecss() {
		System.out.printf("%s()%n", "transAddTitleAndDepartment_Succecss");
		
		Title title = new Title(6, "인턴");
		Department dept = new Department(5, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("commit", res);
	}
	///////////////////////////////////////////////////////////////
	
	@Test
	public void test05transRemoveTitleAndDepartment_FailTitle() {
		System.out.printf("%s()%n", "transRemoveTitleAndDepartment_FailTitle");
		
		Title title = new Title(0);
		Department dept = new Department(5);
		
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test06transRemoveTitleAndDepartment_FailDept() {
		System.out.printf("%s()%n", "transRemoveTitleAndDepartment_FailDept");
		
		Title title = new Title(6);
		Department dept = new Department(0);
		
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test07transRemoveTitleAndDepartment_FailBoth() {
		System.out.printf("%s()%n", "transRemoveTitleAndDepartment_FailBoth");
		
		Title title = new Title(0);
		Department dept = new Department(0);
		
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(0, res);
	}
	
	@Test
	public void test08transRemoveTitleAndDepartment_Succecss() {
		System.out.printf("%s()%n", "transRemoveTitleAndDepartment_Success");
		
		Title title = new Title(6);
		Department dept = new Department(5);
		
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(2, res);
	}
}
