package com.dyno.climb.record.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideoEntity is a Querydsl query type for VideoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoEntity extends EntityPathBase<VideoEntity> {

    private static final long serialVersionUID = -968968164L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideoEntity videoEntity = new QVideoEntity("videoEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClimbingRecordEntity record;

    public final StringPath uid = createString("uid");

    public final StringPath url = createString("url");

    public QVideoEntity(String variable) {
        this(VideoEntity.class, forVariable(variable), INITS);
    }

    public QVideoEntity(Path<? extends VideoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVideoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVideoEntity(PathMetadata metadata, PathInits inits) {
        this(VideoEntity.class, metadata, inits);
    }

    public QVideoEntity(Class<? extends VideoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.record = inits.isInitialized("record") ? new QClimbingRecordEntity(forProperty("record"), inits.get("record")) : null;
    }

}

