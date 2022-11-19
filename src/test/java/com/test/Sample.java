package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.CityList;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.GetCityList_Input_Pojo;
import com.pojo.address.GetCityList_Output_Pojo;
import com.pojo.address.GetStateList_Output_Pojo;
import com.pojo.address.GetUserAddress_Output_Pojo;
import com.pojo.address.StateList;
import com.pojo.address.UpdateUserAddress_Input_Pojo;
import com.pojo.address.UpdateuserAddress_Output_Pojo;
import com.pojo.login.Login_Output_Pojo;
import com.pojo.product.ProductSearch_Input_Pojo;
import com.pojo.product.ProductSearch_Output_Pojo;
import com.pojo.profile.ChangeProfilePic_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Sample extends BaseClass {
	String logtoken;
	int stateIdNum;
	String State_id;
	int cityid;
	String address_id;

	@Test(priority = 1)
	public void login() {

		// 1.header
		addHeader("accept", "application/json");
		// 2. Authentication
		basicAuth("suryamash9x3@gmail.com", "Surya@123");
		// 3. method type
		Response response = requestType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String message = login_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Login successfully", "verify Login successfully");

		logtoken = login_Output_Pojo.getData().getLogtoken();

	}

	@Test(priority = 4)
	public void addUserAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", " application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("surya", "p", "6369402173",
				"JH", stateIdNum, cityid, 103, "600097", "chennai", "Home");
		addBody(addUserAddress_Input_Pojo);

		Response response = requestType("POST", "https://omrbranch.com/api/addUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "verify status code");

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String message = addUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Address added successfully", "verify Address added successfully");
		int id = addUserAddress_Output_Pojo.getAddress_id();
		System.out.println(id);
		address_id = String.valueOf(id);

	}

	@Test(priority = 2)
	public void getStateList() {
		addHeader("accept", "application/json");
		Response response = requestType("GET", "https://omrbranch.com/api/stateList");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		GetStateList_Output_Pojo getStateList_Output_Pojo = response.as(GetStateList_Output_Pojo.class);

		ArrayList<StateList> listState = getStateList_Output_Pojo.getData();
		for (StateList eachstateList : listState) {
			String stateName = eachstateList.getName();

			if (stateName.equals("Tamil Nadu")) {
				stateIdNum = eachstateList.getId();
				State_id = String.valueOf(stateIdNum);
				System.out.println(stateIdNum);
				Assert.assertEquals(stateName, "Tamil Nadu", "verify state name is Tamil Nadu");
				break;
			}

		}
	}

	@Test(priority = 3)
	public void getCityList() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", " application/json");
		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		GetCityList_Input_Pojo getCityList_Input_Pojo = new GetCityList_Input_Pojo(State_id);
		addBody(getCityList_Input_Pojo);
		Response response = requestType("POST", "https://omrbranch.com/api/cityList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		GetCityList_Output_Pojo getCityList_Output_Pojo = response.as(GetCityList_Output_Pojo.class);
		ArrayList<CityList> listcityDetails = getCityList_Output_Pojo.getData();
		for (CityList eachcityList : listcityDetails) {
			String cityname = eachcityList.getName();
			if (cityname.equals("Dharmapuri")) {
				cityid = eachcityList.getId();
				System.out.println(cityid);

				Assert.assertEquals(cityname, "Dharmapuri", "verify city name is Dharmapuri");
				break;

			}

		}

	}

	@Test(priority = 5)
	public void UpdateUserAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_id,
				"surya", "p", "6369402173", "JH", stateIdNum, cityid, 103, "600097", "chennai", "Home");
		addBody(updateUserAddress_Input_Pojo);

		Response response = requestType("PUT", "https://omrbranch.com/api/updateUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		UpdateuserAddress_Output_Pojo updateuserAddress_Output_Pojo = response.as(UpdateuserAddress_Output_Pojo.class);
		String message = updateuserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Address updated successfully", "verify Address updated successfully");

	}

	@Test(priority = 6)
	public void getuserAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		Response response = requestType("GET", "https://omrbranch.com/api/getUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String message = getUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "OK", "verify getuseraddress OK");

	}

	@Test(priority = 7)
	public void DeleteUserAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(address_id);
		addBody(deleteUserAddress_Input_Pojo);

		Response response = requestType("DELETE", "https://omrbranch.com/api/deleteAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = deleteUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Address deleted successfully", "verify Address deleted successfully");

	}

	@Test(priority = 8)
	public void searchproduct() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		ProductSearch_Input_Pojo productSearch_Input_Pojo = new ProductSearch_Input_Pojo("nuts");
		addBody(productSearch_Input_Pojo);

		Response response = requestType("POST", "https://omrbranch.com/api/searchProduct");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		ProductSearch_Output_Pojo productSearch_Output_Pojo = response.as(ProductSearch_Output_Pojo.class);
		String message = productSearch_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "verify OK product search");
	}

	@Test(priority = 9)
	public void Changeprofilepic() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		addFormData("profile_picture", new File(
				"C:\\Users\\surya\\eclipse-workspace\\maven\\IMAGES\\3D-Nature-Desktop-Wallpapers-widescreen.jpg"));
		
		Response response = requestType("POST", "https://omrbranch.com/api/changeProfilePic");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");

		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		String message = changeProfilePic_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Profile updated Successfully", "verify Profile updated Successfully");

	}
}
