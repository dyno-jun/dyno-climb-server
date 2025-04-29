package com.dyno.climb.user.application.port.in;

import com.dyno.climb.user.domain.User;
import java.util.List;

public interface GetUserUseCase {
  List<User> getUserByIds(List<String> ids);
}
