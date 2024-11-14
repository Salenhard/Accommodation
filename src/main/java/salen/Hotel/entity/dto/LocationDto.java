package salen.Hotel.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationDto {
    @JsonProperty("address")
    @NotBlank
    private String address;
    @JsonProperty("city")
    @NotBlank
    private String city;
    @JsonProperty("country")
    @NotBlank
    private String country;
    @JsonSetter("zipCode")
    @Min(10000)
    @Max(99999)
    @NotNull
    private Integer zipCode;
}
