package eu.restcountries.restservices.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import eu.restcountries.restservices.bean.Country;
import eu.restcountries.restservices.core.CountryBasePage;
import eu.restcountries.restservices.core.CountryBaseTest;
import eu.restcountries.restservices.core.HttpUtil;
import eu.restcountries.restservices.core.HttpValidator;
import eu.restcountries.restservices.core.JSONParserUtilUsingJackson;

public class CountryAPIGETTestUsingConsole extends CountryBasePage {
	public CountryAPIGETTestUsingConsole() throws IOException {
		super();

	}

	CountryBaseTest base;
	JSONParserUtilUsingJackson parser;
	String endUrl;
	String serviceUrl;
	String url;
	HttpUtil util;
	HttpValidator validator;
	HttpResponse response;

	@BeforeMethod
	public void setUp() {
		base = new CountryBaseTest();
		String code = base.userInput();
		if(code.equals("quit")) {
			System.exit(0);
		}
		while (!(code.isEmpty())) {
			if (code.length() <= 3) {
				endUrl = prop.getProperty("endurl");
				serviceUrl = prop.getProperty("ccserviceurl");
				url = endUrl + serviceUrl + code;
				break;
			} else if (code.length() > 3) {
				if (code.equals("quit")) {
					break;
				} else {
					endUrl = prop.getProperty("endurl");
					serviceUrl = prop.getProperty("cnserviceurl");
					url = endUrl + serviceUrl + code;
					break;
				}

			}

		}

	}

	@Test(priority = 0)
	public void GetTestValidParameters() throws ClientProtocolException, IOException {
		base = new CountryBaseTest();
		parser = new JSONParserUtilUsingJackson();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		HttpResponse response = base.getWithRequestHeaders(url, headerMap);
		String msg = base.responseMsg(response);
		System.out.println(msg);
		Country country = parser.getCountryObjectFromJSON(msg);
		Map<String, String> headermap = base.getResponseHeaders(response);
		Assert.assertEquals(headermap.get("Content-Type"), "application/json;charset=utf-8");
		//Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		System.out.println("Capital city for the code entered:" + country.getCapital());

	}

	@Test(priority = 1)
	public void GetTestWithInvalidParameters() throws ClientProtocolException, IOException {
		base = new CountryBaseTest();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		HttpResponse response = base.getWithRequestHeaders(url, headerMap);
		String msg = base.responseMsg(response);
		System.out.println(msg);
		Map<String, String> headermap = base.getResponseHeaders(response);
		Assert.assertEquals(headermap.get("Content-Type"), "application/json;charset=utf-8");
		//Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	}

	@Test(priority = 2)
	public void GetTestWithSpecialCharacters() throws ClientProtocolException, IOException {
		base = new CountryBaseTest();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		HttpResponse response = base.getWithRequestHeaders(url, headerMap);
		String msg = base.responseMsg(response);
		System.out.println(msg);
		Map<String, String> headermap = base.getResponseHeaders(response);

		Assert.assertEquals(headermap.get("Content-Type"), "application/json;charset=utf-8");
		//Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);

	}
	
	@Test(priority = 3)
	public void GetTestWithInvalidDataType() throws ClientProtocolException, IOException {
		base = new CountryBaseTest();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		HttpResponse response = base.getWithRequestHeaders(url, headerMap);
		String msg = base.responseMsg(response);
		System.out.println(msg);
		Map<String, String> headermap = base.getResponseHeaders(response);
		Assert.assertEquals(headermap.get("Content-Type"), "application/json;charset=utf-8");
		//Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);

	}

	@Test(priority = 4)
	public void GetTestWithBlank() throws ClientProtocolException, IOException {
		base = new CountryBaseTest();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		HttpResponse response = base.getWithRequestHeaders(url, headerMap);
		String msg = base.responseMsg(response);
		System.out.println(msg);
		Map<String, String> headermap = base.getResponseHeaders(response);
		Assert.assertEquals(headermap.get("Content-Type"), "application/json;charset=utf-8");
		//Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);

	}

}