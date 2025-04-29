package com.dyno.climb.gym.application.service;

import com.dyno.climb.gym.application.port.out.GymPort;
import com.dyno.climb.gym.application.port.in.GetGymUseCase;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.command.SearchQuery;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GymService implements GetGymUseCase {
  private final GymPort gymPort;

  public GymService(GymPort gymPort) {
    this.gymPort = gymPort;
  }

  @Override
  public List<Gym> getAll() {
    return gymPort.findAll();
  }

  @Override
  public List<Gym> getByQuery(SearchQuery searchQuery) {
    return gymPort.findByQuery(searchQuery);
  }

  @Override
  public Gym getById(String id) {
    return gymPort.findById(id);
  }

  @Override
  public List<Gym> getByIds(List<String> ids) {
    return getAll();
  }
}
