package eu.restcountries.restservices.core;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import eu.restcountries.restservices.bean.Country;

public class HttpValidator {
	
	public static void performCommonResponseValidations(HttpResponse response, int statuscode) {
		Assert.assertEquals(response.getStatusLine().getStatusCode(), statuscode);
	}
	
	public static void contentValidations(String result, String capital) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		Country country = JSONParserUtilUsingJackson.getCountryObjectFromJSON(result);
		Assert.assertEquals(country.getCapital(), capital);

	}	

}
