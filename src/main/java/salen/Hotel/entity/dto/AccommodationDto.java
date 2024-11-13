package salen.Hotel.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccommodationDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private String category;
    @JsonProperty("location")
    private LocationDto location;
    @JsonProperty("image")
    private String image;
    @JsonProperty("reputation")
    private Integer reputation;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("availability")
    private Integer availability;
    @JsonProperty("rating")
    private Integer rating;
    @JsonProperty("reputationBadge")
    private String reputationBadge;
}
