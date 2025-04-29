package com.dyno.climb.feature.search.service;

import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.user.domain.User;

public interface SearchUseCase {
  SearchDto search(User user, SearchFilter searchFilter);
}
