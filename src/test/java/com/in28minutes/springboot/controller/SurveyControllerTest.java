package com.in28minutes.springboot.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;


@RunWith(SpringRunner.class)
@WebMvcTest(value=SurveyController.class,secure=false)
public class SurveyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;


	//Mock @Autowired
	@MockBean
	private SurveyService surveyService;
	
	@Test
	public void SurveyControllerTest() throws Exception {
		Question mockQuestion  = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(),Mockito.anyString())).thenReturn(mockQuestion);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/survey/Survey1/questions/Question1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
		
	}
	
	@Test
	public void retrieveSurveyQuestions() throws Exception {
		List<Question> mockList = Arrays.asList(
                new Question("Question1", "First Alphabet", "A", Arrays.asList(
                        "A", "B", "C", "D")),
                new Question("Question2", "Last Alphabet", "Z", Arrays.asList(
                        "A", "X", "Y", "Z")));
		Mockito.when(surveyService.retrieveQuestions(Mockito.anyString())).thenReturn(mockList);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/survey/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "["
                + "{id:Question1,description:First Alphabet,correctAnswer:A,options:[A,B,C,D]},"
                + "{id:Question2,description:Last Alphabet,correctAnswer:Z,options:[A,X,Y,Z]}"
                + "]";
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
	}
	
	@Test
	public void addSurveyQuestion() throws Exception {
		Question mockQuestion = new Question("1", "Smallest Number", "1",
				Arrays.asList("1", "2", "3", "4"));

		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";
		Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(mockQuestion);
		MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/survey/Survey1/questions")
				.content(questionJson).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		
		assertEquals("http://localhost/surveys/Survey1/questions/1", response.getResponse()
				.getHeader(HttpHeaders.LOCATION));
	}

}
