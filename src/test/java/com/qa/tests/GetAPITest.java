package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String apiUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHtppRespone;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		url = serviceUrl+apiUrl;
		
		}
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHtppRespone = restClient.get(url);
		
		// a. Status Code
				int statusCode = closeableHtppRespone.getStatusLine().getStatusCode();
				System.out.println("Status code --->"+ statusCode);
				Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200, "Status code is not 200");
				// b. Json String
				String responseString = EntityUtils.toString(closeableHtppRespone.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API --->"+ responseJson);
				
				//single value assertion
				String perPageValue =TestUtil.getValueByJPath(responseJson,"/per_page");
				System.out.println("Value of per page is-->"+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6 );
				
				//get the value from Json array
				String lastName =TestUtil.getValueByJPath(responseJson,"/data[0]/last_name");
				String id =TestUtil.getValueByJPath(responseJson,"/data[0]/id");
				String avatar =TestUtil.getValueByJPath(responseJson,"/data[0]/avatar");
				String firstName =TestUtil.getValueByJPath(responseJson,"/data[0]/first_name");

				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
				
				
				// c. All Headers
				Header[] headersArray = closeableHtppRespone.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for (Header header : headersArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array --->"+ allHeaders);
			
	}

	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String,String> headerMap =new HashMap<String, String>();
		headerMap.put("Content_Type","application/json");
		//headerMap.put("username","test@m.com");
		//headerMap.put("password","testom");
		//headerMap.put("Auth Token","12345");

		closeableHtppRespone = restClient.get(url);
		
		// a. Status Code
				int statusCode = closeableHtppRespone.getStatusLine().getStatusCode();
				System.out.println("Status code --->"+ statusCode);
				Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200, "Status code is not 200");
				// b. Json String
				String responseString = EntityUtils.toString(closeableHtppRespone.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API --->"+ responseJson);
				
				//single value assertion
				String perPageValue =TestUtil.getValueByJPath(responseJson,"/per_page");
				System.out.println("Value of per page is-->"+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6 );
				
				//get the value from Json array
				String lastName =TestUtil.getValueByJPath(responseJson,"/data[0]/last_name");
				String id =TestUtil.getValueByJPath(responseJson,"/data[0]/id");
				String avatar =TestUtil.getValueByJPath(responseJson,"/data[0]/avatar");
				String firstName =TestUtil.getValueByJPath(responseJson,"/data[0]/first_name");

				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
				
				
				// c. All Headers
				Header[] headersArray = closeableHtppRespone.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for (Header header : headersArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array --->"+ allHeaders);
			
	}

}
