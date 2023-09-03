package day5;

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
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;


public class XmlResponse 
{

	@Test(priority=1)
	public void testXmlResponseThen()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
			.log().all()
			.contentType("application/xml; charset=utf-8")
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].id", equalTo("11133"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name" ,equalTo("Developer"));
			
	}
	
	@Test(priority=2)
	public void testXMLresponseBody()
	{
		
		Response response = given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		//JSONObject jsonObject = new JSONObject(response.asString());
		assertEquals(response.getStatusCode(),200);
		assertEquals(response.header("Content-Type"),"application/xml; charset=utf-8");
		assertEquals(response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString(), "Developer");
		
		
	
		
	}
	
	
	@Test(priority=3)
	public void xmlInAnyOrder()
	{
		
		Response response = 
		
		 given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlObj = new XmlPath(response.asString());
		List<String> travelers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		// returns total number of travelers i.e. total number of list item, here travelers in xml is list
		assertEquals(travelers.size(), 10);
		System.out.println(travelers.size());
		System.out.println(travelers);

		// verify traveler name is present in response --
		
		List<String> travelersName = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean flag = false;
		for(String names : travelersName)
		{
			System.out.println(names);
			if(names.equalsIgnoreCase("asdasd"))
			{
				System.out.println("Name Match Found -->"+names);
				flag = true;
				break;
			}
		}
		assertEquals(flag, true); System.out.println("PASS : Name Found ><");
		
	}
	
}
