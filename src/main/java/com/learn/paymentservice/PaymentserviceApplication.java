package com.learn.paymentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@SpringBootApplication
public class PaymentserviceApplication {

 	public final Logger logger = LoggerFactory.getLogger(PaymentserviceApplication.class);
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentserviceApplication.class, args);
	}

	@GetMapping("/say")
	public String sayHello() {
		logger.info("just a message");
		logger.debug("just a debug message");
		return "Hello";
	}
	
	@RequestMapping(value="/do",method=RequestMethod.GET)
	public String getResult() {
		ResponseEntity<String> output=restTemplate.getForEntity("https://randomuser.me/api/",String.class);
		return output.getBody();
	}
	
	@RequestMapping(value="/object",method=RequestMethod.GET)
	public String getResultObject() {
		Object[] customerJson = restTemplate.getForObject("https://randomuser.me/api/",Object[].class);
		return customerJson.toString();
	}
	
}
