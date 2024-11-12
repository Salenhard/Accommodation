package salen.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.ReputationBadge;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAll();

    List<Accommodation> findAllByReputationBadge(ReputationBadge reputationBadge);

    List<Accommodation> findAllByRating(Integer rating);

    List<Accommodation> findAllByLocation_City(String city);

    List<Accommodation> findAllByRatingAndLocation_CityLikeAndReputationBadge(Integer rating, String city, ReputationBadge badge);
}
