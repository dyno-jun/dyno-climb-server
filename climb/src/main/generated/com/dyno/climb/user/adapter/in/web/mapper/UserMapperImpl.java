package com.dyno.climb.user.adapter.in.web.mapper;

import com.dyno.climb.user.adapter.in.web.dto.response.UserDto;
import com.dyno.climb.user.adapter.in.web.dto.response.UserLevelDto;
import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.UserLevel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T21:54:14+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserLevelDto toDto(UserLevel level) {
        if ( level == null ) {
            return null;
        }

        int currentExp = 0;
        int levelUpExp = 0;
        String level1 = null;

        currentExp = level.currentExp();
        levelUpExp = level.levelUpExp();
        level1 = level.level();

        UserLevelDto userLevelDto = new UserLevelDto( currentExp, levelUpExp, level1 );

        return userLevelDto;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserLevel( toDto( user.getLevel() ) );
        userDto.setNickName( user.getNickName() );
        userDto.setStartDate( user.getStartDate() );
        if ( user.getReach() != null ) {
            userDto.setReach( user.getReach() );
        }
        userDto.setAccountId( user.getAccountId() );

        return userDto;
    }
}
