package com.dyno.climb.record.domain.service;

import com.dyno.climb.record.application.port.HashtagPort;
import com.dyno.climb.record.application.service.CreateHashtagUseCase;
import com.dyno.climb.record.application.service.GetHashtagUseCase;
import com.dyno.climb.record.domain.Hashtag;
import com.dyno.climb.shared.utils.IDGenerator;
import com.dyno.climb.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class HashtagService implements GetHashtagUseCase, CreateHashtagUseCase {
  private final HashtagPort hashtagPort;

  public HashtagService(HashtagPort hashtagPort) {
    this.hashtagPort = hashtagPort;
  }

  @Override
  public String save(User user, String name) {
    Optional<Hashtag> tag = hashtagPort.findByName(name);
    if (tag.isPresent()) {
      return tag.get().getId();
    }

    Hashtag hashtag = new Hashtag(IDGenerator.generate(), name);
    hashtagPort.save(hashtag);
    return hashtag.getId();
  }

  @Override
  public List<Hashtag> getTagsByKeyword(String keyword) {
    return hashtagPort.findByKeyword(keyword);
  }
}
