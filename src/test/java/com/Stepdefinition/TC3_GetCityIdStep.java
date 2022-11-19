package com.Stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.CityList;
import com.pojo.address.GetCityList_Input_Pojo;
import com.pojo.address.GetCityList_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_GetCityIdStep extends BaseClass {
	Response response;

	@Given("User add header for cityList endpoint")
	public void userAddHeaderForCityListEndpoint() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", " application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);
	}

	@When("User add stateId to get cityList {string}")
	public void userAddStateIdToGetCityList(String State_id) {
		GetCityList_Input_Pojo getCityList_Input_Pojo = new GetCityList_Input_Pojo(TC1_LoginStep.globaldatas.getState_id());
		addBody(getCityList_Input_Pojo);
	}

	@When("User send {string} request for cityList endpoint")
	public void userSendRequestForCityListEndpoint(String type) {
		response = requestType(type, Endpoints.CITYLIST);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);
	}

	@Then("User verify the cityList response body name present as {string} and get the cityId of Dharmapuri")
	public void userVerifyTheCityListResponseBodyNamePresentAsAndGetTheCityIdOfDharmapuri(String expcityname) {
		GetCityList_Output_Pojo getCityList_Output_Pojo = response.as(GetCityList_Output_Pojo.class);
		ArrayList<CityList> listcityDetails = getCityList_Output_Pojo.getData();
		for (CityList eachcityList : listcityDetails) {
			String cityname = eachcityList.getName();
			if (cityname.equals("Dharmapuri")) {
			int	cityid = eachcityList.getId();
			TC1_LoginStep.globaldatas.setCityid(cityid);
			Assert.assertEquals("verify cityname as Dharmapuri", expcityname, cityname);	
			break;

			}

		}

	}

}
