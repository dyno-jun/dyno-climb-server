package com.dyno.climb.gym.application.port.in;

import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.command.SearchQuery;
import java.util.List;

public interface GetGymUseCase {
  List<Gym> getAll();

  List<Gym> getByQuery(SearchQuery searchQuery);

  Gym getById(String id);

  List<Gym> getByIds(List<String> ids);
}
