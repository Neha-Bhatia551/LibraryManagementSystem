package edu.depaul.cdm.se452.fall2023group1;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class LibraryProjectApplication {

	public static void main(String[] args) {
		log.info("Library app is running...");
		SpringApplication.run(LibraryProjectApplication.class, args);

	}

}
