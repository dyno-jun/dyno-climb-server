package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGymEntity is a Querydsl query type for GymEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGymEntity extends EntityPathBase<GymEntity> {

    private static final long serialVersionUID = -631288949L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGymEntity gymEntity = new QGymEntity("gymEntity");

    public final QAddressDto address;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QImageDto image;

    public final ListPath<BoulderLevelEntity, QBoulderLevelEntity> levels = this.<BoulderLevelEntity, QBoulderLevelEntity>createList("levels", BoulderLevelEntity.class, QBoulderLevelEntity.class, PathInits.DIRECT2);

    public final QLinkDto link;

    public final QLocationDto location;

    public final StringPath name = createString("name");

    public final QTelDto tel;

    public final StringPath uid = createString("uid");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QGymEntity(String variable) {
        this(GymEntity.class, forVariable(variable), INITS);
    }

    public QGymEntity(Path<? extends GymEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGymEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGymEntity(PathMetadata metadata, PathInits inits) {
        this(GymEntity.class, metadata, inits);
    }

    public QGymEntity(Class<? extends GymEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddressDto(forProperty("address")) : null;
        this.image = inits.isInitialized("image") ? new QImageDto(forProperty("image")) : null;
        this.link = inits.isInitialized("link") ? new QLinkDto(forProperty("link")) : null;
        this.location = inits.isInitialized("location") ? new QLocationDto(forProperty("location")) : null;
        this.tel = inits.isInitialized("tel") ? new QTelDto(forProperty("tel")) : null;
    }

}

