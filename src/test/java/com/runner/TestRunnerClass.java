package com.runner;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.reports.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@StateId", monochrome = true,publish = true,snippets = SnippetType.CAMELCASE,dryRun = false, plugin = { "pretty",
		"json:target\\output.json" }, features = "src\\test\\resources", glue = "com.Stepdefinition")

public class TestRunnerClass extends BaseClass {
	
	@AfterClass
	public static void afterclass() throws FileNotFoundException, IOException {
		Reporting.generatejvm("C:\\Users\\surya\\eclipse-workspace\\OMRBranchAPIAutomation\\target\\Output.JSON");

		Reporting.generatejvm(getprojectloc() + getPropertyFileValue("jsonpath"));

	}
}
//@Login or @StateId or @CityId or @Address or @ProductSearch or @ChangeProfilepic
//snippets = SnippetType.CAMELCASE, publish = true, dryRun = false, stepNotifications = true,