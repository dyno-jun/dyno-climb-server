package com.dyno.climb.gym.application.port.out;

import com.dyno.climb.gym.domain.BoulderProblem;
import java.util.List;

public interface BoulderProblemPort {
  List<BoulderProblem> findAll();

  BoulderProblem findById(String id);

  List<BoulderProblem> findByIds(List<String> ids);
}
