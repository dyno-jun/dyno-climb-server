package com.dyno.climb.gym.application.service;

import com.dyno.climb.gym.application.port.out.BoulderProblemPort;
import com.dyno.climb.gym.application.port.in.GetBoulderProblemUseCase;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.command.BoulderProblemQuery;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BoulderProblemService implements GetBoulderProblemUseCase {
  private final BoulderProblemPort boulderProblemPort;

  public BoulderProblemService(BoulderProblemPort boulderProblemPort) {
    this.boulderProblemPort = boulderProblemPort;
  }

  @Override
  public List<BoulderProblem> getAll() {
    return boulderProblemPort.findAll();
  }

  @Override
  public List<BoulderProblem> getByQuery(BoulderProblemQuery query) {
    return boulderProblemPort.findAll();
  }

  @Override
  public BoulderProblem getById(String id) {
    return boulderProblemPort.findById(id);
  }

  @Override
  public List<BoulderProblem> getByIds(List<String> ids) {
    return boulderProblemPort.findByIds(ids);
  }
}
