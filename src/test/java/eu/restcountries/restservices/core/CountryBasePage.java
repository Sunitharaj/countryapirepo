package eu.restcountries.restservices.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CountryBasePage {
	public Properties prop;
	public CountryBasePage() throws IOException
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("data.config");
		prop.load(fis);
		
		
	}

}
