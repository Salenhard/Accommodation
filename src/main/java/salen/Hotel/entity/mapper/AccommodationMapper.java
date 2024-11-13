package salen.Hotel.entity.mapper;

import org.mapstruct.Mapper;
import salen.Hotel.entity.Accommodation;
import salen.Hotel.entity.dto.AccommodationDto;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface AccommodationMapper {
    AccommodationDto toDto(Accommodation entity);

    Accommodation toEntity(AccommodationDto dto);
}
