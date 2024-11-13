package salen.Hotel.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class LocationDto {
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonSetter("zipCode")
    private Integer zipCode;
}
