package salen.Hotel.util;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.Location;
import salen.Hotel.entity.ReputationBadge;

public class AccommodationSpecification {
    private AccommodationSpecification() {
    }
 
    public static Specification<Accommodation> cityLike(String cityLike) {

        return (root, query, builder) ->{
            Join<Location, Accommodation> accommodationLocation = root.join("location");
            return builder.like(accommodationLocation.get("city"), "%" + cityLike + "%");
        };
    }

    public static Specification<Accommodation> hasRating(Integer rating) {
        return (root, query, builder) -> builder.equal(root.get("rating"), rating);
    }

    public static Specification<Accommodation> hasReputationBadge(ReputationBadge reputationBadge) {
        return (root, query, builder) -> builder.equal(root.get("reputationBadge"), reputationBadge);
    }
}
