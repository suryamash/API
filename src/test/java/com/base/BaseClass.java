package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	RequestSpecification reqspec;
	Response response;

	public void addHeader(String key, String value) {
		reqspec = RestAssured.given().header(key, value);
	}

	public void addPathParam(String key, String value) {
		reqspec = reqspec.pathParam(key, value);
	}

	public void addBody(String body) {
		reqspec = reqspec.body(body);
	}

	public Response requestType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqspec.log().all().get(endpoint);
			// response = reqspec.get(endpoint);
			break;

		case "POST":
			response = reqspec.log().all().post(endpoint);
			// response = reqspec.post(endpoint);
			break;

		case "PUT":
			response = reqspec.log().all().put(endpoint);
			// response = reqspec.put(endpoint);
			break;

		case "DELETE":
			response = reqspec.log().all().delete(endpoint);
			// response = reqspec.delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String getResBodyAsString(Response response) {
		String asString = response.asString();
		return asString;
	}

	public String getResBodyAsprettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}

	public void basicAuth(String username, String password) {
		reqspec = reqspec.auth().preemptive().basic(username, password);

	}

	public void addHeaders(Headers headers) {
		reqspec = RestAssured.given().headers(headers);
	}

	public void addBody(Object body) {
		reqspec = reqspec.body(body);

	}

	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getprojectloc() + "\\Config\\config.properties"));
		return (String) properties.get(key);

	}

	public static String getprojectloc() {
		String path = System.getProperty("user.dir");
		return path;

	}
	
	public void addFormData(String key,File value) {
		reqspec = reqspec.multiPart(key,value);
	    
	}

}
