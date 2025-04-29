package com.dyno.climb.record.application.service;

import com.dyno.climb.user.domain.User;

public interface CreateHashtagUseCase {
  String save(User user, String name);
}
