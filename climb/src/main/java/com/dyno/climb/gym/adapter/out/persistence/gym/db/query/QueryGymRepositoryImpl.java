package com.dyno.climb.gym.adapter.out.persistence.gym.db.query;

import com.dyno.climb.gym.adapter.out.persistence.gym.QueryGymRepository;
import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.GymEntity;
import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.QGymEntity;
import com.dyno.climb.gym.domain.command.SearchQuery;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class QueryGymRepositoryImpl implements QueryGymRepository {
  private static final double EARTH_RADIUS = 6371.0; // Radius in kilometers
  ;
  private final JPAQueryFactory jpaQueryFactory;

  public QueryGymRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<GymEntity> findByQuery(SearchQuery query) {
    QGymEntity gym = QGymEntity.gymEntity;

    BooleanExpression predicate = gym.isNotNull();
    if (query.keyword() != null && !query.keyword().isEmpty()) {
      predicate = predicate.and(QGymEntity.gymEntity.name.like("%" + query.keyword() + "%"));
    }

    NumberExpression<Double> distanceExpression =
        Expressions.numberTemplate(
            Double.class,
            "{0} * acos("
                + "cos(radians({1})) * "
                + "cos(radians({2})) * "
                + "cos(radians({3}) - radians({4})) + "
                + "sin(radians({1})) * "
                + "sin(radians({2}))"
                + ")",
            EARTH_RADIUS,
            query.lat(),
            gym.location.latitude,
            gym.location.longitude,
            query.lng());

    return jpaQueryFactory
        .selectFrom(QGymEntity.gymEntity)
        .where(predicate)
        .orderBy(distanceExpression.asc())
        .fetch();
  }
}
