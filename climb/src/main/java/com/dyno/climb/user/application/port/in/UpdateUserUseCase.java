package com.dyno.climb.user.application.port.in;

import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.command.UpdateUser;

public interface UpdateUserUseCase {

  void update(User user, UpdateUser updateUser);
}
