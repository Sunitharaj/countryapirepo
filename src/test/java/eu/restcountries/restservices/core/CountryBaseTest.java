package eu.restcountries.restservices.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class CountryBaseTest {
	
	public String userInput()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter CountryCode or Country Name");
		String code = scan.next();
		return code;	
	}
	
	public HttpResponse get(String url) throws ClientProtocolException, IOException
	{
		HttpClient httpClient=HttpClientBuilder.create().build();
		HttpGet httpGet=new HttpGet(url);
		HttpResponse response=httpClient.execute(httpGet);
		
		return response;
		
	}
	
	public String responseMsg(HttpResponse response) throws ParseException, IOException
	{
		HttpEntity entity=response.getEntity();
		String resultMsg=EntityUtils.toString(entity);
		if(resultMsg.trim().charAt(0) == '[')
		{
			JSONArray jsonArray = new JSONArray(resultMsg);
			JSONObject obj = jsonArray.optJSONObject(0);
			resultMsg= obj.toString();
		} 
       return resultMsg;	
	}
	// Passing headers in the GET request
	public HttpResponse getWithRequestHeaders(String url,HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		HttpClient httpClient=HttpClientBuilder.create().build();
		HttpGet httpGet=new HttpGet(url);
		for(Map.Entry<String, String> map:headerMap.entrySet())
		{
			httpGet.addHeader(map.getKey(),map.getValue());
		}
		HttpResponse response=httpClient.execute(httpGet);	
		return response;
		
	}
	//Response Headers
	public HashMap<String, String> getResponseHeaders(HttpResponse response)
	{
		Header[] resheader=response.getAllHeaders();
		HashMap<String, String> headers=new HashMap<String, String>();
		for(Header header:resheader)
		{
		headers.put(header.getName(), header.getValue());
		}
		return headers;
		
	}

}
