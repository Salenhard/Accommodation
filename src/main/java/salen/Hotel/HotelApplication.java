package salen.Hotel;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@SpringMapperConfig
public class HotelApplication {
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
		//todo add flyway database
		//todo providing sql schema(allowing spring to create tables according to entities is great for prototyping.
		// but not always a way to go since there's usually a lot of changes in schema during development
		// so it's just straight up easier to provide initial_schema.sql and then patch databse with new revisions of .sql files)
		//todo tests (unit, e2e)
	}
}
