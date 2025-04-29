package com.dyno.climb.gym.adapter.in.web.mapper;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderLevelDto;
import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemDto;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.vo.BoulderLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Reference BoulderLevelMapper
public interface BoulderProblemMapper {
  BoulderLevelDto toDto(BoulderLevel level);

  @Mapping(source = "image.url", target = "image")
  BoulderProblemDto toDto(BoulderProblem boulderProblem);
}
