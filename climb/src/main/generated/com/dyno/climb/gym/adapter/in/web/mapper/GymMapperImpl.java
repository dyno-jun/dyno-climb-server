package com.dyno.climb.gym.adapter.in.web.mapper;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderLevelDto;
import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.vo.Address;
import com.dyno.climb.gym.domain.vo.BoulderLevel;
import com.dyno.climb.gym.domain.vo.Link;
import com.dyno.climb.gym.domain.vo.Location;
import com.dyno.climb.gym.domain.vo.Tel;
import com.dyno.climb.shared.vo.Image;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T21:54:14+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class GymMapperImpl implements GymMapper {

    @Override
    public GymDto toDto(Gym gym) {
        if ( gym == null ) {
            return null;
        }

        GymDto gymDto = new GymDto();

        gymDto.setId( gym.getId() );
        gymDto.setName( gym.getName() );
        gymDto.setAddress( gymAddressMain( gym ) );
        gymDto.setLatitude( gymLocationLatitude( gym ) );
        gymDto.setLongitude( gymLocationLongitude( gym ) );
        gymDto.setTel( gymTelPhone( gym ) );
        gymDto.setImage( gymImageUrl( gym ) );
        gymDto.setLink( gymLinkUrl( gym ) );
        gymDto.setUpdatedAt( gym.getUpdatedAt() );
        gymDto.setLevels( mapLevels( gym.getLevels() ) );

        return gymDto;
    }

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
    public List<BoulderLevelDto> mapLevels(List<BoulderLevel> levels) {
        if ( levels == null ) {
            return null;
        }

        List<BoulderLevelDto> list = new ArrayList<BoulderLevelDto>( levels.size() );
        for ( BoulderLevel boulderLevel : levels ) {
            list.add( toDto( boulderLevel ) );
        }

        return list;
    }

    private String gymAddressMain(Gym gym) {
        if ( gym == null ) {
            return null;
        }
        Address address = gym.getAddress();
        if ( address == null ) {
            return null;
        }
        String main = address.main();
        if ( main == null ) {
            return null;
        }
        return main;
    }

    private Double gymLocationLatitude(Gym gym) {
        if ( gym == null ) {
            return null;
        }
        Location location = gym.getLocation();
        if ( location == null ) {
            return null;
        }
        double latitude = location.latitude();
        return latitude;
    }

    private Double gymLocationLongitude(Gym gym) {
        if ( gym == null ) {
            return null;
        }
        Location location = gym.getLocation();
        if ( location == null ) {
            return null;
        }
        double longitude = location.longitude();
        return longitude;
    }

    private String gymTelPhone(Gym gym) {
        if ( gym == null ) {
            return null;
        }
        Tel tel = gym.getTel();
        if ( tel == null ) {
            return null;
        }
        String phone = tel.phone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String gymImageUrl(Gym gym) {
        if ( gym == null ) {
            return null;
        }
        Image image = gym.getImage();
        if ( image == null ) {
            return null;
        }
        String url = image.url();
        if ( url == null ) {
            return null;
        }
        return url;
    }

    private String gymLinkUrl(Gym gym) {
        if ( gym == null ) {
            return null;
        }
        Link link = gym.getLink();
        if ( link == null ) {
            return null;
        }
        String url = link.url();
        if ( url == null ) {
            return null;
        }
        return url;
    }
}
