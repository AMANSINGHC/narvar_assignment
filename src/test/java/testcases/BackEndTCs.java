package testcases;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class BackEndTCs extends BaseTestBackEnd{
	
	@Test(priority=1, description="Check if the status code is 200.")
	public void validateStatusCode() 
	{		
		given().when().get().then().assertThat().statusCode(200);
	}
	
	@Test(priority=2, description="Check if the response content type is in JSON format.")
	public void validateContentType()
	{
		given().when().get().then().assertThat().contentType(ContentType.JSON);
	}
	
	@Test(priority=3, description="Check if the respnse have firstName as Aman for empId as 610001.")
	@Parameters("id")
	public void validateFirstName(String id)
	{
		Response response=given().param("id", Integer.parseInt(id)).when().get().then().extract().response();
		
		Assert.assertEquals(response.jsonPath().get("firstName[0]"), "Aman");
	}
	
	@Test(priority=4, description="Add new employee detail and verify it.")
	public void addNewEmployeeDetails()
	{
		String details="{\n\t\"id\": 610003,\n\t\"firstName\": \"Shashwat\",\n\t\"middleName\": \"   \",\n\t"
				+ "\"lastName\": \"Shukla\",\n\t\"homeTown\": \"Allahabad\",\n\t\"currentCity\": \"Gurugram\",\n\t"
				+ "\"companyName\": \"EY\",\n\t\"rating\": 2\n}";
		
		given().header("Content-Type", "application/json").body(details).when().post().then().assertThat().statusCode(201);
	}
	
	@Test(priority=5, description="Update middleName as Kumar where id=610003")
	@Parameters({"idForUpdate", "middleName"})
	public void updateEmployeeDetails(String idForUpdate, String middleName)
	{
		String body="{\n\t\"id\": 610003,\n\t\"firstName\": \"Shashwat\",\n\t\"middleName\": \""+middleName+"\",\n\t"
				+ "\"lastName\": \"Shukla\",\n\t\"homeTown\": \"Allahabad\",\n\t\"currentCity\": \"Gurugram\",\n\t"
				+ "\"companyName\": \"EY\",\n\t\"rating\": 2\n}";
		
		System.out.println(body);
		
		Response response=given().header("Content-Type", "application/json").body(body).when().put("/"+Integer
				.parseInt(idForUpdate)).then().extract().response();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("middleName"), "Kumar");
	}
}
