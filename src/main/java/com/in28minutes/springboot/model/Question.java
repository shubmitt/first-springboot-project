package com.in28minutes.springboot.model;

import java.util.List;

public class Question {
 
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String id;
	private String description;
	private String correctAnswer;
	private List<String> options;
	public String getId() {
		return id;
	}
	
	public Question(String id, String description, String correctAnswer, List<String> options) {
		super();
		this.id = id;
		this.description = description;
		this.correctAnswer = correctAnswer;
		this.options = options;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
}
