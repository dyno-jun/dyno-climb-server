package com.dyno.climb.record.adapter.in.web.mapper;

import com.dyno.climb.record.adapter.in.web.dto.response.HashtagDto;
import com.dyno.climb.record.domain.Hashtag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T21:54:14+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class HashtagMapperImpl implements HashtagMapper {

    @Override
    public HashtagDto toDto(Hashtag level) {
        if ( level == null ) {
            return null;
        }

        HashtagDto hashtagDto = new HashtagDto();

        hashtagDto.setId( level.getId() );
        hashtagDto.setName( level.getName() );

        return hashtagDto;
    }
}
