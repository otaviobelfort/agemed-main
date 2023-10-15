package com.age.med;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/test")
public class AgemedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgemedApplication.class, args);
	}
	@GetMapping
	public String Teste(){
		return "Ilá";
	}

}
