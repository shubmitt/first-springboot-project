package com.in28minutes.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration("basic")

public class BasicConfiguration{
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	private int number;
	private boolean value;
}