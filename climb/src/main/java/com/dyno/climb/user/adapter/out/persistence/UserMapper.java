package com.dyno.climb.user.adapter.out.persistence;

import com.dyno.climb.user.adapter.out.persistence.db.entity.UserEntity;
import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.UserLevel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public User mapToUser(UserEntity user) {
    if (user == null) {
      return null;
    }

    return new User(
        user.getUid(),
        user.getAccountId(),
        new UserLevel(
            user.getLevel().getCurrentExp(),
            user.getLevel().getLevelUpExp(),
            user.getLevel().getLevel()),
        "USER",
        user.getPassword(),
        user.getNickName(),
        user.getStartDate(),
        user.getReach());
  }
}
