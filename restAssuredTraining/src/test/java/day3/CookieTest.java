package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
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


public class CookieTest 
{
	@Test(priority=1)
	public void testCookie()
	{
		given()
		
		.when()
			.get("https://www.google.com")
		.then()
			.statusCode(200)
			//.cookie("AEC","Ad49MVGyJjlz1mKlK7Z8GLLjhEqv-9V-D1YwD0dCsrnLoaHvWMAuNlrKiA; expires=Sun, 25-Feb-2024 16:08:24 GMT; path=/; domain=.google.com; Secure; HttpOnly; SameSite=lax")
			//.cookie("1P_JAR","2023-08-29-16")
			.log().all();
		
	}
	
	
	@Test(priority=2)
	public void getCookieInfo()
	{
		
		Response response = given()
		
		
		.when()
			.get("https://www.google.com");
		
		String cookie_AEC = response.getCookie("AEC");
		
		System.out.println("Captured Cookie AEC ----> "+cookie_AEC);
		
		Map<String, String> cookiesDeposit = response.getCookies();
		
		System.out.println(cookiesDeposit.keySet());
		
		for(String s : cookiesDeposit.keySet())
		{
			System.out.println("--------------");
			System.out.println(response.getCookie(s));
			
		}
		
	}
	
}
