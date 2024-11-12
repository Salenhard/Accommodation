package salen.Hotel.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.ReputationBadge;
import salen.Hotel.exception.AccommodationAlreadyExists;
import salen.Hotel.exception.AccommodationNotFoundException;
import salen.Hotel.exception.ReputationBadgeNotFoundException;
import salen.Hotel.service.AccommodationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accommodations")
@AllArgsConstructor
public class AccommodationController {
    private final AccommodationService service;

    @GetMapping
    public List<Accommodation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Accommodation get(@PathVariable Long id) {
        return service.get(id).orElseThrow(() -> new AccommodationNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Accommodation accommodation) {
        if (service.get(accommodation.getId()).isPresent())
            throw new AccommodationAlreadyExists(accommodation.getId());
        return ResponseEntity.ok().body(service.save(accommodation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Accommodation newAccommodation) {
        Accommodation updatedAccommodation = service.get(id).map(accommodation ->
        {
            accommodation.setName(newAccommodation.getName());
            accommodation.setCategory(newAccommodation.getCategory());
            accommodation.setLocation(newAccommodation.getLocation());
            accommodation.setImage(newAccommodation.getImage());
            accommodation.setReputation(newAccommodation.getReputation());
            accommodation.setPrice(newAccommodation.getPrice());
            accommodation.setAvailability(newAccommodation.getAvailability());
            accommodation.setRating(newAccommodation.getRating());
            accommodation.setReputationBadge();
            return accommodation;
        }).orElseThrow(() -> new AccommodationNotFoundException(id));
        return ResponseEntity.ok().body(service.save(updatedAccommodation));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> book(@PathVariable Long id) {
        if (service.book(id))
            return ResponseEntity.ok().body("You booked accommodation");
        else
            return ResponseEntity.badRequest().body("No place left");
    }

    @GetMapping("/search")
    public List<Accommodation> filter(@RequestParam Optional<Integer> rating, @RequestParam Optional<String> city, @RequestParam Optional<String> badge) {
        ReputationBadge reputationBadge;
        try {
            reputationBadge = ReputationBadge.valueOf(badge.orElse("GREEN").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ReputationBadgeNotFoundException(badge.get());
        }
        return service.getAllByFilters(rating.orElse(5), city.orElse(""), reputationBadge);
    }

    @GetMapping("/rating/{rating}")
    public List<Accommodation> getByRating(@PathVariable Integer rating) {
        return service.getAllByRating(rating);
    }

    @GetMapping("/city/{city}")
    public List<Accommodation> getAllByCity(@PathVariable String city) {
        return service.getAllByCity(city);
    }

    @GetMapping("/badge/{badge}")
    public List<Accommodation> getAllByReputationBadge(@PathVariable String badge) {
        ReputationBadge reputationBadge;
        try {
            reputationBadge = ReputationBadge.valueOf(badge.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ReputationBadgeNotFoundException(badge);
        }
        return service.getAllByReputationBadge(reputationBadge);
    }
}
