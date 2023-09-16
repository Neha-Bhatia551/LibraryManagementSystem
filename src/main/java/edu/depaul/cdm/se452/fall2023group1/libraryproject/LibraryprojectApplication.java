package edu.depaul.cdm.se452.fall2023group1.libraryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryprojectApplication {

	public static void main(String[] args) {
		System.out.println("Our Spring app is running...");
		SpringApplication.run(LibraryprojectApplication.class, args);
		System.out.println("Done");
	}

}
