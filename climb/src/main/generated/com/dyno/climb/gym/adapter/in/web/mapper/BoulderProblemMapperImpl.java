package com.dyno.climb.gym.adapter.in.web.mapper;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderLevelDto;
import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemDto;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.vo.BoulderLevel;
import com.dyno.climb.shared.vo.Image;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T21:54:14+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class BoulderProblemMapperImpl implements BoulderProblemMapper {

    @Override
    public BoulderLevelDto toDto(BoulderLevel level) {
        if ( level == null ) {
            return null;
        }

        BoulderLevelDto boulderLevelDto = new BoulderLevelDto();

        boulderLevelDto.setName( level.name() );
        boulderLevelDto.setColor( level.color() );

        return boulderLevelDto;
    }

    @Override
    public BoulderProblemDto toDto(BoulderProblem boulderProblem) {
        if ( boulderProblem == null ) {
            return null;
        }

        BoulderProblemDto boulderProblemDto = new BoulderProblemDto();

        boulderProblemDto.setImage( boulderProblemImageUrl( boulderProblem ) );
        boulderProblemDto.setId( boulderProblem.getId() );
        boulderProblemDto.setSector( boulderProblem.getSector() );
        boulderProblemDto.setLevel( toDto( boulderProblem.getLevel() ) );
        boulderProblemDto.setGymId( boulderProblem.getGymId() );

        return boulderProblemDto;
    }

    private String boulderProblemImageUrl(BoulderProblem boulderProblem) {
        if ( boulderProblem == null ) {
            return null;
        }
        Image image = boulderProblem.getImage();
        if ( image == null ) {
            return null;
        }
        String url = image.url();
        if ( url == null ) {
            return null;
        }
        return url;
    }
}
