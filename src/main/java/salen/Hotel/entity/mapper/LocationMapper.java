package salen.Hotel.entity.mapper;

import org.mapstruct.Mapper;
import salen.Hotel.entity.Location;
import salen.Hotel.entity.dto.LocationDto;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toEntity(Location entity);

    Location toDto(LocationDto dto);
}
