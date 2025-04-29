package com.dyno.climb.gym.adapter.out.persistence.boulderproblem.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoulderProblemEntity is a Querydsl query type for BoulderProblemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoulderProblemEntity extends EntityPathBase<BoulderProblemEntity> {

    private static final long serialVersionUID = 516403495L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoulderProblemEntity boulderProblemEntity = new QBoulderProblemEntity("boulderProblemEntity");

    public final com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QGymEntity gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QImageDto image;

    public final com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QBoulderLevelEntity level;

    public final StringPath sector = createString("sector");

    public final StringPath uid = createString("uid");

    public QBoulderProblemEntity(String variable) {
        this(BoulderProblemEntity.class, forVariable(variable), INITS);
    }

    public QBoulderProblemEntity(Path<? extends BoulderProblemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoulderProblemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoulderProblemEntity(PathMetadata metadata, PathInits inits) {
        this(BoulderProblemEntity.class, metadata, inits);
    }

    public QBoulderProblemEntity(Class<? extends BoulderProblemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QGymEntity(forProperty("gym"), inits.get("gym")) : null;
        this.image = inits.isInitialized("image") ? new com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QImageDto(forProperty("image")) : null;
        this.level = inits.isInitialized("level") ? new com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QBoulderLevelEntity(forProperty("level"), inits.get("level")) : null;
    }

}

