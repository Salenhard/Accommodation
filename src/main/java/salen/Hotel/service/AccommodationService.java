package salen.Hotel.service;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.ReputationBadge;
import salen.Hotel.exception.AccommodationNoPlaceLeftException;
import salen.Hotel.exception.AccommodationNotFoundException;
import salen.Hotel.repository.AccommodationRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static salen.Hotel.util.AccommodationSpecification.*;

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
    @CacheEvict(value = "accommodations", key = "#id")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @CachePut(value = "accommodations", key = "#id")
    public void book(Long id) {
        Accommodation accommodation = repository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        if (accommodation.getAvailability() <= 0) throw new AccommodationNoPlaceLeftException();

        accommodation.setAvailability(accommodation.getAvailability() - 1);
        save(accommodation);
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

    public List<Accommodation> getAll(Integer rating, String city, ReputationBadge reputationBadge) {
        Specification<Accommodation> filters = Specification.where((StringUtils.isBlank(city) ? null : cityLike(city))
                .and(Objects.isNull(rating) ? null : hasRating(rating))
                .and(Objects.isNull(reputationBadge) ? null : hasReputationBadge(reputationBadge)));
        return repository.findAll(filters);
    }
}
