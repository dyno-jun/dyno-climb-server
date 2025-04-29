package com.dyno.climb.gym.adapter.in.web.mapper;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderLevelDto;
import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.vo.BoulderLevel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GymMapper {
  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "address.main", target = "address")
  @Mapping(source = "location.latitude", target = "latitude")
  @Mapping(source = "location.longitude", target = "longitude")
  @Mapping(source = "tel.phone", target = "tel")
  @Mapping(source = "image.url", target = "image")
  @Mapping(source = "link.url", target = "link")
  @Mapping(source = "updatedAt", target = "updatedAt")
  @Mapping(source = "levels", target = "levels")
  GymDto toDto(Gym gym);

  BoulderLevelDto toDto(BoulderLevel level);

  List<BoulderLevelDto> mapLevels(List<BoulderLevel> levels);
}
