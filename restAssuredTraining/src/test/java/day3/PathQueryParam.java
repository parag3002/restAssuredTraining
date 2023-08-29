package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;

public class PathQueryParam 
{

	@Test
	public void testQueryPathParam()
	{
		
		
		String email = given()
			.pathParam("pathVariable1","users")
			.queryParam("page","2")
			.queryParam("id","5")
		
		.when()
			.get("https://reqres.in/api/{pathVariable1}")
			.jsonPath().getString("data.email");
		System.out.println(email);
		
//		.then()
//			.statusCode(200)
//			.body("data.email", equalTo("charles.morris@reqres.in"))
//			.log().all();
			
			
	}
	
}
