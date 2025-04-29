package com.dyno.climb.user.adapter.in.web.mapper;

import com.dyno.climb.user.adapter.in.web.dto.response.UserDto;
import com.dyno.climb.user.adapter.in.web.dto.response.UserLevelDto;
import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.UserLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserLevelDto toDto(UserLevel level);

  @Mapping(source = "level", target = "userLevel")
  UserDto toDto(User user);
}
