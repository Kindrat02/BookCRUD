package com.eleks.mentorship;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import java.io.IOException;

@SpringBootApplication
public class MentorshipApplication {

	private static EmbeddedPostgres postgres;

	public static void main(String[] args) throws IOException {
		postgres = EmbeddedPostgres
				.builder()
				.setPort(5433)
				.start();

		SpringApplication.run(MentorshipApplication.class, args);
	}

	@PreDestroy
	public void closeDatabaseConnection() throws IOException {
		postgres.close();
	}
}
