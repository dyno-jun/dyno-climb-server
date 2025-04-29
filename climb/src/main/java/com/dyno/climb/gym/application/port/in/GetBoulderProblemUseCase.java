package com.dyno.climb.gym.application.port.in;

import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.command.BoulderProblemQuery;
import java.util.List;

public interface GetBoulderProblemUseCase {
  List<BoulderProblem> getAll();

  List<BoulderProblem> getByQuery(BoulderProblemQuery query);

  BoulderProblem getById(String id);

  List<BoulderProblem> getByIds(List<String> ids);
}
