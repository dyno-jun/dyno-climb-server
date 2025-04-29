package com.dyno.climb.record.adapter.out.persistence.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClimbingRecordEntity is a Querydsl query type for ClimbingRecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClimbingRecordEntity extends EntityPathBase<ClimbingRecordEntity> {

    private static final long serialVersionUID = -805266653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClimbingRecordEntity climbingRecordEntity = new QClimbingRecordEntity("climbingRecordEntity");

    public final StringPath boulderProblemId = createString("boulderProblemId");

    public final BooleanPath completed = createBoolean("completed");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final StringPath gymId = createString("gymId");

    public final ListPath<HashTagClimbingRecordEntity, QHashTagClimbingRecordEntity> hashTags = this.<HashTagClimbingRecordEntity, QHashTagClimbingRecordEntity>createList("hashTags", HashTagClimbingRecordEntity.class, QHashTagClimbingRecordEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ImageEntity, QImageEntity> images = this.<ImageEntity, QImageEntity>createList("images", ImageEntity.class, QImageEntity.class, PathInits.DIRECT2);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final StringPath memo = createString("memo");

    public final QMissionClimbingRecordEntity missionClimbingRecord;

    public final NumberPath<Integer> tryCnt = createNumber("tryCnt", Integer.class);

    public final StringPath uid = createString("uid");

    public final StringPath userId = createString("userId");

    public final ListPath<VideoEntity, QVideoEntity> videos = this.<VideoEntity, QVideoEntity>createList("videos", VideoEntity.class, QVideoEntity.class, PathInits.DIRECT2);

    public QClimbingRecordEntity(String variable) {
        this(ClimbingRecordEntity.class, forVariable(variable), INITS);
    }

    public QClimbingRecordEntity(Path<? extends ClimbingRecordEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClimbingRecordEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClimbingRecordEntity(PathMetadata metadata, PathInits inits) {
        this(ClimbingRecordEntity.class, metadata, inits);
    }

    public QClimbingRecordEntity(Class<? extends ClimbingRecordEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.missionClimbingRecord = inits.isInitialized("missionClimbingRecord") ? new QMissionClimbingRecordEntity(forProperty("missionClimbingRecord"), inits.get("missionClimbingRecord")) : null;
    }

}

