package com.dyno.climb.gym.adapter.out.persistence.boulderproblem;

import com.dyno.climb.gym.adapter.out.persistence.boulderproblem.db.entity.BoulderProblemEntity;
import com.dyno.climb.gym.application.port.out.BoulderProblemPort;
import com.dyno.climb.gym.domain.BoulderProblem;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BoulderProblemPersistenceAdapter implements BoulderProblemPort {
  private final BoulderProblemMapper mapper;
  private final BoulderProblemRepository boulderProblemRepository;

  public BoulderProblemPersistenceAdapter(
      BoulderProblemMapper mapper, BoulderProblemRepository boulderProblemRepository) {
    this.mapper = mapper;
    this.boulderProblemRepository = boulderProblemRepository;
  }

  @Override
  public List<BoulderProblem> findAll() {
    final List<BoulderProblemEntity> boulderProblemEntities = boulderProblemRepository.findAll();
    return mapper.mapTo(boulderProblemEntities);
  }

  @Override
  public BoulderProblem findById(String id) {
    return mapper.mapTo(boulderProblemRepository.findByUid(id));
  }

  @Override
  public List<BoulderProblem> findByIds(List<String> ids) {
    final List<BoulderProblemEntity> boulderProblemEntities =
        boulderProblemRepository.findByUidIn(ids);
    return mapper.mapTo(boulderProblemEntities);
  }
}
