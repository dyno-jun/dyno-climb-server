package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTelDto is a Querydsl query type for TelDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTelDto extends BeanPath<TelDto> {

    private static final long serialVersionUID = 525219031L;

    public static final QTelDto telDto = new QTelDto("telDto");

    public final StringPath phone = createString("phone");

    public final StringPath store = createString("store");

    public QTelDto(String variable) {
        super(TelDto.class, forVariable(variable));
    }

    public QTelDto(Path<? extends TelDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTelDto(PathMetadata metadata) {
        super(TelDto.class, metadata);
    }

}

