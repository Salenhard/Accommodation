package salen.Hotel.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.ReputationBadge;
import salen.Hotel.exception.AccommodationNotFoundException;
import salen.Hotel.repository.AccommodationRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccommodationService {
    private final AccommodationRepository repository;

    @Cacheable(value = "accommodations", key = "#id")
    public Optional<Accommodation> get(Long id) {
        return repository.findById(id);
    }

    public List<Accommodation> getAll() {
        return repository.findAll();
    }

    @Transactional
    @CachePut(value = "accommodations", key = "#accommodation.id")
    public Accommodation save(Accommodation accommodation) {
        return repository.save(accommodation);
    }

    @Transactional
    @CacheEvict(value = "accommodations", key = "#accommodation.id")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @CachePut(value = "accommodations", key = "#accommodation.id")
    public boolean book(Long id) {
        Accommodation accommodation = repository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));
        if (accommodation.getAvailability() == 0)
            return false;
        else {
            accommodation.setAvailability(accommodation.getAvailability() - 1);
            save(accommodation);
            return true;
        }
    }

    public List<Accommodation> getAllByRating(Integer rating) {
        return repository.findAllByRating(rating);
    }

    public List<Accommodation> getAllByReputationBadge(ReputationBadge reputationBadge) {
        return repository.findAllByReputationBadge(reputationBadge);
    }

    public List<Accommodation> getAllByCity(String city) {
        return repository.findAllByLocation_City(city);
    }

    public List<Accommodation> getAllByFilters(Integer rating, String city, ReputationBadge reputationBadge) {
        return repository.findAllByRatingAndLocation_CityLikeAndReputationBadge(rating, city, reputationBadge);
    }
}
