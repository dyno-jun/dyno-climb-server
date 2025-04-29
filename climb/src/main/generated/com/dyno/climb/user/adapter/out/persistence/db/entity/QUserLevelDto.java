package com.dyno.climb.user.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLevelDto is a Querydsl query type for UserLevelDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserLevelDto extends BeanPath<UserLevelDto> {

    private static final long serialVersionUID = -49775410L;

    public static final QUserLevelDto userLevelDto = new QUserLevelDto("userLevelDto");

    public final NumberPath<Integer> currentExp = createNumber("currentExp", Integer.class);

    public final StringPath level = createString("level");

    public final NumberPath<Integer> levelUpExp = createNumber("levelUpExp", Integer.class);

    public QUserLevelDto(String variable) {
        super(UserLevelDto.class, forVariable(variable));
    }

    public QUserLevelDto(Path<? extends UserLevelDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLevelDto(PathMetadata metadata) {
        super(UserLevelDto.class, metadata);
    }

}

