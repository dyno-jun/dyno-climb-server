package com.dyno.climb.record.application.port;

import com.dyno.climb.record.domain.Hashtag;
import java.util.List;
import java.util.Optional;

public interface HashtagPort {
  List<Hashtag> findByKeyword(String keyword);

  Optional<Hashtag> findByName(String name);

  void save(Hashtag record);
}
