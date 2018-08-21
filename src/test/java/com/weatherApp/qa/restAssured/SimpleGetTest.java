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
 
public class SimpleGetTest {
 
	@Test(priority=1)
	public void getWeatherDetailsTest()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
 
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
 
		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		// Response response = httpRequest.request(Method.GET, "/Kochi");
		
		// OR, Make a GET request call directly by using RequestSpecification.get() method.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("/New York");
 
		// Now let us print the body of the message to see what response
		// we have received from the server
		// String responseBody = response.getBody().asString();
		
		// OR, Response.asString method will directly return the content of the body
		// as String.
		System.out.println("Response Body is =>  " + response.asString());
 
	}
	
	@Test(priority=2)
	public void getValidStatusCodeTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
 
		// Get the status code from the Response. In case of 
		// a successful interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();
 
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode, 200, "Incorrect status code returned");
	}
	

	@Test(priority=3)
	public void getInvalidStatusCodeTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/78789798798");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400, "Incorrect status code returned");
	}
	
	@Test(priority=4)
	public void getWeatherStatusLineTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
		
		// Get the status line from the Response and store it in a variable called statusLine
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Incorrect status line returned");
	}
	
	//HEADER TESTS

	@Test(priority=5)
	public void iteratingOverHeadersTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
	 
		// Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();
	 
		// Iterate over all the Headers
		for(Header header : allHeaders)
		{
			System.out.println("Key: " + header.getName() + "," + " Value: " + header.getValue());
		}
	}

	@Test(priority=6)
	public void getWeatherHeadersTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
	 
		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);
	 
		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType =  response.header("Server");
		Assert.assertEquals(serverType /* actual value */, "nginx/1.14.0" /* expected value */);
	 
		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);
	}

	@Test(priority=7)
	public void getWeatherMessageBodyTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
	 
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
	 
		// By using the ResponseBody.asString() method, we can convert the  body
		// into the string representation.
		System.out.println("Response Body is: " + body.asString());
	}

	@Test(priority=8)
	public void getWeatherMessageBodyTest2()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Kochi"), true, "Response body does not contain Kochi");
	}
	
	@Test(priority=9)
	public void stringContainedInResponseBodyTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
	 
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
	 
		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Kochi"), true, "Response body does not contain Kochi");
	}
	
	@Test(priority=10)
	public void stringPresenceIgnoringCasingTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
	 
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
	 
		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
	 
		// convert the body into lower case and then do a comparison to ignore casing.
		Assert.assertEquals(bodyAsString.toLowerCase().contains("kochi"), true, "Response body does not contain Kochi");
	}

	@Test(priority=11)
	public void verifyCityInJsonResponseTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
	 
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("City");
	 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);
	 
		// Validate the response
		Assert.assertEquals(city, "Kochi", "Incorrect city name received in the Response");
	 
	}

	@Test(priority=12)
	public void displayAllNodesInWeatherAPI()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Kochi");
	 
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + jsonPathEvaluator.get("City"));
	 
		// Print the temperature node
		System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));
	 
		// Print the humidity node
		System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));
	 
		// Print weather description
		System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));
	 
		// Print Wind Speed
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));
	 
		// Print Wind Direction Degree
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
	}

	@Test(priority=13)
	public void RegistrationSuccessful()
	{		
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
	 
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "User3"); // Cast
		requestParams.put("LastName", "Roy");
		requestParams.put("UserName", "user123100");
		requestParams.put("Password", "Password123@");
		requestParams.put("Email",  "user3@example.com");
		request.body(requestParams.toJSONString());
		Response response = request.post("/register");
	 
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 201);
		/*JsonPath jp = response.jsonPath();
		String successCode = jp.get("SuccessCode");*/
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println(successCode);
		Assert.assertEquals(successCode, "OPERATION_SUCCESS", "Incorrect Success code was returned");
	}
	
	@Test(priority=14)
	public void getInsteadOfPostTest()
	{		
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
	 
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); // Cast
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");	
		requestParams.put("Email",  "sample2ee26d9@gmail.com");
	 
		request.body(requestParams.toJSONString());
		Response response = request.get("/register");
	 
		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
	 
		System.out.println("Response body: " + response.body().asString());
		
		Assert.assertEquals(response.getStatusCode(), 405);
		String faultId = response.jsonPath().get("FaultId");
		Assert.assertEquals(faultId, "Invalid method type found in Request.", "Incorrect Fault ID was returned");
	}
}