package com.dyno.climb.user.application.service;

import com.dyno.climb.user.application.port.out.UserPort;
import com.dyno.climb.user.application.port.in.GetUserUseCase;
import com.dyno.climb.user.application.port.in.LogoutUseCase;
import com.dyno.climb.user.application.port.in.UpdateUserUseCase;
import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.command.UpdateUser;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService implements GetUserUseCase, UpdateUserUseCase, LogoutUseCase {

  private final UserPort userPort;

  public UserService(UserPort userPort) {
    this.userPort = userPort;
  }

  @Override
  public void logout(User user) {}

  @Override
  public void update(User user, UpdateUser updateUser) {
    user.update(updateUser);
    userPort.save(user);
  }

  @Override
  public List<User> getUserByIds(List<String> ids) {
    return userPort.findByIds(ids);
  }
}
