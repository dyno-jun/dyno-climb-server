package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageDto is a Querydsl query type for ImageDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QImageDto extends BeanPath<ImageDto> {

    private static final long serialVersionUID = -1539716393L;

    public static final QImageDto imageDto = new QImageDto("imageDto");

    public final StringPath url = createString("url");

    public QImageDto(String variable) {
        super(ImageDto.class, forVariable(variable));
    }

    public QImageDto(Path<? extends ImageDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageDto(PathMetadata metadata) {
        super(ImageDto.class, metadata);
    }

}

