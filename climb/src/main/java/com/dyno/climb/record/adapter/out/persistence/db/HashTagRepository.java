package com.dyno.climb.record.adapter.out.persistence.db;

import com.dyno.climb.record.adapter.out.persistence.db.entity.HashTagEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long> {
  Optional<HashTagEntity> findByName(String name);

  List<HashTagEntity> findByNameContaining(String name);
}
