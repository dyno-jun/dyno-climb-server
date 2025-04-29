package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionEntity is a Querydsl query type for MissionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionEntity extends EntityPathBase<MissionEntity> {

    private static final long serialVersionUID = 761360544L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionEntity missionEntity = new QMissionEntity("missionEntity");

    public final StringPath boulderProblemId = createString("boulderProblemId");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> exp = createNumber("exp", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QImageDto image;

    public final StringPath missionGroupId = createString("missionGroupId");

    public final StringPath title = createString("title");

    public final EnumPath<com.dyno.climb.mission.domain.vo.MissionType> type = createEnum("type", com.dyno.climb.mission.domain.vo.MissionType.class);

    public final StringPath uid = createString("uid");

    public QMissionEntity(String variable) {
        this(MissionEntity.class, forVariable(variable), INITS);
    }

    public QMissionEntity(Path<? extends MissionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionEntity(PathMetadata metadata, PathInits inits) {
        this(MissionEntity.class, metadata, inits);
    }

    public QMissionEntity(Class<? extends MissionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QImageDto(forProperty("image")) : null;
    }

}

