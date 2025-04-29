package com.dyno.climb.record.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionClimbingRecordEntity is a Querydsl query type for MissionClimbingRecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionClimbingRecordEntity extends EntityPathBase<MissionClimbingRecordEntity> {

    private static final long serialVersionUID = -1119169333L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionClimbingRecordEntity missionClimbingRecordEntity = new QMissionClimbingRecordEntity("missionClimbingRecordEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath missionGroupId = createString("missionGroupId");

    public final StringPath missionId = createString("missionId");

    public final QClimbingRecordEntity record;

    public final EnumPath<com.dyno.climb.record.domain.MissionRecordStatus> status = createEnum("status", com.dyno.climb.record.domain.MissionRecordStatus.class);

    public final StringPath uid = createString("uid");

    public QMissionClimbingRecordEntity(String variable) {
        this(MissionClimbingRecordEntity.class, forVariable(variable), INITS);
    }

    public QMissionClimbingRecordEntity(Path<? extends MissionClimbingRecordEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionClimbingRecordEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionClimbingRecordEntity(PathMetadata metadata, PathInits inits) {
        this(MissionClimbingRecordEntity.class, metadata, inits);
    }

    public QMissionClimbingRecordEntity(Class<? extends MissionClimbingRecordEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.record = inits.isInitialized("record") ? new QClimbingRecordEntity(forProperty("record"), inits.get("record")) : null;
    }

}

