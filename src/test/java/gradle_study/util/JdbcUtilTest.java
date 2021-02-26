package gradle_study.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.Connection;

// Jnuit테스트(단위 테스트)
public class JdbcUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception { //1번
		System.out.printf("%s()%n", "setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {  //5번
		System.out.printf("%s()%n", "tearDownAfterClass");
	}

	@Before
	public void setUp() throws Exception { //2번
		System.out.printf("%s()%n", "setUp");
	}

	@After
	public void tearDown() throws Exception {  //4번
		System.out.printf("%s()%n", "tearDown");
	}

	@Test
	public void testGetConnection() {  //3번
		System.out.printf("%s()%n", "testGetConnection");
		Connection con = (Connection) JdbcUtil.getConnection();
		System.out.println("con > " + con);
		Assert.assertNotNull(con);
	}

}
