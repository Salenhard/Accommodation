package salen.Hotel.config;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.Category;
import salen.Hotel.entity.Location;
import salen.Hotel.entity.ReputationBadge;
import salen.Hotel.entity.mapper.AccommodationMapper;
import salen.Hotel.repository.AccommodationRepository;

@Configuration
@AllArgsConstructor
public class LoadDB {
    private final Logger log = LoggerFactory.getLogger(LoadDB.class);
    private final AccommodationRepository repository;
    private final AccommodationMapper mapper;

    @Bean
    CommandLineRunner load() {
        return args -> {
            Accommodation accommodation1 = new Accommodation(1L, "test accommodation 1", Category.hotel, new Location("address for the fans", "Chita", "Russia", 10000), "https://image.com", 1000, 150, 1, 4, ReputationBadge.GREEN);
            Accommodation accommodation2 = new Accommodation(2L, "test accommodation 2", Category.lodge, new Location("address for the fans1", "Moscow", "Russia", 15000), "https://image1.com", 600, 150, 0, 3, ReputationBadge.YELLOW);
            Accommodation accommodation3 = new Accommodation(3L, "test accommodation 3", Category.hostel, new Location("address for the fans2", "Washington", "U.S.", 99999), "https://image2.com", 100, 150, 15, 1, ReputationBadge.RED);
            repository.save(accommodation1);
            repository.save(accommodation2);
            repository.save(accommodation3);
            repository.findAll().forEach((item) -> log.info("preload... {}", item));
        };
    }
}
