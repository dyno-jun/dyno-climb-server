package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLinkDto is a Querydsl query type for LinkDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLinkDto extends BeanPath<LinkDto> {

    private static final long serialVersionUID = 709305554L;

    public static final QLinkDto linkDto = new QLinkDto("linkDto");

    public final StringPath url = createString("url");

    public QLinkDto(String variable) {
        super(LinkDto.class, forVariable(variable));
    }

    public QLinkDto(Path<? extends LinkDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLinkDto(PathMetadata metadata) {
        super(LinkDto.class, metadata);
    }

}

