package com.in28minutes.springboot.jpa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class UserCommandLineRunner implements CommandLineRunner{
	
	@Autowired
	private UserRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repository.save(new User("A","Admin"));
		repository.save(new User("B","General"));
		repository.save(new User("C","User"));
		
		for(User user: repository.findByRole("Admin")) {
			System.out.println(user.toString());
		}
	}
	
}