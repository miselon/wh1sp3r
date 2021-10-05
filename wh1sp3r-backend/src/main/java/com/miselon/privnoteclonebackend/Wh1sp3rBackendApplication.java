package com.miselon.privnoteclonebackend;

import com.miselon.privnoteclonebackend.services.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Wh1sp3rBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Wh1sp3rBackendApplication.class, args);
	}

	/**
	 *  Runs async task to delete expired notes,
	 *  runs after the application context is loaded
	 */
	@Bean
	CommandLineRunner commandLineRunner(NoteService noteService){
		return args -> {
			noteService.runExpiredNoteDeleter();
		};
	}
}