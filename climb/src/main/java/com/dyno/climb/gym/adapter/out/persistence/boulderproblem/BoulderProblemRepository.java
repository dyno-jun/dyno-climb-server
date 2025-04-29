package com.dyno.climb.gym.adapter.out.persistence.boulderproblem;

import com.dyno.climb.gym.adapter.out.persistence.boulderproblem.db.entity.BoulderProblemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoulderProblemRepository extends JpaRepository<BoulderProblemEntity, Long> {
  BoulderProblemEntity findByUid(String uid);

  List<BoulderProblemEntity> findByUidIn(List<String> ids);
}
