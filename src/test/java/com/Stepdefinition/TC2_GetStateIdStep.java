package com.Stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.GetStateList_Output_Pojo;
import com.pojo.address.StateList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class TC2_GetStateIdStep extends BaseClass {
	Response response;
	
	@Given("User add header for stateList endpoint")
	public void userAddHeaderForStateListEndpoint() {
		addHeader("accept", "application/json");
	}

	@Given("User send {string} request for stateList endpoint")
	public void userSendRequestForStateListEndpoint(String type) throws FileNotFoundException, IOException {
		response = requestType(type, Endpoints.STATELIST);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);
	}

	@Then("User verify the stateList response body name present as {string} and get the stateId of Tamil Nadu")
	public void userVerifyTheStateListResponseBodyNamePresentAsAndGetTheStateIdOfTamilNadu(String expstatename) {
		
		GetStateList_Output_Pojo getStateList_Output_Pojo = response.as(GetStateList_Output_Pojo.class);
		ArrayList<StateList> listState = getStateList_Output_Pojo.getData();
		for (StateList eachstateList : listState) {
			String stateName = eachstateList.getName();
			if (stateName.equals("Tamil Nadu")) {
			int	stateIdNum = eachstateList.getId();
			 String state_id = String.valueOf(stateIdNum);
			
			 TC1_LoginStep.globaldatas.setState_id(state_id);
			TC1_LoginStep.globaldatas.setStateIdNum(stateIdNum);
           
			Assert.assertEquals("verify state name is Tamil Nadu", expstatename, stateName);
            break;
			}

		}
	}

}
