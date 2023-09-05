package day6;

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
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;

//json to schema generator
//https://www.liquid-technologies.com/online-json-to-schema-converter

public class SchemaValidationClass 
{

	@Test(priority=1)
	public void jsonSchema_Test()
	{
		
		given()
		
		
		.when()
			.get("https://gorest.co.in/public/v2/users/")
		
		.then()
			.statusCode(200)
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"))
			.log().all();
			
	}
	
	@Test(priority=2)
	public void xmlSchema_Test()
	{
		
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
			
		.then()
			.statusCode(200)
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("travelerSchema.xsd"))
			.log().body();
	}
	
}
