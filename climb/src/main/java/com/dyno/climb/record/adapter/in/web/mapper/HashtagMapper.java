package com.dyno.climb.record.adapter.in.web.mapper;

import com.dyno.climb.record.adapter.in.web.dto.response.HashtagDto;
import com.dyno.climb.record.domain.Hashtag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Reference BoulderLevelMapper
public interface HashtagMapper {
  HashtagDto toDto(Hashtag level);
}
