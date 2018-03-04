package testcases;

import org.testng.annotations.BeforeTest;

import com.jayway.restassured.RestAssured;

public class BaseTestBackEnd extends BaseSuite{
	
	@BeforeTest
	public void initialSetUp()
	{
		RestAssured.baseURI=prop.getProperty("employeeDB_URL");
		RestAssured.basePath="/employees";
	}
}
