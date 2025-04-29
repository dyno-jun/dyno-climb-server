package com.dyno.climb.record.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImageEntity is a Querydsl query type for ImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageEntity extends EntityPathBase<ImageEntity> {

    private static final long serialVersionUID = 163456252L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImageEntity imageEntity = new QImageEntity("imageEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClimbingRecordEntity record;

    public final StringPath uid = createString("uid");

    public final StringPath url = createString("url");

    public QImageEntity(String variable) {
        this(ImageEntity.class, forVariable(variable), INITS);
    }

    public QImageEntity(Path<? extends ImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImageEntity(PathMetadata metadata, PathInits inits) {
        this(ImageEntity.class, metadata, inits);
    }

    public QImageEntity(Class<? extends ImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.record = inits.isInitialized("record") ? new QClimbingRecordEntity(forProperty("record"), inits.get("record")) : null;
    }

}

