package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class A {
	public static void main(String[] args) throws IOException {

		String path = System.getProperty("user.dir");
		FileInputStream stream = new FileInputStream(
				path + "\\Config\\config.properties");

		Properties properties = new Properties();
		properties.load(stream);

		Object object = properties.get("Username");
		String value = (String) object;
		System.out.println(value);

	}

}
