package com.dyno.climb.feature.home.service;

import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.user.domain.User;

public interface GetHomeUseCase {
  HomeDto get(User user, SearchFilter searchFilter);
}
