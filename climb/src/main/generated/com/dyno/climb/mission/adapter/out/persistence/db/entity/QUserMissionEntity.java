package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserMissionEntity is a Querydsl query type for UserMissionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMissionEntity extends EntityPathBase<UserMissionEntity> {

    private static final long serialVersionUID = -1519253995L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMissionEntity userMissionEntity = new QUserMissionEntity("userMissionEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath missionGroupId = createString("missionGroupId");

    public final StringPath missionId = createString("missionId");

    public final QUserRecordSummaryDto summary;

    public final StringPath uid = createString("uid");

    public final StringPath userId = createString("userId");

    public QUserMissionEntity(String variable) {
        this(UserMissionEntity.class, forVariable(variable), INITS);
    }

    public QUserMissionEntity(Path<? extends UserMissionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserMissionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserMissionEntity(PathMetadata metadata, PathInits inits) {
        this(UserMissionEntity.class, metadata, inits);
    }

    public QUserMissionEntity(Class<? extends UserMissionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.summary = inits.isInitialized("summary") ? new QUserRecordSummaryDto(forProperty("summary")) : null;
    }

}

