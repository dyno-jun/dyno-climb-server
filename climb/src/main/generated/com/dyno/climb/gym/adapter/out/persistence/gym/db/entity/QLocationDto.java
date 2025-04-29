package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocationDto is a Querydsl query type for LocationDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLocationDto extends BeanPath<LocationDto> {

    private static final long serialVersionUID = -633771849L;

    public static final QLocationDto locationDto = new QLocationDto("locationDto");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public QLocationDto(String variable) {
        super(LocationDto.class, forVariable(variable));
    }

    public QLocationDto(Path<? extends LocationDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocationDto(PathMetadata metadata) {
        super(LocationDto.class, metadata);
    }

}

