package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoulderLevelEntity is a Querydsl query type for BoulderLevelEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoulderLevelEntity extends EntityPathBase<BoulderLevelEntity> {

    private static final long serialVersionUID = -2013658899L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoulderLevelEntity boulderLevelEntity = new QBoulderLevelEntity("boulderLevelEntity");

    public final StringPath color = createString("color");

    public final QGymEntity gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath uid = createString("uid");

    public QBoulderLevelEntity(String variable) {
        this(BoulderLevelEntity.class, forVariable(variable), INITS);
    }

    public QBoulderLevelEntity(Path<? extends BoulderLevelEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoulderLevelEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoulderLevelEntity(PathMetadata metadata, PathInits inits) {
        this(BoulderLevelEntity.class, metadata, inits);
    }

    public QBoulderLevelEntity(Class<? extends BoulderLevelEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new QGymEntity(forProperty("gym"), inits.get("gym")) : null;
    }

}

