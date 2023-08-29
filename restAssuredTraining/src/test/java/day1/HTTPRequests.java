package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import groovy.util.logging.Log;

public class HTTPRequests 
{
	int id;
	
	@Test(priority=1)
	public void listSingleUser()
	{
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}
	
	
	
	@Test(priority=2, dependsOnMethods = {"listSingleUser"})
	public void createUser()
	{
		HashMap data = new HashMap();
		data.put("name", "Sunny");
		data.put("job", "service");
		
		
	id = given(). // this id is integer variable declared in class level
		
		contentType("application/json").body(data)
		
		
	.when()
		.post("https://reqres.in/api/users/api/users")
		.jsonPath().getInt("id"); // this id is referring to JSON response id
	
		
	System.out.println("Captured id --> "+id);
	
//	.then()
//		.statusCode(201)
//		.log().all();
		
	}
	
	@Test(priority=3,dependsOnMethods = {"createUser"})
	public void updateUser()
	{
		
		HashMap data = new HashMap();
		data.put("name", "Someone");
		data.put("job", "business");
		
		given()
		
			.contentType("application/json")
			.body(data)
		
		.when()
		
			.put("https://reqres.in/api/users/api/users/"+id)
		
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	
	@Test(priority=3,dependsOnMethods = {"updateUser"})
	public void deleteUser()
	{
		given()
		
		
		.when()
			
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
			System.out.println("<<---------Deleted Successfully-------------->>");
		
		
	}
	
}
