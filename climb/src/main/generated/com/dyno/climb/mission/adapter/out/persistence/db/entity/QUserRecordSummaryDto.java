package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRecordSummaryDto is a Querydsl query type for UserRecordSummaryDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserRecordSummaryDto extends BeanPath<UserRecordSummaryDto> {

    private static final long serialVersionUID = 958861380L;

    public static final QUserRecordSummaryDto userRecordSummaryDto = new QUserRecordSummaryDto("userRecordSummaryDto");

    public final EnumPath<com.dyno.climb.mission.domain.vo.MissionStatus> status = createEnum("status", com.dyno.climb.mission.domain.vo.MissionStatus.class);

    public final NumberPath<Integer> tryCnt = createNumber("tryCnt", Integer.class);

    public QUserRecordSummaryDto(String variable) {
        super(UserRecordSummaryDto.class, forVariable(variable));
    }

    public QUserRecordSummaryDto(Path<? extends UserRecordSummaryDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRecordSummaryDto(PathMetadata metadata) {
        super(UserRecordSummaryDto.class, metadata);
    }

}

