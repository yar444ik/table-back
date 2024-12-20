package com.vorstu.table;

import com.vorstu.table.service.InitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	private static InitializerService initializerService;

	@Autowired
	public void setInitialLoader(InitializerService initializerService) {
		DemoApplication.initializerService = initializerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		initializerService.initial();
	}

}
