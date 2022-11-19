package com.Stepdefinition;


import org.junit.Assert;

import io.cucumber.java.en.Then;

public class CommonStep {
	
	@Then("User verify the status code is {int}")
	public void user_verify_the_status_code_is(int expstatuscode) {
		int actStatusCode = TC1_LoginStep.globaldatas.getStatusCode();
		Assert.assertEquals("Verify status code", expstatuscode, actStatusCode);
	}

}
