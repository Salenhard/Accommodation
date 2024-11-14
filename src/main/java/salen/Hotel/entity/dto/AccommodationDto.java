package salen.Hotel.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Data
public class AccommodationDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    @Size(min = 10)
    @NotBlank
    @Pattern(regexp = "^((?!Free|Offer|Book|Website).)*$")
    private String name;
    @JsonProperty("category")
    @NotNull
    private String category;
    @JsonProperty("location")
    @NotNull
    @Valid
    private LocationDto location;
    @JsonProperty("image")
    @URL
    @NotNull
    private String image;
    @JsonProperty("reputation")
    @Range(min = 0, max = 1000)
    @NotNull
    private Integer reputation;
    @JsonProperty("price")
    @NotNull
    private Integer price;
    @JsonProperty("availability")
    @NotNull
    private Integer availability;
    @JsonProperty("rating")
    @Min(0)
    @Max(5)
    @NotNull
    private Integer rating;
    @JsonProperty("reputationBadge")
    private String reputationBadge;
}
