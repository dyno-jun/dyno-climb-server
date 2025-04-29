package com.dyno.climb.record.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTagClimbingRecordEntity is a Querydsl query type for HashTagClimbingRecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashTagClimbingRecordEntity extends EntityPathBase<HashTagClimbingRecordEntity> {

    private static final long serialVersionUID = -230078741L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashTagClimbingRecordEntity hashTagClimbingRecordEntity = new QHashTagClimbingRecordEntity("hashTagClimbingRecordEntity");

    public final QHashTagEntity hashTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClimbingRecordEntity record;

    public final StringPath uid = createString("uid");

    public QHashTagClimbingRecordEntity(String variable) {
        this(HashTagClimbingRecordEntity.class, forVariable(variable), INITS);
    }

    public QHashTagClimbingRecordEntity(Path<? extends HashTagClimbingRecordEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashTagClimbingRecordEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashTagClimbingRecordEntity(PathMetadata metadata, PathInits inits) {
        this(HashTagClimbingRecordEntity.class, metadata, inits);
    }

    public QHashTagClimbingRecordEntity(Class<? extends HashTagClimbingRecordEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashTag = inits.isInitialized("hashTag") ? new QHashTagEntity(forProperty("hashTag")) : null;
        this.record = inits.isInitialized("record") ? new QClimbingRecordEntity(forProperty("record"), inits.get("record")) : null;
    }

}

