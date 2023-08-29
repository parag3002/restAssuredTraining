package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;


public class HeaderTest 
{
	
	@Test(priority=1)
	public void testHeader()
	{
		
		given()
		
		
		.when()
			.get("https://www.google.com")
		
		.then()
			.statusCode(200)
			//.log().all()
			.log().headers()
			.log().cookies()
			.log().body()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Server","gws");
	}
	
	
	@Test(priority=2)
	public void getHeaderInfo()
	{
		Response response = given()
		
		.when()
			.get("https://www.google.com");
		
		String singleHeader = response.getHeader("Content-Type");
		System.out.println("Content-Type ----->"+singleHeader);
		
		Headers allHeaders = response.getHeaders(); // io.restassured.http
		
		for(Header h : allHeaders)
		{
			System.out.println();
			System.out.println("Name->"+h.getName()+" | "+"Value->"+h.getValue());
		}
	}

}






