package com.dyno.climb.gym.adapter.out.persistence.gym;

import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.GymEntity;
import com.dyno.climb.gym.domain.command.SearchQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymRepository extends JpaRepository<GymEntity, Long>, QueryGymRepository {
  GymEntity findByUid(String uid);
}
