package com.dyno.climb.gym.adapter.out.persistence.boulderproblem;

import com.dyno.climb.gym.adapter.out.persistence.boulderproblem.db.entity.BoulderProblemEntity;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.shared.vo.Image;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BoulderProblemMapper {

  public List<BoulderProblem> mapTo(List<BoulderProblemEntity> boulderProblemEntities) {
    return boulderProblemEntities.stream().map(this::mapTo).collect(Collectors.toList());
  }

  public BoulderProblem mapTo(BoulderProblemEntity entity) {
    return new BoulderProblem(
        entity.getUid(),
        entity.getGym().getUid(),
        entity.getSector(),
        entity.getLevel().asDomain(),
        new Image(entity.getImage().getUrl()));
  }
}
