package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMissionConditionEntity is a Querydsl query type for MissionConditionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionConditionEntity extends EntityPathBase<MissionConditionEntity> {

    private static final long serialVersionUID = 1981887681L;

    public static final QMissionConditionEntity missionConditionEntity = new QMissionConditionEntity("missionConditionEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.dyno.climb.mission.domain.vo.InEquality> inequality = createEnum("inequality", com.dyno.climb.mission.domain.vo.InEquality.class);

    public final StringPath missionId = createString("missionId");

    public final EnumPath<com.dyno.climb.mission.domain.vo.ConditionType> type = createEnum("type", com.dyno.climb.mission.domain.vo.ConditionType.class);

    public final StringPath uid = createString("uid");

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QMissionConditionEntity(String variable) {
        super(MissionConditionEntity.class, forVariable(variable));
    }

    public QMissionConditionEntity(Path<? extends MissionConditionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMissionConditionEntity(PathMetadata metadata) {
        super(MissionConditionEntity.class, metadata);
    }

}

