package day7;

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

public class AuthenticationAPI 
{

	@Test(priority=1)
	public void basicAuthentication()
	{
		given()
			.auth().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			//.statusCode(401) .statusLine("HTTP/1.1 401 Unauthorized")
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	
	
	
	@Test(priority=2)
	public void digestAuthentication()
	{
		given()
			.auth().digest("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			//.statusCode(401) .statusLine("HTTP/1.1 401 Unauthorized")
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	
	
	
	@Test(priority=3)
	public void preemptiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman","password")
			//preemptive is combination of basic and digest
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			//.statusCode(401) .statusLine("HTTP/1.1 401 Unauthorized")
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	
	@Test(priority=4)
	public void bearerTokenAuth()
	{
		String bearerToken = "Bearer b5d4fb3ee6dd407fcf12f52dd0ed6c26b15bcf6ceab3ff248f4a4629de273b49";
		
		given()
			//b5d4fb3ee6dd407fcf12f52dd0ed6c26b15bcf6ceab3ff248f4a4629de273b49
			.headers("Authorization",bearerToken)
		
		.when()
			.get("https://gorest.co.in/public/v2/users/")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	@Test(priority=5)
	public void oauth_02_Auth()
	{
		
		
		given()
			//.auth().oauth2()
		
		.when()
			.get("https://gorest.co.in/public/v2/users/")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	@Test(priority=6)
	public void apiKeyAuth()
	{
		
		
		given()
			//.auth().oauth2()
		
		.when()
			.get("https://gorest.co.in/public/v2/users/")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	
}
