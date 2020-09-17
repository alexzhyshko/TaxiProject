package application.context.reader;

import java.io.IOException;
import java.util.Properties;

import application.context.annotation.Component;
import application.context.scanner.Scanner;

@Component
public class PropertyReader {

	private String propertyName = "configuration.properties";
	private Properties properties;
	
	public PropertyReader() {
		this.properties = new Properties();
		try {
			this.properties.load(Scanner.class.getClassLoader().getResourceAsStream(propertyName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}
	
}