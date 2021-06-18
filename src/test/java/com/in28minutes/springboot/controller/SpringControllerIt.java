package com.in28minutes.springboot.controller;



import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
//import java.util.Base64;
import org.springframework.security.crypto.codec.Base64;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.model.Question;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringControllerIt {
	
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = createHttpHeaders("user1","password1");
	
	private HttpHeaders createHttpHeaders(String userId,String password) {
		HttpHeaders headers = new HttpHeaders();
		String auth = userId + ":" + password;
		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
		String headerValue = "Basic " + new String(encodedAuth);
		headers.add("Authorization",headerValue);
 		return headers;
		
	}
	
	@Before
	public void before() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}
	
//	@Test
//	public void testJsonAssert() throws JSONException {
//		String actual = "{id: 2,name: Ranga}";
//		JSONAssert.assertEquals("{id:2}",actual,false);
//	}

	@Test
	public void test() throws JSONException {
		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
		String url = "http://localhost:"+port+"/surveys/Survey1/questions/Question1";
		TestRestTemplate restTemplate = new TestRestTemplate();
		
				HttpEntity entity = new HttpEntity<String>(null,headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
        System.out.println(response.getBody());
	    JSONAssert.assertEquals(expected,response.getBody(),false);
	}
	
	@Test
	public void addQuestion() throws JSONException {
		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
		String url = "http://localhost:"+port+"/surveys/Survey1/questions";
		Question question  = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		
				HttpEntity entity = new HttpEntity<Question>(question,headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,entity,String.class);
	    String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        System.out.println(response.getBody());
	    assertTrue(actual.contains("/surveys/Survey1/questions/"));
	}

}
