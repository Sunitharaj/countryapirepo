package eu.restcountries.restservices.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.restcountries.restservices.bean.Country;
import eu.restcountries.restservices.bean.Code;


public class JSONParserUtilUsingJackson {

	// Parsing Java object to JSON
	public static String getJSONFromObject(Country country) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("/Users/sunitha/Documents/json/customer.json"), country);
		String strformatted = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(country);
		return strformatted;

	}

	// Parsing JSON to Country object
	public static Country getCountryObjectFromJSON(String jsonString)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Country country = (Country) mapper.readValue(jsonString, Country.class);
		return country;

	}
	
}

