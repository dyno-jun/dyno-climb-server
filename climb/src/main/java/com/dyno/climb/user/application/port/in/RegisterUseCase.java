package com.dyno.climb.user.application.port.in;

import com.dyno.climb.user.domain.command.CreateUser;

public interface RegisterUseCase {
  void register(CreateUser createUser);
}
