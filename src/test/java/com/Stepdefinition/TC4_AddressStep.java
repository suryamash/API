package com.Stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.GetUserAddress_Output_Pojo;
import com.pojo.address.UpdateUserAddress_Input_Pojo;
import com.pojo.address.UpdateuserAddress_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClass {
	Response response;

	@Given("User add header and bearer authorization for accessing adduserAddress endpoint")
	public void user_add_header_and_bearer_authorization_for_accessing_adduser_address_endpoint() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		Header h3 = new Header("Content-Type", " application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);
	}

	@When("user add request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_request_body_for_add_new_address_and(String String1, String String2, String String3,
			String String4, String String5, String String6, String String7, String String8, String String9,
			String String10) {
		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("surya", "p", "6369402173",
				"JH", TC1_LoginStep.globaldatas.getStateIdNum(), TC1_LoginStep.globaldatas.getCityid(), 103, "600097",
				"chennai", "Home");
		addBody(addUserAddress_Input_Pojo);
	}

	@When("User send {string} request for adduserAddress endpoint")
	public void user_send_request_for_adduser_address_endpoint(String type) {
		response = requestType(type, Endpoints.ADDUSERADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);

	}

	@Then("user verify the adduserAddress response message matches {string} and get the addressid")
	public void user_verify_the_adduser_address_response_message_matches_and_get_the_addressid(String expmessage) {
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String message = addUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		int id = addUserAddress_Output_Pojo.getAddress_id();
		String address_id = String.valueOf(id);
		TC1_LoginStep.globaldatas.setAddress_id(address_id);
		Assert.assertEquals("verify Address added successfully", expmessage, message);
	}

	@Given("User add header and bearer authorization for accessing updateUserAddress endpoint")
	public void user_add_header_and_bearer_authorization_for_accessing_update_user_address_endpoint() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		Header h3 = new Header("Content-Type", " application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);
	}

	@When("user add request body for add update address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_request_body_for_add_update_address_and(String address_id, String first_name, String last_name,
			String mobile, String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(
				TC1_LoginStep.globaldatas.getAddress_id(), "surya", "p", "6369402173", "JH",
				TC1_LoginStep.globaldatas.getStateIdNum(), TC1_LoginStep.globaldatas.getCityid(), 103, "600097",
				"chennai", "Home");
		addBody(updateUserAddress_Input_Pojo);

	}

	@When("User send {string} request for updateUserAddress endpoint")
	public void user_send_request_for_update_user_address_endpoint(String type) {
		response = requestType(type, Endpoints.UPDATEUSERADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);
	}

	@Then("user verify the updateUserAddress response message matches {string}")
	public void user_verify_the_update_user_address_response_message_matches(String expmessage) {
		UpdateuserAddress_Output_Pojo updateuserAddress_Output_Pojo = response.as(UpdateuserAddress_Output_Pojo.class);
		String message = updateuserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("verify Address updated successfully", expmessage, message);
	}

	@Given("User add header and bearer authorization for accessing getUserAddress endpoint")
	public void user_add_header_and_bearer_authorization_for_accessing_get_user_address_endpoint() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);
	}

	@When("User send {string} request for getUserAddress endpoint")
	public void user_send_request_for_get_user_address_endpoint(String type) {
		response = requestType(type, Endpoints.GETUSERADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);
	}

	@Then("user verify the getUserAddress response message matches {string}")
	public void user_verify_the_get_user_address_response_message_matches(String expmessage) {
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String message = getUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("verify getuseraddress OK", expmessage, message);
	}

	@Given("User add header and bearer authorization for accessing deleteAddress endpoint")
	public void user_add_header_and_bearer_authorization_for_accessing_delete_address_endpoint() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		Header h3 = new Header("Content-Type", " application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);
	}

	@When("User add request body for delete address {string}")
	public void user_add_request_body_for_delete_address(String string) {
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(
				TC1_LoginStep.globaldatas.getAddress_id());
		addBody(deleteUserAddress_Input_Pojo);
	}

	@When("User send {string} request for deleteAddress endpoint")
	public void user_send_request_for_delete_address_endpoint(String type) {
		response = requestType(type, Endpoints.DELETEADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);

	}

	@Then("user verify the deleteAddress response message matches {string}")
	public void user_verify_the_delete_address_response_message_matches(String getmessage) {
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = deleteUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("verify Address deleted successfully", getmessage, message);
	}

}
