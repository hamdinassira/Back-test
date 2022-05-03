package com.sid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TestTechniqueApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(TestTechniqueApplication.class, args);
	}
	
	





	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Application Started to Run");
		//pdfFileService.export(response);;
		System.out.println("Pdf File Got Created");
		
	}

}
