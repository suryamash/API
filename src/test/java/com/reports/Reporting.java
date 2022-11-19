package com.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends BaseClass {
	
	public static void generatejvm(String jsonfile) throws FileNotFoundException, IOException {
		// File file = new File("C:\\Users\\surya\\eclipse-workspace\\OMRBranchAPIAutomation\\target");
		
		File file = new File(getprojectloc() + getPropertyFileValue("jvmpath"));

		Configuration configuration = new Configuration(file, "OMRBranchAPIAutomation");

		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Browser version", "107");
		configuration.addClassifications("OS", "Windows11");

		List<String> jsonfiles = new ArrayList<String>();
		jsonfiles.add(jsonfile);

		ReportBuilder builder = new ReportBuilder(jsonfiles, configuration);

		builder.generateReports();

	}
}
