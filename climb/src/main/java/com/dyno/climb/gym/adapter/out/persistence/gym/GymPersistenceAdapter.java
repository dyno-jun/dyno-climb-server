package com.dyno.climb.gym.adapter.out.persistence.gym;

import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.GymEntity;
import com.dyno.climb.gym.application.port.out.GymPort;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.command.SearchQuery;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GymPersistenceAdapter implements GymPort {
  private final GymMapper mapper;
  private final GymRepository gymRepository;

  public GymPersistenceAdapter(GymMapper mapper, GymRepository gymRepository) {
    this.mapper = mapper;
    this.gymRepository = gymRepository;
  }

  @Override
  public List<Gym> findAll() {
    final List<GymEntity> gyms = gymRepository.findAll();
    return mapper.mapToGym(gyms);
  }

  @Override
  public List<Gym> findByQuery(SearchQuery query) {
    final List<GymEntity> gyms = gymRepository.findByQuery(query);
    return mapper.mapToGym(gyms);
  }

  @Override
  public Gym findById(String id) {
    return mapper.mapToGym(gymRepository.findByUid(id));
  }
}
