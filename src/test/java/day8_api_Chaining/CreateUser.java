package day8_api_Chaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;

import dev.failsafe.internal.util.Assert;
import groovyjarjarpicocli.CommandLine.IExitCodeExceptionMapper;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl.HamcrestAssertionClosure;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;


public class CreateUser 
{

	Faker faker;
	int count;
	public CreateUser()
	{
		faker = new Faker();
	}
	
	@Test(priority=1)
	public void createUser()
	{
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("email", faker.internet().emailAddress());
		data.put("gender", faker.options().option("male","female"));
		data.put("status", faker.options().option("active","inactive"));
		
		String bearerToken = "Bearer b5d4fb3ee6dd407fcf12f52dd0ed6c26b15bcf6ceab3ff248f4a4629de273b49";
		
		
		
		given()
			.headers("Authorization",bearerToken)
			.contentType("application/json")
			.body(data.toString())
		
			
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
		
		.then()
			.statusCode(201)
			.log().all();
		
		
		count =count +1;
	switch(count)
	{
	case 10: break;
	default : createUser();
	}
	
	}
	
}
