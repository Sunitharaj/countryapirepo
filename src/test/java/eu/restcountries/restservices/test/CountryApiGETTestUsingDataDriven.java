package eu.restcountries.restservices.test;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.restcountries.restservices.constant.CountryConstant;
import eu.restcountries.restservices.core.CountryBaseTest;
import eu.restcountries.restservices.core.HttpUtil;
import eu.restcountries.restservices.core.HttpValidator;
import eu.restcountries.restservices.core.XLSUtil;

public class CountryApiGETTestUsingDataDriven extends CountryBaseTest implements CountryConstant {
	HttpResponse response = null;

	@DataProvider(name = "getResponseData")
	Object[][] getResponseData() {

		String data[][] = XLSUtil.readfromSpreadSheet("country_response_data.xls", "GetData", "countryGetResponse");
		return data;
	}

	@Test(dataProvider = "getResponseData")
	public void testGetWithDataDriven(String country, String code, String capital)
			throws ClientProtocolException, IOException, JAXBException {
		response = HttpUtil.sendAndReceiveGETMessageInJSONFormat(base_url + country);
		String result = HttpUtil.getStringResponseMessage(response);
		HttpValidator.performCommonResponseValidations(response, Integer.parseInt(code));
		if(Integer.parseInt(code)==200)
		HttpValidator.contentValidations(result, capital);
	
	} 


}
