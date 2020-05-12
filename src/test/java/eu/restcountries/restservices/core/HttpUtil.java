package eu.restcountries.restservices.core;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	
	public static String getStringResponseMessage(HttpResponse response) throws IOException {
		String result = null;
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity);
			System.out.println(result);
		}
		return result;
	}
	
	public static HttpResponse sendAndReceiveGETMessageInJSONFormat(String url) throws IOException, ClientProtocolException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet requestMsg = new HttpGet(url);
		HttpResponse response = httpClient.execute(requestMsg);
		response.addHeader("Content-Type", "application/json");
		return response;
	}
	
	public static HttpResponse sendAndReceiveGETMessage(String url) throws IOException, ClientProtocolException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet requestMsg = new HttpGet(url);
		HttpResponse response = httpClient.execute(requestMsg);
		return response;
	}

}
