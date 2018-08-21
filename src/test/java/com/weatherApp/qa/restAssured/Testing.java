package com.weatherApp.qa.restAssured;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
 
public class Testing {
 
	@Test
	public void RegistrationSuccessful()
	{		
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
	 
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "User1"); // Cast
		requestParams.put("LastName", "Roy");
		requestParams.put("UserName", "user123098");
		requestParams.put("Password", "Password123@");
		requestParams.put("Email",  "user1@example.com");
		request.body(requestParams.toJSONString());
		Response response = request.post("/register");
		
		System.out.println("Response Body is =>  " + response.asString());
	 
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 201);
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println(successCode);
		Assert.assertEquals(successCode, "OPERATION_SUCCESS", "Incorrect Success code was returned");
	}
}