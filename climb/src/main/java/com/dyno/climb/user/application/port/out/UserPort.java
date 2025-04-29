package com.dyno.climb.user.application.port.out;

import com.dyno.climb.user.domain.User;

import java.util.List;

public interface UserPort {
  User findByAccountId(String accountId);

  void save(User user);

  List<User> findByIds(List<String> ids);
}
