package com.dyno.climb.user.adapter.out.persistence.db;

import com.dyno.climb.user.adapter.out.persistence.db.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByAccountId(String accountId);

  List<UserEntity> findByUidIn(List<String> ids);
}
