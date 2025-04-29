package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionGroupEntity is a Querydsl query type for MissionGroupEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionGroupEntity extends EntityPathBase<MissionGroupEntity> {

    private static final long serialVersionUID = 86150309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionGroupEntity missionGroupEntity = new QMissionGroupEntity("missionGroupEntity");

    public final StringPath description = createString("description");

    public final StringPath gymId = createString("gymId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QImageDto image;

    public final QMissionPeriodDto period;

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalExp = createNumber("totalExp", Integer.class);

    public final EnumPath<com.dyno.climb.mission.domain.vo.MissionGroupType> type = createEnum("type", com.dyno.climb.mission.domain.vo.MissionGroupType.class);

    public final StringPath uid = createString("uid");

    public QMissionGroupEntity(String variable) {
        this(MissionGroupEntity.class, forVariable(variable), INITS);
    }

    public QMissionGroupEntity(Path<? extends MissionGroupEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionGroupEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionGroupEntity(PathMetadata metadata, PathInits inits) {
        this(MissionGroupEntity.class, metadata, inits);
    }

    public QMissionGroupEntity(Class<? extends MissionGroupEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QImageDto(forProperty("image")) : null;
        this.period = inits.isInitialized("period") ? new QMissionPeriodDto(forProperty("period")) : null;
    }

}

