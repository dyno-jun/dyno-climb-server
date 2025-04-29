package com.dyno.climb.gym.adapter.out.persistence.gym;

import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.BoulderLevelEntity;
import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.GymEntity;
import com.dyno.climb.gym.domain.Gym;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class GymMapper {

  public List<Gym> mapToGym(List<GymEntity> gyms) {
    return gyms.stream().map(this::mapToGym).collect(Collectors.toList());
  }

  public Gym mapToGym(GymEntity entity) {
    return new Gym(
        entity.getUid(),
        entity.getName(),
        entity.getAddress().asDomain(),
        entity.getLocation().asDomain(),
        entity.getTel().asDomain(),
        entity.getImage().asDomain(),
        entity.getLink().asDomain(),
        entity.getUpdatedAt(),
        entity.getLevels().stream()
            .map(
                BoulderLevelEntity::asDomain)
            .collect(Collectors.toList()));
  }
}
