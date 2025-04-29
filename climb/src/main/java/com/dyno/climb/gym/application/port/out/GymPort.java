package com.dyno.climb.gym.application.port.out;

import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.command.SearchQuery;

import java.util.List;

public interface GymPort {
  List<Gym> findAll();

  List<Gym> findByQuery(SearchQuery query);

  Gym findById(String id);
}
