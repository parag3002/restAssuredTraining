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


//https://github.com/DiUS/java-faker#fakers

public class FakerLibrary 
{
	
	@Test(priority=1)
	public void fakerLibrary() // to generate random data in runTime
	// wee need to add dependency for this in pom.xml
	{
		Faker faker = new Faker();
		
		System.out.println("first name-->"+faker.name().firstName());
		System.out.println("City-->"+faker.address().city());
		System.out.println("Beer-->"+faker.beer().name());
		System.out.println("Disease-->"+faker.medical().diseaseName());
		System.out.println("ip-->"+faker.internet().ipV4Address());
		System.out.println("Avatar-->"+faker.internet().avatar());
		System.out.println("Weather-->"+faker.weather().temperatureCelsius());
		System.out.println("credit card-->"+faker.business().creditCardNumber());
		System.out.println("color-->"+faker.color().name());
		

	}
}
