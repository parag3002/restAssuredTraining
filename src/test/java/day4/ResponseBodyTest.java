package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.JsonObject;

import dev.failsafe.internal.util.Assert;
import groovyjarjarpicocli.CommandLine.IExitCodeExceptionMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;


public class ResponseBodyTest 
{

	@Test(priority =1)
	public void responseTestiNthen()
	{
		//Response responseBody = 
		given()
				.contentType("application/json")
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.statusCode(200)
			.header("Content-Type","application/json; charset=utf-8")
			.body("book[4].author",equalTo("Parag Das"));
			//.log().all();
	
	}
	
	
	@Test(priority=2)
	public void responseTestTestNgAssertion()
	{
		
		Response responseBody = given()
			.contentType("application/json")
			
		.when()
			.get("http://localhost:3000/store");
		
		
		
		assertEquals(responseBody.getStatusCode(),200);
		System.out.println("PASSED Status code --->"+responseBody.getStatusCode());
		
		assertEquals(responseBody.header("Content-Type"),"application/json; charset=utf-8");
		System.out.println("PASSED | Heade Content-Type -->"+responseBody.header("Content-Type"));
	
		String responseData1 = responseBody.jsonPath().get("book[1].title").toString();
		assertEquals(responseData1,"Saying of Century");
		System.out.println("PASSED | title of certain book -->"+responseData1);
		
	
	}
	
	
	
	
	
	@Test(priority=3)
	public void responseTest() // capturing title from all book, or
	//capturing same key from each object in json body	
	{
		
		Response responseBody = 
		
		given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("https://reqres.in/api/users?page=2");
//			.get("https://gorest.co.in/public/v2/users/");
//		.then()
//			.log().all();
		

		
		
		assertEquals(responseBody.getStatusCode(),200);
		System.out.println("PASSED Status code --->"+responseBody.getStatusCode());
		
		assertEquals(responseBody.header("Content-Type"),"application/json; charset=utf-8");
		System.out.println("PASSED | Heade Content-Type -->"+responseBody.header("Content-Type"));
	
		String element1 = responseBody.jsonPath().get("data[1].email").toString();
		assertEquals(element1,"lindsay.ferguson@reqres.in");
		System.out.println("PASSED | title of certain book -->"+element1);
		
		
		JSONObject jsonObject = new JSONObject(responseBody.asString());
	
		for(int i=0;i<jsonObject.getJSONArray("data").length();i++)
		{
			String arrayElement = jsonObject.getJSONArray("data").getJSONObject(i).get("email").toString();
			System.out.println(arrayElement);
			
		}
		
		for(int i=0;i<jsonObject.getJSONArray("data").length();i++)
		{
			String element2 = jsonObject.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			System.out.println(element2);
		}
		
		
		boolean flag = false;
		for(int i=0;i<jsonObject.getJSONArray("data").length();i++)
		{
			String element2 = jsonObject.getJSONArray("data").getJSONObject(i).get("id").toString();
			System.out.println(element2);
			if(element2.equals("10"))
			{
				flag=true; 
				System.out.println("flag------>"+flag);
				break;
			}

		}
		assertEquals(flag, true);
		
		System.out.println("------------------------");
		int sum = 0;
		for(int i=0;i<jsonObject.getJSONArray("data").length();i++)
		{
			String ids = jsonObject.getJSONArray("data").getJSONObject(i).get("id").toString();
			System.out.println(ids);
			
			sum = sum + Integer.parseInt(ids);
			
		}
		System.out.println(sum);
		assertEquals(sum,57);
	}
	
	
}
