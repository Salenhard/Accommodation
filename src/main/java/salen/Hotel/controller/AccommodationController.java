package salen.Hotel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.ReputationBadge;
import salen.Hotel.entity.dto.AccommodationDto;
import salen.Hotel.entity.mapper.AccommodationMapper;
import salen.Hotel.exception.AccommodationAlreadyExistsException;
import salen.Hotel.exception.AccommodationNotFoundException;
import salen.Hotel.exception.ReputationBadgeNotFoundException;
import salen.Hotel.service.AccommodationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accommodations")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationService service;
    private final AccommodationMapper mapper;

    @GetMapping
    public List<AccommodationDto> getAll() {
        return service.getAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public AccommodationDto get(@PathVariable Long id) {
        return mapper.toDto(service.get(id).orElseThrow(() -> new AccommodationNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AccommodationDto accommodationDto) {
        if (accommodationDto.getId() != null && service.get(accommodationDto.getId()).isPresent())
            throw new AccommodationAlreadyExistsException(accommodationDto.getId());
        return ResponseEntity.ok().body(mapper.toDto(service.save(mapper.toEntity(accommodationDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid AccommodationDto newAccommodationDto) {
        Accommodation newAccommodation = mapper.toEntity(newAccommodationDto);
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
        return ResponseEntity.ok().body(mapper.toDto(service.save(updatedAccommodation)));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> book(@PathVariable Long id) {
        service.book(id);
        return ResponseEntity.ok().body("You booked accommodation");
    }

    @GetMapping("/search")
    public List<AccommodationDto> filter(@RequestParam Optional<Integer> rating, @RequestParam Optional<String> city, @RequestParam Optional<String> badge) {
        ReputationBadge reputationBadge;
        try {
            reputationBadge = ReputationBadge.valueOf(badge.orElse("GREEN").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ReputationBadgeNotFoundException(badge.get());
        }
        return service.getAll(rating.orElse(5), city.orElse(""), reputationBadge)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/rating/{rating}")
    public List<AccommodationDto> getByRating(@PathVariable Integer rating) {
        return service.getAllByRating(rating).stream().map(mapper::toDto).toList();
    }

    @GetMapping("/city/{city}")
    public List<AccommodationDto> getAllByCity(@PathVariable String city) {
        return service.getAllByCity(city).stream().map(mapper::toDto).toList();
    }

    @GetMapping("/badge/{badge}")
    public List<AccommodationDto> getAllByReputationBadge(@PathVariable String badge) {
        ReputationBadge reputationBadge;
        try {
            reputationBadge = ReputationBadge.valueOf(badge.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ReputationBadgeNotFoundException(badge);
        }
        return service.getAllByReputationBadge(reputationBadge).stream().map(mapper::toDto).toList();
    }
}
