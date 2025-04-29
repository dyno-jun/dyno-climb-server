package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMissionPeriodDto is a Querydsl query type for MissionPeriodDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMissionPeriodDto extends BeanPath<MissionPeriodDto> {

    private static final long serialVersionUID = 1276908161L;

    public static final QMissionPeriodDto missionPeriodDto = new QMissionPeriodDto("missionPeriodDto");

    public final DateTimePath<java.time.LocalDateTime> endAt = createDateTime("endAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startAt = createDateTime("startAt", java.time.LocalDateTime.class);

    public QMissionPeriodDto(String variable) {
        super(MissionPeriodDto.class, forVariable(variable));
    }

    public QMissionPeriodDto(Path<? extends MissionPeriodDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMissionPeriodDto(PathMetadata metadata) {
        super(MissionPeriodDto.class, metadata);
    }

}

