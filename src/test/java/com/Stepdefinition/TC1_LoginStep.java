package com.Stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.globals.GlobalDatas;
import com.pojo.login.Login_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass {
	Response response;
	String logtoken;
	static GlobalDatas globaldatas = new GlobalDatas();

	@Given("User add header")
	public void user_add_header() {
		addHeader("accept", "application/json");

	}

	@When("User add basic authentication for login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		basicAuth(getPropertyFileValue("Username"), getPropertyFileValue("Password"));
	}

	@When("User send {string} request for login endpoint")
	public void user_send_request_for_login_endpoint(String type) throws FileNotFoundException, IOException {
		response = requestType(type, Endpoints.POSTMANBASICAUTHLOGIN);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		globaldatas.setStatusCode(statusCode);

	}

	@Then("User verify the login response body firstname present as {string} and get the logtoken saved")
	public void user_verify_the_login_response_body_firstname_present_as_and_get_the_logtoken_saved(
			String expfirstname) {
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String first_name = login_Output_Pojo.getData().getFirst_name();
		Assert.assertEquals("verify firstname in login endpoint", expfirstname, first_name);
		String logtoken = login_Output_Pojo.getData().getLogtoken();
		globaldatas.setLogtoken(logtoken);

	}

}
