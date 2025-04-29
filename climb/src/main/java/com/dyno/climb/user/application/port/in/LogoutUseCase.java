package com.dyno.climb.user.application.port.in;

import com.dyno.climb.user.domain.User;

public interface LogoutUseCase {
  void logout(User user);
}
