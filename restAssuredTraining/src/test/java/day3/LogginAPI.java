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

public class LogginAPI 
{

	@Test
	public void testLog()
	{
		given()
		
		.when()
			.get("https://www.google.com")
			
		.then()
			//.log().all();
			//.log().body();
			//.log().cookies();
			.log().headers();
		
	}
	
}
