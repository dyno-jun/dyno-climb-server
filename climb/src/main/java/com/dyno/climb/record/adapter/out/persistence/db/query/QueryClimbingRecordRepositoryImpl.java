package com.dyno.climb.record.adapter.out.persistence.db.query;

import com.dyno.climb.record.adapter.out.persistence.QueryClimbingRecordRepository;
import com.dyno.climb.record.adapter.out.persistence.db.entity.ClimbingRecordEntity;
import com.dyno.climb.record.adapter.out.persistence.db.entity.QClimbingRecordEntity;
import com.dyno.climb.record.domain.command.RecordSearchQuery;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class QueryClimbingRecordRepositoryImpl implements QueryClimbingRecordRepository {
  private final JPAQueryFactory jpaQueryFactory;

  public QueryClimbingRecordRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<ClimbingRecordEntity> findByQuery(RecordSearchQuery query) {
    BooleanExpression predicate = QClimbingRecordEntity.climbingRecordEntity.isNotNull();
    if (query.missionId() != null
        && !query.missionId().isEmpty()
        && query.missionGroupId() != null
        && !query.missionGroupId().isEmpty()) {
      predicate =
          predicate
              .and(
                  QClimbingRecordEntity.climbingRecordEntity.missionClimbingRecord.missionId.eq(
                      query.missionId()))
              .and(
                  QClimbingRecordEntity.climbingRecordEntity.missionClimbingRecord.missionGroupId
                      .eq(query.missionGroupId()));
    }

    if (query.keyword() != null && !query.keyword().isEmpty()) {
      predicate =
          predicate.and(
              QClimbingRecordEntity.climbingRecordEntity.memo.like("%" + query.keyword() + "%"));
    }

    return jpaQueryFactory
        .selectFrom(QClimbingRecordEntity.climbingRecordEntity)
        .where(predicate)
        .fetch();
  }
}
