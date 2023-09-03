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



public class FileUpload 
{

	@Test(priority=1)
	public void singleFileUpload()
	{
		File myFileToUpload = new File(".\\TestUpload_1.txt");
		
		given()
			.multiPart("file",myFileToUpload) // key and pair, in postman also under body--form data we need to specify "file" and then browse the file
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		
		.then()
			.log().all()
			.statusCode(200)
			.body("fileName", equalTo("TestUpload_1.txt"));
		
	}
	
}
