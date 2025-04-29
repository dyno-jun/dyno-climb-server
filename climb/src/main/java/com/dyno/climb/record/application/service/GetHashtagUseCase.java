package com.dyno.climb.record.application.service;

import com.dyno.climb.record.domain.Hashtag;
import java.util.List;

public interface GetHashtagUseCase {
  List<Hashtag> getTagsByKeyword(String keyword);
}
