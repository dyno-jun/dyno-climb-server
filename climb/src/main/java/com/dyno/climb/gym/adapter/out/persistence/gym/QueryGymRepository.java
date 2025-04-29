package com.dyno.climb.gym.adapter.out.persistence.gym;

import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.GymEntity;
import com.dyno.climb.gym.domain.command.SearchQuery;

import java.util.List;

public interface QueryGymRepository {
  List<GymEntity> findByQuery(SearchQuery query);
}
