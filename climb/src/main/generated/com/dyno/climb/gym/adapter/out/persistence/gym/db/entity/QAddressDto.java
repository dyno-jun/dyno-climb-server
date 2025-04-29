package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressDto is a Querydsl query type for AddressDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddressDto extends BeanPath<AddressDto> {

    private static final long serialVersionUID = 610032862L;

    public static final QAddressDto addressDto = new QAddressDto("addressDto");

    public final StringPath detail = createString("detail");

    public final StringPath main = createString("main");

    public final StringPath postCode = createString("postCode");

    public QAddressDto(String variable) {
        super(AddressDto.class, forVariable(variable));
    }

    public QAddressDto(Path<? extends AddressDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressDto(PathMetadata metadata) {
        super(AddressDto.class, metadata);
    }

}

